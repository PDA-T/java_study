package �������.NIO������;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Set;

/**
 * �����
 */
public class Server {
    // ����˶˿ں�
    public static final int SERVER_PORT = 8888;
    // �ַ�������
    private Charset charset = Charset.forName("GBK");
    // ��������
    private ByteBuffer readBuffer = ByteBuffer.allocate(1024);
    // д������
    private ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
    // �û�����,������ӽ������û���
    private static ChatRoomMap<String, SocketChannel> clients = new ChatRoomMap<>();
    // ѡ����
    private Selector selector;
    /**
     * ��������ʼ��
     */
    private void init(){
        try {
            // �õ��������׽���ͨ��
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            // ���÷�����ģʽ
            serverSocketChannel.configureBlocking(false);
            // ͨ��ͨ���õ��������׽���
            ServerSocket serverSocket = serverSocketChannel.socket();
            // �󶨵�ַ�Ͷ˿�
            serverSocket.bind(new InetSocketAddress("127.0.0.1",SERVER_PORT));
            // ��ʼ��ѡ����
            selector = Selector.open();
            // ���ø���Ȥ���¼�(�ȴ��ͻ��˽����¼�)
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            System.out.println("����������ʧ��,����˿ں�");
        }
    }
    /**
     * ѭ������ͨ��
     */
    public void listen(){
        while (true){
            try {
                // �鿴ע�ᵽѡ������ͨ���Ƿ�׼����
                int count = selector.select(5000);
                // �������0��ʾû��ͨ��׼����
                if (count == 0){
                    // ����ѭ��
                    continue;
                }
                // �õ�׼����ͨ���ļ���
                Set<SelectionKey> keys = selector.selectedKeys();
                // ����׼���õ�ͨ��
                for (SelectionKey key:keys){
                    // ����׼��������ͨ��
                    handler(key);
                    // �Ӽ������Ƴ�������ɵ�ͨ��
                    keys.remove(key);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * ����׼��������ͨ��
     */
    private void handler(SelectionKey key){
        // ��������¼�׼������
        if (key.isAcceptable()){
            try {
                // �õ���Ӧ��ͨ��
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                // ����ͻ���,��ȡ�Ϳͻ���һһ��Ӧ�Ŀͻ����׽���ͨ��
                SocketChannel clientSocketChannel = serverSocketChannel.accept();
                // ����Ϊ������
                clientSocketChannel.configureBlocking(false);
                // ע���ѡ����,������ȤΪ��
                clientSocketChannel.register(selector,SelectionKey.OP_READ);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // ���Ϊ������׼����(�ͻ��˷���Ϣ��)
        }else if (key.isReadable()){
            // �õ�ͨ��
            SocketChannel clientSocketChannel = (SocketChannel) key.channel();
            try {
                // ��ն�������
                readBuffer.clear();
                // ������д�뻺����
                int hasRead = clientSocketChannel.read(readBuffer);
                // �������0��ʾ�����ݽ��뻺����
                if (hasRead>0){
                    // ��ת������
                    readBuffer.flip();
                    // ��ȡ����
                    String str = new String(readBuffer.array(),0,hasRead,"GBK");
                    // �������
                    System.out.println(str);
                    // ��ȡ�ͻ��˷������û���(�ж�Э�������ǰ��׺�Ƿ����û���)
                    if (str.startsWith(ChatRoomProtocol.USER_ROUND) && str.endsWith(ChatRoomProtocol.USER_ROUND)){
                        // �û���½
                        login(clientSocketChannel,str);
                        // �����˽��ǰ��׺
                    }else if (str.startsWith(ChatRoomProtocol.PRIVATEMSG_ROUND) && str.endsWith(ChatRoomProtocol.PRIVATEMSG_ROUND)){
                        // ����˽����Ϣ
                        sendMsyToUser(clientSocketChannel,str);
                        // ����ǹ�����Ϣ
                    }else if (str.startsWith(ChatRoomProtocol.PUBLICMSG_ROUND) && str.endsWith(ChatRoomProtocol.PUBLICMSG_ROUND)){
                        // �㲥��������
                        dispatch(clientSocketChannel,str);
                    }
                }
            } catch (IOException e) {
                // �Ƴ��ͻ���
                Server.clients.removeByValue(clientSocketChannel);
            }
        }
    }
    /**
     * ʵ�ֿͻ��˵�½
     */
    private void login(SocketChannel client,String str){
        try {
            // ȥ��ǰ��׺,�õ�����������
            String userName = getRealMsg(str);
            // �ж��û����Ƿ��ظ�
            if (Server.clients.map.containsKey(userName)){
                System.out.println("�û����ظ�");
                // ���д������
                writeBuffer.clear();
                // ��Ҫ��Ӧ�ͻ��˵���Ϣ��������д������(-1)
                writeBuffer.put(charset.encode(ChatRoomProtocol.USER_ROUND+
                        ChatRoomProtocol.NAME_REP+ChatRoomProtocol.USER_ROUND));
                // ��תд������
                writeBuffer.flip();
                // �жϻ������Ƿ�������
                while (writeBuffer.hasRemaining()){
                    // �ѻ���������д��ͨ��(���ͻ�����������)
                    client.write(writeBuffer);
                }
            }else {
                System.out.println("�û���½�ɹ�");
                // ���д������
                writeBuffer.clear();
                // ��Ҫ��Ӧ�ͻ��˵���Ϣ��������д������(1)
                writeBuffer.put(charset.encode(ChatRoomProtocol.USER_ROUND+
                        ChatRoomProtocol.LOGIN_SUCCESS+ChatRoomProtocol.USER_ROUND));
                // ��תд������
                writeBuffer.flip();
                // �жϻ������Ƿ�������
                while (writeBuffer.hasRemaining()){
                    // �ѻ���������д��ͨ��(���ͻ�����������)
                    client.write(writeBuffer);
                }
                // ����ӳ���ϵ
                Server.clients.put(userName,client);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    /**
     * ����˽����Ϣ
     */
    private void sendMsyToUser(SocketChannel client,String str){
        try {
            // �õ���Ϣ(����Ŀ���û�����Ϣ),ȥ��ǰ��׺
            String userAndMsg = getRealMsg(str);
            // ʹ�÷ָ��ַ�����,�û���
            String targetUser = userAndMsg.split(ChatRoomProtocol.SPLIT_SIGN)[0];
            // ��Ϣ����
            String privatemsg = userAndMsg.split(ChatRoomProtocol.SPLIT_SIGN)[1];
            // ���д������
            writeBuffer.clear();
            // ��Ҫ˵����Ϣ��������д������
            writeBuffer.put(charset.encode(Server.clients.getKeyByValue(client)+"˽��:"+privatemsg));
            // ��ת������
            writeBuffer.flip();
            // �жϻ������Ƿ�������
            while (writeBuffer.hasRemaining()){
                // �ѻ���������(Ҫ˵�Ļ�)���͸�Ŀ��ͻ���
                Server.clients.map.get(targetUser).write(writeBuffer);
            }
        }catch (IOException e){
            // �Ƴ��ͻ���
            Server.clients.removeByValue(client);
        }
    }
    /**
     * ���͸�������
     */
    private void dispatch(SocketChannel client,String str){
        // �õ���Ϣ(����Ŀ���û�����Ϣ),ȥ��ǰ��׺
        String publicMsg = getRealMsg(str);
        try {
            Set<SocketChannel> valueSet = Server.clients.getValueSet();
            // ��������
            for (SocketChannel cli:valueSet){
                // ���д������
                writeBuffer.clear();
                // ��Ҫ˵����Ϣ��������д������
                writeBuffer.put(charset.encode(Server.clients.getKeyByValue(client)+"˵:"+publicMsg));
                // ��תд������
                writeBuffer.flip();
                // �жϻ������Ƿ�������
                while (writeBuffer.hasRemaining()){
                    // �ѻ���������(Ҫ˵�Ļ�)���͸�������
                    cli.write(writeBuffer);
                }
            }
        }catch (IOException e){
            // �Ƴ��ͻ���
            Server.clients.removeByValue(client);
        }
    }
    /**
     * ȥ��Э��ǰ��׺
     */
    private String getRealMsg(String lines){
        // ��Э�鳤��(2)��ʼ��ȡ,��ȡ�����ַ������ȼ�ȥЭ�鳤��(2)
        return lines.substring(ChatRoomProtocol.PROTOCOL_LEN,lines.length()-ChatRoomProtocol.PROTOCOL_LEN);
    }

    public static void main(String[] args) {
        // ��������˶���
        Server server = new Server();
        // ���������
        server.init();
        // ����˼���
        server.listen();
    }
}

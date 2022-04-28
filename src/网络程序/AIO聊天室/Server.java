package �������.AIO������;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * �����
 */
public class Server {
    // �������˿�
    private static final int SERVER_PORT = 31000;
    // �ַ�������
    private static final String CHARSET = "UTF-8";
    // �ַ���
    private Charset charset = Charset.forName(CHARSET);
    // ���ӽ����Ŀͻ��˼���
    public static ChatRoomMap<String,AsynchronousSocketChannel> clients = new ChatRoomMap<>();
    // ��ʼ��������
    public void init(){
        try {
            // �����̳߳�
            ExecutorService executorService = Executors.newFixedThreadPool(20);
            // ����ͨ����
            AsynchronousChannelGroup channelGroup
                    = AsynchronousChannelGroup.withThreadPool(executorService);
            // �������׽���ͨ��
            AsynchronousServerSocketChannel serverSocketChannel
                    = AsynchronousServerSocketChannel.open(channelGroup);
            // ��IP�Ͷ˿�
            serverSocketChannel.bind(new InetSocketAddress("127.0.0.1",SERVER_PORT));
            // ѭ�����տͻ�������
            serverSocketChannel.accept(null,new AcceptHandler(serverSocketChannel));
        }catch (Exception e){
            System.out.println("����������ʧ��,����˿ں�");
        }
    }

    /**
     * �����ڲ���
     */
    private class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel,Object>{
        // �õ�������׽���ͨ��
        private AsynchronousServerSocketChannel serverSocketChannel;
        // ������
        public AcceptHandler(AsynchronousServerSocketChannel serverSocketChannel){
            this.serverSocketChannel = serverSocketChannel;
        }

        // ��������
        private ByteBuffer rbuffer = ByteBuffer.allocate(1024);
        // д������
        private ByteBuffer wbeffer = ByteBuffer.allocate(1024);
        /**
         * ���IO����֮�����
         * @param attachment
         */
        @Override
        public void completed(AsynchronousSocketChannel clientsocketChannel, Object attachment) {
            // �ݹ�,�ò���ϵͳȥ������һ���ͻ���,�Լ���������
            serverSocketChannel.accept(null,this);
            // ��ͨ������д�뻺����
            clientsocketChannel.read(rbuffer, null, new CompletionHandler<Integer, Object>() {
                @Override
                public void completed(Integer result, Object attachment) {
                    // ��ת������
                    rbuffer.flip();
                    // ��ȡ����
                    String content = charset.decode(rbuffer).toString();
                    // �����ͷΪ�û���Э���ַ�(˵�������������û���)
                    if (content.startsWith(ChatRoomProtocol.USER_ROUND) &&
                        content.endsWith(ChatRoomProtocol.USER_ROUND)){
                        // ��½
                        login(clientsocketChannel,content);
                        // ������Ϣ
                    }else if (content.startsWith(ChatRoomProtocol.PRIVATEMSG_ROUND) && content.endsWith(ChatRoomProtocol.PRIVATEMSG_ROUND)){
                        // ����˽����Ϣ
                        sendMsyToUser(clientsocketChannel,content);
                        // ����ǹ�����Ϣ
                    }else if (content.startsWith(ChatRoomProtocol.PUBLICMSG_ROUND) && content.endsWith(ChatRoomProtocol.PUBLICMSG_ROUND)){
                        // �㲥��������
                        dispatch(clientsocketChannel,content);
                    }
                    // ��ն�������
                    rbuffer.clear();
                    // �ٴ�ʹ�õݹ�ʵ���ظ�ѭ����ȡ����
                    clientsocketChannel.read(rbuffer,null,this);
                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    System.out.println("���ݶ�ȡʧ��:" + exc);
                    // �Ӽ����Ƴ��ͻ���
                    Server.clients.removeByValue(clientsocketChannel);
                }
            });
        }

        /**
         * ʧ��֮�����
         * @param exc
         * @param attachment
         */
        @Override
        public void failed(Throwable exc, Object attachment) {
            System.out.println("����ʧ��:" + exc);
        }

        /**
         * ʵ�ֿͻ��˵�½
         * @param client
         * @param content
         */
        private void login(AsynchronousSocketChannel client,String content){
            try {
                // ȥ��ǰ��׺,�õ�����������
                String userName = getRealMsg(content);
                // �ж��û����Ƿ��ظ�
                if (Server.clients.map.containsKey(userName)){
                    System.out.println("�û����ظ�");
                    // ��ջ�����
                    wbeffer.clear();
                    // д��Э���ַ����Ƿ�ɹ�
                    wbeffer.put(charset.encode(ChatRoomProtocol.USER_ROUND+
                            ChatRoomProtocol.NAME_REP+ChatRoomProtocol.USER_ROUND));
                    // ��תд������
                    wbeffer.flip();
                    // д��ͨ��
                    client.write(wbeffer).get();
                }else {
                    System.out.println("�û���½�ɹ�");
                    // ��ջ�����
                    wbeffer.clear();
                    // д��Э���ַ����Ƿ�ɹ�
                    wbeffer.put(charset.encode(ChatRoomProtocol.USER_ROUND+
                            ChatRoomProtocol.LOGIN_SUCCESS+ChatRoomProtocol.USER_ROUND));
                    // ��תд������
                    wbeffer.flip();
                    // д��ͨ��
                    client.write(wbeffer).get();
                    // �ѿͻ��˷��뼯��
                    Server.clients.put(userName,client);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        /**
         * ����˽����Ϣ
         */
        private void sendMsyToUser(AsynchronousSocketChannel client,String str){
            try {
                // �õ���Ϣ(����Ŀ���û�����Ϣ),ȥ��ǰ��׺
                String userAndMsg = getRealMsg(str);
                // ʹ�÷ָ��ַ�����,�û���
                String targetUser = userAndMsg.split(ChatRoomProtocol.SPLIT_SIGN)[0];
                // ��Ϣ����
                String privatemsg = userAndMsg.split(ChatRoomProtocol.SPLIT_SIGN)[1];
                // ���д������
                wbeffer.clear();
                // ��Ҫ˵����Ϣ��������д������
                wbeffer.put(charset.encode(Server.clients.getKeyByValue(client)+"˽��:"+privatemsg));
                // ��ת������
                wbeffer.flip();
                // �ѻ���������(Ҫ˵�Ļ�)���͸�Ŀ��ͻ���
                Server.clients.map.get(targetUser).write(wbeffer).get();
            }catch (Exception e){
                // �Ƴ��ͻ���
                Server.clients.removeByValue(client);
            }
        }
        /**
         * ���͸�������
         */
        private void dispatch(AsynchronousSocketChannel client, String str){
            // �õ���Ϣ(����Ŀ���û�����Ϣ),ȥ��ǰ��׺
            String publicMsg = getRealMsg(str);
            try {
                Set<AsynchronousSocketChannel> valueSet = Server.clients.getValueSet();
                // ��������
                for (AsynchronousSocketChannel cli:valueSet){
                    // ���д������
                    wbeffer.clear();
                    // ��Ҫ˵����Ϣ��������д������
                    wbeffer.put(charset.encode(Server.clients.getKeyByValue(client)+"˵:"+publicMsg));
                    // ��תд������
                    wbeffer.flip();
                    // �ѻ���������(Ҫ˵�Ļ�)���͸�������
                    cli.write(wbeffer).get();
                }
            }catch (Exception e){
                // �Ƴ��ͻ���
                Server.clients.removeByValue(client);
            }
        }
        /**
         * ȥ��Э��ǰ��׺
         */
        private String getRealMsg(String lines){
            // ��Э�鳤��(2)��ʼ��ȡ,��ȡ�����ַ������ȼ�ȥЭ�鳤��(2)
            return lines.substring(�������.NIO������.ChatRoomProtocol.PROTOCOL_LEN,lines.length()- �������.NIO������.ChatRoomProtocol.PROTOCOL_LEN);
        }
    }

    public static void main(String[] args) {
        // ���������
        Server server = new Server();
        // ��ʼ�������
        server.init();
        try {
            // �߳�����,�ȴ��ͻ�������(��Ϊ���첽����Ҫ�ֶ�����)
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

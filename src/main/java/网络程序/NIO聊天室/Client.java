package �������.NIO������;

import javax.swing.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.Set;

/**
 * �ͻ���
 */
public class Client {
    // �������˿�
    private static final int SERVER_PORT = 8888;
    // �ַ���������
    private Charset charset = Charset.forName("GBK");
    // ��������
    private ByteBuffer rbuffer = ByteBuffer.allocate(1024);
    // д������
    private ByteBuffer wbuffer = ByteBuffer.allocate(1024);
    // ѡ����
    private Selector selector;
    // ��һ�ε�½ʧ�ܺ����ʾ
    private String tip = "";
    /**
     * ��ʼ���ͻ���
     */
    private void init(){
        try {
            // ��ȡ�׽���ͨ��
            SocketChannel clientChannel = SocketChannel.open();
            // ���÷�����
            clientChannel.configureBlocking(false);
            // ��ȡѡ����
            selector = Selector.open();
            // ע���ѡ����,������Ȥ�¼�Ϊ����
            clientChannel.register(selector, SelectionKey.OP_CONNECT);
            // ���ӷ�����
            clientChannel.connect(new InetSocketAddress("127.0.0.1",SERVER_PORT));
            // ѭ�������¼�
            while (true){
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
            }
        } catch (IOException e) {
            System.out.println("���ӷ�����ʧ��,��鿴�����Ƿ�����");
        }
    }
    /**
     * �����¼�
     */
    private void handler(SelectionKey key){
        // ����������¼�׼������
        if (key.isConnectable()){
            try {
                // �õ�ͨ��
                SocketChannel client = (SocketChannel) key.channel();
                // �жϿͻ����Ƿ���������
                if (client.isConnectionPending()){
                    // ����Ѿ������Ϸ���������true,û�������ϻᱨ��
                    client.finishConnect();
                    System.out.println("���ӳɹ�");
                    // ��½
                    login(client,tip);
                    // �������߳�,�����ȡ������Ϣ���͸�������
                    new Thread(new ClientThread(client)).start();
                    // �ı�ѡ������ͨ������Ȥ�¼�Ϊ��
                    client.register(selector,SelectionKey.OP_READ);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
            // ����Ƕ��¼�����(������������Ϣ����)
        }else if (key.isReadable()){
            try {
                // �õ�ͨ��
                SocketChannel client = (SocketChannel) key.channel();
                // ��ն�������
                rbuffer.clear();
                // ��ȡ������
                int count = client.read(rbuffer);
                // �������0��ʾ�ж�����ȡ����
                if (count>0){
                    // ȡ������
//                    String str = String.valueOf(charset.decode(rbuffer));// �������ⱨ��
                    String str = new String(rbuffer.array(),0,count,"GBK");
                    // �����Ϣ�ڰ�����½Э���ַ�
                    if (str.startsWith(ChatRoomProtocol.USER_ROUND) &&
                        str.endsWith(ChatRoomProtocol.USER_ROUND)){
                        // ȥ��ǰ��׺
                        String loginRes = getRealMsg(str);
                        // �������-1���ʾ����
                        if (loginRes.equals(ChatRoomProtocol.NAME_REP)){
                            tip = "�û����ظ�,����������";
                            // ��½
                            login(client,tip);
                            // ����ǵ�½��Ϣ
                        }else if (loginRes.equals(ChatRoomProtocol.LOGIN_SUCCESS)){
                            System.out.println("�ͻ��˵�½�ɹ�");
                        }
                        // ���͵���������Ϣ
                    }else {
                        // �����Ϣ
                        System.out.println(str);
                    }
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    /**
     * ��½
     */
    private void login(SocketChannel client,String tip){
        try {
            // ������ʾ��,��ȡ�����ֵ
            String userName = JOptionPane.showInputDialog(tip+"�������û���:");
            // ���д������
            wbuffer.clear();
            // д�뻺����
            wbuffer.put(charset.encode(ChatRoomProtocol.USER_ROUND+userName+ ChatRoomProtocol.USER_ROUND));
            // ��ת������
            wbuffer.flip();
            // �жϻ������Ƿ�������
            while (wbuffer.hasRemaining()){
                // �����ַ��͵�������
                client.write(wbuffer);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    /**
     * �����ڲ��߳���
     */
    private class ClientThread implements Runnable{
        // �õ��׽���ͨ��
        private SocketChannel client;
        // ������
        public ClientThread(SocketChannel client){
            // ��ʼ������
            this.client = client;
        }

        @Override
        public void run() {
            // ѭ����ȡ���������
            while (true){
                try {
                    // ����ɨ����
                    Scanner scanner = new Scanner(System.in);
                    // �������������
                    String line = scanner.nextLine();
                    /*
                     * �ж���˽����Ϣ���ǹ�����Ϣ
                     * ���͵���Ϣ�����ð��,������//��ͷ,��ʾ˽����Ϣ
                     */
                    if (line.indexOf(":")>0 && line.startsWith("//")){
                        // ȥ��б��
                        line = line.substring(2);
                        // ���д������
                        wbuffer.clear();
                        // д�뻺����
                        wbuffer.put(charset.encode(ChatRoomProtocol.PRIVATEMSG_ROUND+
                                line.split(":")[0]+ChatRoomProtocol.SPLIT_SIGN+
                                line.split(":")[1]+ChatRoomProtocol.PRIVATEMSG_ROUND));
                        // ��תд������
                        wbuffer.flip();
                        // ���������������
                        while (wbuffer.hasRemaining()){
                            // д��ͨ��
                            client.write(wbuffer);
                        }
                        // ������Ϣ
                    }else {
                        // ���д������
                        wbuffer.clear();
                        // д�뻺����
                        wbuffer.put(charset.encode(ChatRoomProtocol.PRIVATEMSG_ROUND+
                                line+ChatRoomProtocol.PRIVATEMSG_ROUND));
                        // ��תд������
                        wbuffer.flip();
                        // ���������������
                        while (wbuffer.hasRemaining()){
                            // д��ͨ��
                            client.write(wbuffer);
                        }
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
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
        // �����ͻ���
        Client client = new Client();
        // ��ʼ���ͻ���
        client.init();
    }
}

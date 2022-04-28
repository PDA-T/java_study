package �������.AIO������;

import �������.NIO������.ChatRoomProtocol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * �ͻ���
 */
public class Client {
    // �������˿�
    private static final int SERVER_PORT = 31000;
    // �ַ�������
    private static final String CHARSET = "UTF-8";
    // �ַ���
    private Charset charset = Charset.forName(CHARSET);
    // ����������ӵ�ͨ��
    AsynchronousSocketChannel clientChannel;
    // ������
    JFrame mainWin = new JFrame("������");
    // �ı���,16��48��
    JTextArea jta = new JTextArea(16,48);
    // �ı���
    JTextField jtf = new JTextField(40);
    // ��ť
    JButton sendBtn = new JButton("����");
    // ��½����ʾ
    String tip = "";
    // д������
    ByteBuffer wbuffer = ByteBuffer.allocate(1024);
    // ��������
    ByteBuffer rbuffer = ByteBuffer.allocate(1024);
    // ��ʼ���ͻ���
    public void init(){
        // ���ò���Ϊ���Բ���
        mainWin.setLayout(new BorderLayout());
        // �ر��ı���ı༭Ȩ��
        jta.setEditable(false);
        // ���ı�����ӹ�����,���ж���
        mainWin.add(new JScrollPane(jta),BorderLayout.CENTER);
        // ����һ������,���ı���Ͱ�ť
        JPanel jp = new JPanel();
        // �����ı���
        jp.add(jtf);
        // ���밴ť
        jp.add(sendBtn);
        // ��ť�����¼�
        Action sendAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ��ȡ�ı���д�������
                String content = jtf.getText();
                // �ж��ı���ǰ��ո�ȥ��֮�����0(˵��д������)
                if (content.trim().length()>0){
                    // ȥ���ո�
                    content = content.trim();
                    try {
                        /*
                         * �ж���˽����Ϣ���ǹ�����Ϣ
                         * ���͵���Ϣ�����ð��,������//��ͷ,��ʾ˽����Ϣ
                         */
                        if (content.indexOf(":")>0 && content.startsWith("//")){
                            // ȥ��б��
                            content = content.substring(2);
                            // ���д������
                            wbuffer.clear();
                            // д�뻺����
                            wbuffer.put(charset.encode(ChatRoomProtocol.PRIVATEMSG_ROUND+
                                    content.split(":")[0]+ChatRoomProtocol.SPLIT_SIGN+
                                    content.split(":")[1]+ChatRoomProtocol.PRIVATEMSG_ROUND));
                            // ��תд������
                            wbuffer.flip();
                            // д��ͨ��
                            clientChannel.write(wbuffer).get();
                            // ������Ϣ
                        }else {
                            // ���д������
                            wbuffer.clear();
                            // д�뻺����
                            wbuffer.put(charset.encode(ChatRoomProtocol.PRIVATEMSG_ROUND+
                                    content+ChatRoomProtocol.PRIVATEMSG_ROUND));
                            // ��תд������
                            wbuffer.flip();
                            // д��ͨ��
                            clientChannel.write(wbuffer).get();
                        }
                    }catch (Exception ex){
                        System.out.println("���ͳ���");
                    }
                }
                // ����ı�������
                jtf.setText("");
            }
        };
        // ��Ӱ�ť�¼�
        sendBtn.addActionListener(sendAction);
        // ��ӿ�ݼ��س�
        jtf.getInputMap().put(KeyStroke.getKeyStroke('\n'),"send");
        // ���¼�
        jtf.getActionMap().put("send",sendAction);
        // ����������������,�����������ϱ�(�±�)
        mainWin.add(jp,BorderLayout.SOUTH);
        // ���õ���ر�
        mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // �Զ����������С���������
        mainWin.pack();
        // �������
        mainWin.setLocationRelativeTo(null);
        // ���ô���ɼ�
        mainWin.setVisible(true);
    }
    // ���ӷ�����
    public void connect(){
        try {
            // �̳߳�
            ExecutorService executorService =
                    Executors.newFixedThreadPool(80);
            // ͨ����
            AsynchronousChannelGroup channelGroup =
                    AsynchronousChannelGroup.withThreadPool(executorService);
            // ��ȡ�ͻ����׽���ͨ��
            clientChannel = AsynchronousSocketChannel.open(channelGroup);
            // ���ӷ�����,������������Ϊֹ
            clientChannel.connect(new InetSocketAddress("127.0.0.1",SERVER_PORT)).get();
            System.out.println("���ӳɹ�");
            // ��½
            login(clientChannel,tip);
            // ��ն�������
            rbuffer.clear();
            // ��ȡ������
            clientChannel.read(rbuffer, null, new CompletionHandler<Integer, Object>() {
                @Override
                public void completed(Integer result, Object attachment) {
                    // ��ת������
                    rbuffer.flip();
                    // �õ�������������
                    String content = charset.decode(rbuffer).toString();
                    // �����Ϣ�ڰ�����½Э���ַ�
                    if (content.startsWith(ChatRoomProtocol.USER_ROUND) &&
                            content.endsWith(ChatRoomProtocol.USER_ROUND)){
                        // ȥ��ǰ��׺
                        String loginRes = getRealMsg(content);
                        // �������-1���ʾ����
                        if (loginRes.equals(ChatRoomProtocol.NAME_REP)){
                            tip = "�û����ظ�,����������";
                            // ��½
                            login(clientChannel,tip);
                            // ����ǵ�½��Ϣ
                        }else if (loginRes.equals(ChatRoomProtocol.LOGIN_SUCCESS)){
                            System.out.println("�ͻ��˵�½�ɹ�");
                        }
                        // ���͵���������Ϣ
                    }else {
                        // �����Ϣ
                        jta.append(content + "\n");
                    }
                    // ��ն�������
                    rbuffer.clear();
                    // �ݹ�,���¶�ȡ
                    clientChannel.read(rbuffer,null,this);
                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    System.out.println("��ȡʧ��:" + exc);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // ��½����
    private void login(AsynchronousSocketChannel client,String tip){
        try {
            // ������ʾ��,��ȡ�����ֵ
            String userName = JOptionPane.showInputDialog(tip+"�������û���:");
            // ����������û���
            mainWin.setTitle("������:" + userName);
            // ���д������
            wbuffer.clear();
            // д�뻺����
            wbuffer.put(charset.encode(ChatRoomProtocol.USER_ROUND+userName+ChatRoomProtocol.USER_ROUND));
            // ��ת������
            wbuffer.flip();
            // �����ַ��͵�������
            client.write(wbuffer).get();
        }catch (Exception e){
            e.printStackTrace();
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
        // ��ʼ������
        client.init();
        // ���ӷ�����
        client.connect();
    }
}

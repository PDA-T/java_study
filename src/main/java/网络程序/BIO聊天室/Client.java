package �������.BIO������;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * �ͻ���
 */
public class Client {
    // ���ӷ������˿ں�
    private static final int SERVER_PORT = 40000;
    // ��ӡ��
    private PrintStream ps = null;
    // ������������
    private BufferedReader inServer = null;
    // ����������
    private BufferedReader inKey = null;
    // ���ӷ��������׽���
    private Socket socket = null;

    /**
     * ��ʼ������
     * ���ӷ�����
     */
    public void init(){
        try {
            // ��ʼ������������
            inKey = new BufferedReader(new InputStreamReader(System.in,"GBK"));
            // ���ӵ�������
            socket = new Socket("127.0.0.1",SERVER_PORT);
            // ��ȡ��Ӧ�������
            ps = new PrintStream(socket.getOutputStream(),true,"GBK");
            // ��ȡ��Ӧ��������
            inServer = new BufferedReader(new InputStreamReader(socket.getInputStream(),"GBK"));
            // ��ʾ��ǩ
            String tip = "";
            // ѭ����������½
            while (true){
                // ������ʾ��,��ȡ�����ֵ
                String userName = JOptionPane.showInputDialog(tip+"�������û���:");
                // ���û������͸�������(�����û���Э���ַ�)
                ps.println(ChatRoomProtocol.USER_ROUND+userName+ChatRoomProtocol.USER_ROUND);
                // ��ȡ��������Ӧ��ֵ�Ƿ���-1
                String res = inServer.readLine();
                // �������-1
                if (res.equals(ChatRoomProtocol.NAME_REP)){
                    // �����ظ�
                    tip = "�û����ظ�,";
                    // ������ǰѭ��
                    continue;
                }
                // �������1
                if (res.equals(ChatRoomProtocol.LOGIN_SUCCESS)){
                    // ����ѭ��
                    break;
                }
            }
        }catch (UnknownHostException e){
            // ������û������
            System.out.println("�޷��ҵ�������,��鿴�������Ƿ�����");
            // �ر���Դ
            closeRes();
            // �˳�ϵͳ
            System.exit(1);
        }catch (IOException e){
            // �����쳣
            System.out.println("��鿴�Ƿ���������");
            // �ر���Դ
            closeRes();
            // �˳�ϵͳ
            System.exit(1);
        }
        // �����߳�,��ȡ����������Ӧ��Ϣ
        new Thread(new ClientThread(inServer)).start();
    }

    /**
     * �ͻ��˻�ȡ�����ϵ���Ϣ���ҷ��͸�������
     */
    private void readAndSend(){
        try {
            // �������Ϣ
            String line = null;
            // ѭ����ȡ�����ϵ���Ϣ
            while ((line=inKey.readLine())!=null){
                /*
                 * �ж���˽����Ϣ���ǹ�����Ϣ
                 * ���͵���Ϣ�����ð��,������//��ͷ,��ʾ˽����Ϣ
                 */
                if (line.indexOf(":")>0 && line.startsWith("//")){
                    // ȥ��б��
                    line = line.substring(2);
                    // ƴ�ӷ����û�������Ϣ(����˻��Զ����)
                    ps.println(ChatRoomProtocol.PRIVATEMSG_ROUND+line.split(":")[0]+
                            ChatRoomProtocol.SPLIT_SIGN+line.split(":")[1]+
                            ChatRoomProtocol.PRIVATEMSG_ROUND);
                }else {
                    // ���͹�����Ϣ
                    ps.println(ChatRoomProtocol.PUBLICMSG_ROUND+line+ChatRoomProtocol.PUBLICMSG_ROUND);
                }
            }
        }catch (IOException e){
            // �����쳣
            System.out.println("����ͨ���쳣,��������Ƿ�����");
            // �ر���Դ
            closeRes();
            // �˳�ϵͳ
            System.exit(1);
        }
    }

    /**
     * �ر���Դ
     */
    private void closeRes(){
        try {
            if (inKey!=null){
                inKey.close();
            }
            if (inServer!=null){
                inServer.close();
            }
            if(ps!=null){
                ps.close();
            }
            if (socket!=null){
                socket.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // �����ͻ�����
        Client client = new Client();
        // ��ʼ�����ӷ����
        client.init();
        // ����������Ϣ
        client.readAndSend();
    }
}

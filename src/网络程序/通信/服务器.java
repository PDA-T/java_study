package �������.ͨ��;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ������ {
    public static void main(String[] args) {
        try {
            ServerSocket server=new ServerSocket(1100);// �����������׽���
            System.out.println("�����ɹ�");// ���
            Socket s=server.accept();// �ȴ��ͻ�������
            System.out.println("���ӳɹ�:"+s.getInetAddress());// ���+��ȡ�ͻ���IP
            InputStream in=s.getInputStream();// ��ȡ������
            byte bt[]=new byte[1024];// ����������
            int len=in.read(bt);// ������
            String data=new String(bt,0,len);// �����ַ���
            System.out.println(data);// ���
            OutputStream out=s.getOutputStream();// ��ȡ�����
            String st="�����˿�";// �����ַ���
            out.write(st.getBytes());// �����
            server.close();// �ر�
            System.out.println("�����ж�");// ���
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

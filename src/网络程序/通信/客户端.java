package �������.ͨ��;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class �ͻ��� {
    public static void main(String[] args) {
        try {
            Socket cli=new Socket("127.0.0.1",1100);// �����׽���
            System.out.println("���ӳɹ�");// ���
            OutputStream out=cli.getOutputStream();// ��ȡ�ͻ����׽��������
            String z="�ͻ��˽���";// �����ַ���
            out.write(z.getBytes());// �����,ת��Ϊ�ֽ�
            InputStream in=cli.getInputStream();// ��ȡ������
            byte bt[]=new byte[1024];// ����������
            int i=in.read(bt);// ������
            String st=new String(bt,0,i);// ��ȡ�ַ���
            System.out.println(st);// ���
            cli.close();// �ر�
            System.out.println("�����ж�");// ���
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

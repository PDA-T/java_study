package �������.ͨ��;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * �ͻ���
 */
public class ClientTest {
    public static void main(String[] args) throws IOException {
        // �����ͻ����׽���
        Socket socket = new Socket();
        // ����
        socket.connect(new InetSocketAddress("127.0.0.1",40000));
        // ��ȡ�����
        PrintStream ps = new PrintStream(socket.getOutputStream(),true,"GBK");
        // ��������
        ps.println("�ͻ���");
        // ��ȡ������
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(),"GBK"));
        // ��������
        String str = in.readLine();
        // ���
        System.out.println("������:"+str);
        // �ر���
        in.close();
        ps.close();
        socket.close();
    }
}

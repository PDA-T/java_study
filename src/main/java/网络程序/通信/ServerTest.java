package �������.ͨ��;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ����˺Ϳͻ��˵ļ�ͨ��
 */
public class ServerTest {
    public static void main(String[] args) throws IOException {
        // �����������׽���
        ServerSocket serverSocket = new ServerSocket();
        // ��IP�Ͷ˿�
        serverSocket.bind(new InetSocketAddress("127.0.0.1",40000));
        // ѭ�����տͻ��˵�����(����)
        while (true){
            // ���տͻ�������(��ȡ���ӽ����Ŀͻ����׽���),������
            Socket clientSocket = serverSocket.accept();
            // ͨ���ͻ����׽��ֶ�Ӧ������������������
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(),"GBK"));
            // ͨ����������ȡ�ͻ��˷�������Ϣ
            String str = in.readLine();
            System.out.println("�ͻ���:"+str);

            // ������ӡ��
            PrintStream ps = new PrintStream(clientSocket.getOutputStream(),true,"GBK");
            // ������Ϣ
            ps.println("������");
            // �ر���
            ps.close();
            in.close();
            clientSocket.close();
        }
    }
}

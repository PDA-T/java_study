package �������.������;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

/**
 * ������׽���
 */
public class ServerSocketTest {
    public static void main(String[] args) {
        try {
            // Ĭ�Ϲ�����
            ServerSocket serverSocket = new ServerSocket();
            // �󶨶˿ڹ�����
            ServerSocket serverSocket2 = new ServerSocket(5000);
            // �󶨶˿ں��жӴ�С,�������Ŀͻ��˶��ж�С��ܾ�����
            ServerSocket serverSocket3 = new ServerSocket(5000,60);
            // �󶨵�IP������
            ServerSocket serverSocket4 = new ServerSocket(5000,60, InetAddress.getByName("127.0.0.1"));
            // ���׽�������IP�Ͷ˿�
            serverSocket.bind(new InetSocketAddress("127.0.0.1",5000));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

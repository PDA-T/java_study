package �������.BIO������;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ����˺Ϳͻ��˵ļ�ͨ��
 */
public class Server {
    // �����������˿�
    public static final int SERVER_PORT = 40000;
    // �������������������û����Ͷ�Ӧ���׽��������
    public static ChatRoomMap<String, PrintStream> clients = new ChatRoomMap<>();

    /**
     * ��װ��ip��ַ�Ͷ˿ڵ���������������
     */
    public void init() {
        try {
            // �����������׽���
            ServerSocket serverSocket = new ServerSocket();
            // ��IP�Ͷ˿�
            serverSocket.bind(new InetSocketAddress("127.0.0.1", SERVER_PORT));
            // ѭ�����տͻ��˵�����(����)
            while (true) {
                // ���տͻ�������(��ȡ���ӽ����Ŀͻ����׽���),������
                Socket clientSocket = serverSocket.accept();
                // �����߳�
                new Thread(new ServerThread(clientSocket)).start();
            }
        }catch (Exception e){
            System.out.println("����������ʧ��,����˿�,�˿ں�:"+SERVER_PORT);
        }
    }

    public static void main(String[] args) {
        // ��������������
        Server server = new Server();
        // ����������
        server.init();
    }
}

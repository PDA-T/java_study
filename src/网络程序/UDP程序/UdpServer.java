package �������.UDP����;

import java.io.IOException;
import java.net.*;

/**
 * �����
 */
public class UdpServer {
    public static void main(String[] args) {
        try {
            // �����׽���,������Ҫ��IP�Ͷ˿�
            DatagramSocket socket = new DatagramSocket(new InetSocketAddress("127.0.0.1",30000));
            // ���յ�����
            byte[] inbuffer = new byte[4096];
            // ���յ����ݰ�
            DatagramPacket inPacket = new DatagramPacket(inbuffer,inbuffer.length);
            // ���͵����ݰ�
            DatagramPacket outPacket;
            // ���͵���������
            String content = "�������յ�";
            // ����ͻ���û�йر�
            while (socket.isClosed() == false){
                // ��������
                socket.receive(inPacket);
                // ����յ�������
                System.out.println(new String(inbuffer,0,inPacket.getLength()));
                // �õ������ߵ�ַ
                SocketAddress clientAddr = inPacket.getSocketAddress();
                // Ҫ���͵�����
                byte[] sendData = content.getBytes();
                // �������ݰ�
                outPacket = new DatagramPacket(sendData,sendData.length,clientAddr);
                // ����
                socket.send(outPacket);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

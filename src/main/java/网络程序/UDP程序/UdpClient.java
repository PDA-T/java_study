package �������.UDP����;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Scanner;

/**
 * �ͻ���
 */
public class UdpClient {
    public static void main(String[] args) {
        try {
            // UDP�׽���
            DatagramSocket socket = new DatagramSocket();
            // ���ݰ���󳤶�
            int maxleng = 4096;
            // Ҫ���͵�����
            byte[] inbuffer = new byte[maxleng];
            // �������ݵ����ݰ�(�������ݲ���Ҫ��ַ)
            DatagramPacket inPacket = new DatagramPacket(inbuffer,maxleng);
            // �����ݵ����ݰ�
            DatagramPacket outPacket = new DatagramPacket(new byte[0],0,
                    new InetSocketAddress("127.0.0.1",30000));
            // ɨ����
            Scanner sc = new Scanner(System.in);
            // ѭ����ȡ����
            while (sc.hasNextLine()){
                // ��ȡ���ַ���
                byte[] datas = sc.nextLine().getBytes();
                // �����ݷ������ݰ�
                outPacket.setData(datas);
                // ����
                socket.send(outPacket);
                // �Ѵӷ������յ������ݷ���inPacket
                socket.receive(inPacket);
                // ����������ظ�����Ϣ
                System.out.println("���������͵���Ϣ:" +
                        new String(inbuffer,0,inPacket.getLength()));
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

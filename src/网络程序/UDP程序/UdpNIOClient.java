package �������.UDP����;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * ����UDP��NIO�ͻ���
 * UDPû��AIO
 */
public class UdpNIOClient {
    public static void main(String[] args) {
        // �����ݺ������ݵĻ�����
        ByteBuffer inbuffer = ByteBuffer.allocate(1024);
        ByteBuffer outbuffer = ByteBuffer.allocate(1024);
        try {
            // ɨ����
            Scanner scanner = new Scanner(System.in);
            // ���ݰ�ͨ��
            DatagramChannel datagramChannel = DatagramChannel.open();
            // ���÷�����
            datagramChannel.configureBlocking(false);
            // ѭ���ж�ɨ�����Ƿ�������
            while (scanner.hasNextLine()){
                // ��ȡɨ��������
                String content = scanner.nextLine();
                // ��ջ�����
                outbuffer.clear();
                // ������д�뻺����
                outbuffer.put(content.getBytes("UTF-8"));
                // ��ת������
                outbuffer.flip();
                // �������ݰ�
                datagramChannel.send(outbuffer,new InetSocketAddress("127.0.0.1",40000));
                // ��ն�������
                inbuffer.clear();
                // �Ѵӷ������յ������ݷ���inPacket
                datagramChannel.receive(inbuffer);
                // ��ת��������
                inbuffer.flip();
                // �������������
                System.out.println("��������������Ϣ:"+
                        StandardCharsets.UTF_8.decode(inbuffer).toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

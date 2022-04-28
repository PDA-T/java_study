package ������.�߼�������.ѡ����;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * �ۺ���ϰ
 * �ͻ��˳���
 */
public class ClientDemo {
    public static void main(String[] args) {
        try {
            // �õ��׽���ͨ��
            SocketChannel channel = SocketChannel.open();
            // ���ӷ�����
            channel.connect(new InetSocketAddress("127.0.0.1",8000));
            // ������������
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            // ����д������
            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
            // ��д�������ڷ����ַ���
            writeBuffer.put("�ͻ���".getBytes());
            // ��תд������
            writeBuffer.flip();
            // ����ѭ��
            while (true){
                // ����д��������λ��ָ��
                writeBuffer.rewind();
                // ������д������
                channel.write(writeBuffer);
                // ��ն�������
                readBuffer.clear();
                // ��ͨ�������ݷ��뻺����
                channel.read(readBuffer);
                // ��ת������
                readBuffer.flip();
                // �������˵�����
                System.out.println("�ͻ����յ�������:" + new String(readBuffer.array()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

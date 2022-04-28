package ������.�߼�������.ѡ����;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * �ۺ���ϰ
 * ����˳���
 */
public class ServerDemo {
    public static void main(String[] args) {
        try {
            // ��ȡ�������׽���ͨ������
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            // ����IP��ַ�Ͷ˿�
            serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1",8000));
            // ���÷�����ģʽ
            serverSocketChannel.configureBlocking(false);
            // ��ȡѡ��������
            Selector selector = Selector.open();
            // ע���ѡ����,��ȤΪ�����¼�׼������
            serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
            // ������������
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            // ����д������
            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
            // ��д�������ڷ����ַ���
            writeBuffer.put("Google".getBytes());
            // ��תд������
            writeBuffer.flip();
            // ����ѭ��
            while (true){
                // ���ע���ͨ�����¼�,�鿴�ĸ��¼��Ѿ�׼������,����ʱ������1��
                int nReady = selector.select(1000);
                // �������Ϊ0,˵��ǰһ����ʱ�����û��ͨ��׼������
                if (nReady == 0){
                    // ���½���ѭ��
                    continue;
                }
                // �ó�׼��������ͨ��
                Set<SelectionKey> keys = selector.selectedKeys();
                // �õ�������
                Iterator<SelectionKey> it = keys.iterator();
                // ͨ���������жϼ����Ƿ�������
                while (it.hasNext()){
                    // �ó�����,SelectionKey�з�װ��ѡ����,ͨ���ȸ�����Ϣ
                    SelectionKey key = it.next();
                    // �ֶ��Ƴ�,�����ͬһ��ͨ���ظ��Ĵ���(�ǳ���Ҫ)
                    it.remove();
                    // �жϷ������׽���ͨ���ǲ���׼�����˽����µ�����
                    if (key.isAcceptable()){
                        // ����������
                        SocketChannel channel = serverSocketChannel.accept();
                        // ����Ϊ������ģʽ
                        channel.configureBlocking(false);
                        // ע���ѡ����,��ȤΪ��
                        channel.register(selector,SelectionKey.OP_READ);
                        // �ж��Ƿ�׼��������ȡ
                    }else if (key.isReadable()){
                        // ��ȡͨ��(һ��ͨ����Ӧһ���ͻ���)
                        SocketChannel channel = (SocketChannel) key.channel();
                        // ��ջ�����
                        readBuffer.clear();
                        // ��ͨ��������ݶ���������
                        channel.read(readBuffer);
                        // ��ת������
                        readBuffer.flip();
                        // �������������
                        System.out.println("���������յ�������:" + new String(readBuffer.array()));
                        // ��ͨ����Ȥ��Ϊд
                        key.interestOps(SelectionKey.OP_WRITE);
                        // �ж�ͨ���Ƿ�Ϊд����
                    }else if (key.isWritable()){
                        // ��key���õ�ͨ��
                        SocketChannel channel = (SocketChannel) key.channel();
                        // ��ԭд��������λ��ָ��
                        writeBuffer.rewind();
                        // ��д�������е�����д��ͨ��
                        channel.write(writeBuffer);
                        // ��ͨ����Ȥ��Ϊ��
                        key.interestOps(SelectionKey.OP_READ);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

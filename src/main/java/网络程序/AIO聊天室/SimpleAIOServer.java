package �������.AIO������;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * AIO�����
 */
public class SimpleAIOServer {
    public static void main(String[] args) {
        // �õ��첽�׽��ַ����ͨ��
        try(AsynchronousServerSocketChannel serverSocketChannel
                    = AsynchronousServerSocketChannel.open();){
            // ��IP��ַ�Ͷ˿ں�
            serverSocketChannel.bind(new InetSocketAddress("127.0.0.1",6500));
            // ѭ�����Ͻ��տͻ��˵���Ϣ
            while (true){
                // ���տͻ�������
                Future<AsynchronousSocketChannel> future = serverSocketChannel.accept();
                // ѭ������,�ȴ��ͻ��˽���
                while (!future.isDone());
                // �ڶ�����������,�᷵�ش���ͻ��˵��׽���ͨ��
                AsynchronousSocketChannel socketChannel = future.get();
                // д������
                ByteBuffer wbuffer = ByteBuffer.allocate(1024);
                // ���д������
                wbuffer.clear();
                // д������
                wbuffer.put("������".getBytes(StandardCharsets.UTF_8));
                // ��ת������
                wbuffer.flip();
                // ���д��������������
                while (wbuffer.hasRemaining()){
                    // д��ͨ��
                    socketChannel.write(wbuffer);
                }
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

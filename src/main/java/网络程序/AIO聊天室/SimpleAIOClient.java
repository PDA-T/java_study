package �������.AIO������;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * AIO�ͻ���
 */
public class SimpleAIOClient {
    public static void main(String[] args) {
        // �����ͻ����׽���
        try( AsynchronousSocketChannel socketChannel =
                     AsynchronousSocketChannel.open();) {
            // �����������
            ByteBuffer rbuffer = ByteBuffer.allocate(1024);
            // �����ַ���
            Charset charset = Charset.forName("UTF-8");
            // ���ӷ�����
            Future<Void> future = socketChannel.connect(new InetSocketAddress("127.0.0.1", 6500));
            // �������򵽳ɹ�����֮��
            while (future.isDone());
            // �ڶ�����������
            future.get();
            // ��ն�������
            rbuffer.clear();
            // �����ݶ�ȡ��������
            Future<Integer> fu = socketChannel.read(rbuffer);
            // ��������ȡ���
            fu.get();
            // ��ת��������
            rbuffer.flip();
            // ��ȡ����������(GBK�޷�ʹ�����������ȡ)
            String content = String.valueOf(charset.decode(rbuffer));
            // �ڶ��ֻ�ȡ����
            String content2 = charset.decode(rbuffer).toString();
            // ���
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

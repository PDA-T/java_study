package 网络程序.AIO聊天室;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * AIO客户端
 */
public class SimpleAIOClient {
    public static void main(String[] args) {
        // 创建客户端套接字
        try( AsynchronousSocketChannel socketChannel =
                     AsynchronousSocketChannel.open();) {
            // 定义读缓冲区
            ByteBuffer rbuffer = ByteBuffer.allocate(1024);
            // 定义字符集
            Charset charset = Charset.forName("UTF-8");
            // 连接服务器
            Future<Void> future = socketChannel.connect(new InetSocketAddress("127.0.0.1", 6500));
            // 阻塞程序到成功连接之后
            while (future.isDone());
            // 第二种阻塞方法
            future.get();
            // 清空读缓冲区
            rbuffer.clear();
            // 把数据读取到缓冲区
            Future<Integer> fu = socketChannel.read(rbuffer);
            // 阻塞到读取完成
            fu.get();
            // 反转读缓冲区
            rbuffer.flip();
            // 获取缓冲区数据(GBK无法使用这个方法获取)
            String content = String.valueOf(charset.decode(rbuffer));
            // 第二种获取方法
            String content2 = charset.decode(rbuffer).toString();
            // 输出
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

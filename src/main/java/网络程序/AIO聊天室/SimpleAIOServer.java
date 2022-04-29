package 网络程序.AIO聊天室;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * AIO服务端
 */
public class SimpleAIOServer {
    public static void main(String[] args) {
        // 拿到异步套接字服务端通道
        try(AsynchronousServerSocketChannel serverSocketChannel
                    = AsynchronousServerSocketChannel.open();){
            // 绑定IP地址和端口号
            serverSocketChannel.bind(new InetSocketAddress("127.0.0.1",6500));
            // 循环不断接收客户端的信息
            while (true){
                // 接收客户端连接
                Future<AsynchronousSocketChannel> future = serverSocketChannel.accept();
                // 循环阻塞,等待客户端接入
                while (!future.isDone());
                // 第二种阻塞方法,会返回代表客户端的套接字通道
                AsynchronousSocketChannel socketChannel = future.get();
                // 写缓冲区
                ByteBuffer wbuffer = ByteBuffer.allocate(1024);
                // 清空写缓冲区
                wbuffer.clear();
                // 写入数据
                wbuffer.put("服务器".getBytes(StandardCharsets.UTF_8));
                // 反转缓冲区
                wbuffer.flip();
                // 如果写缓冲区还有数据
                while (wbuffer.hasRemaining()){
                    // 写入通道
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

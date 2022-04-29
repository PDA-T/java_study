package 数据流.高级数据流.选择器;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 综合练习
 * 客户端程序
 */
public class ClientDemo {
    public static void main(String[] args) {
        try {
            // 拿到套接字通道
            SocketChannel channel = SocketChannel.open();
            // 连接服务器
            channel.connect(new InetSocketAddress("127.0.0.1",8000));
            // 创建读缓冲区
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            // 创建写缓冲区
            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
            // 在写缓冲区内放入字符串
            writeBuffer.put("客户端".getBytes());
            // 反转写缓冲区
            writeBuffer.flip();
            // 创建循环
            while (true){
                // 重置写缓冲区的位置指针
                writeBuffer.rewind();
                // 向服务端写入数据
                channel.write(writeBuffer);
                // 清空读缓冲区
                readBuffer.clear();
                // 把通道内数据放入缓冲区
                channel.read(readBuffer);
                // 反转缓冲区
                readBuffer.flip();
                // 输出服务端的数据
                System.out.println("客户端收到的数据:" + new String(readBuffer.array()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

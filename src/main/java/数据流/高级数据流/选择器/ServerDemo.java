package 数据流.高级数据流.选择器;

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
 * 综合练习
 * 服务端程序
 */
public class ServerDemo {
    public static void main(String[] args) {
        try {
            // 获取服务器套接字通道对象
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            // 设置IP地址和端口
            serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1",8000));
            // 设置非阻塞模式
            serverSocketChannel.configureBlocking(false);
            // 获取选择器对象
            Selector selector = Selector.open();
            // 注册进选择器,兴趣为接入事件准备就绪
            serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
            // 创建读缓冲区
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            // 创建写缓冲区
            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
            // 在写缓冲区内放入字符串
            writeBuffer.put("Google".getBytes());
            // 反转写缓冲区
            writeBuffer.flip();
            // 创建循环
            while (true){
                // 检测注册进通道的事件,查看哪个事件已经准备就绪,阻塞时间上限1秒
                int nReady = selector.select(1000);
                // 如果返回为0,说明前一秒中时间段内没有通道准备就绪
                if (nReady == 0){
                    // 重新进行循环
                    continue;
                }
                // 拿出准备就绪的通道
                Set<SelectionKey> keys = selector.selectedKeys();
                // 拿到迭代器
                Iterator<SelectionKey> it = keys.iterator();
                // 通过迭代器判断集合是否有数据
                while (it.hasNext()){
                    // 拿出数据,SelectionKey中封装了选择器,通道等各种信息
                    SelectionKey key = it.next();
                    // 手动移除,避免对同一个通道重复的处理(非常重要)
                    it.remove();
                    // 判断服务器套接字通道是不是准备好了接入新的链接
                    if (key.isAcceptable()){
                        // 接入新链接
                        SocketChannel channel = serverSocketChannel.accept();
                        // 设置为非阻塞模式
                        channel.configureBlocking(false);
                        // 注册进选择器,兴趣为读
                        channel.register(selector,SelectionKey.OP_READ);
                        // 判断是否准备就绪读取
                    }else if (key.isReadable()){
                        // 获取通道(一个通道对应一个客户端)
                        SocketChannel channel = (SocketChannel) key.channel();
                        // 清空缓冲区
                        readBuffer.clear();
                        // 把通道里的数据读到缓冲区
                        channel.read(readBuffer);
                        // 反转缓冲区
                        readBuffer.flip();
                        // 输出缓冲区数据
                        System.out.println("服务器端收到的数据:" + new String(readBuffer.array()));
                        // 把通道兴趣改为写
                        key.interestOps(SelectionKey.OP_WRITE);
                        // 判断通道是否为写就绪
                    }else if (key.isWritable()){
                        // 从key中拿到通道
                        SocketChannel channel = (SocketChannel) key.channel();
                        // 还原写缓冲区的位置指针
                        writeBuffer.rewind();
                        // 把写缓冲区中的数据写入通道
                        channel.write(writeBuffer);
                        // 把通道兴趣改为读
                        key.interestOps(SelectionKey.OP_READ);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

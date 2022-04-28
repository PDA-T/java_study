package 数据流.高级数据流.选择器;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * 选择器
 * 一般在网络编程中使用
 */
public class SelectorTest {
    public static void main(String[] args) throws IOException {
        // 获取服务器套接字通道对象
        ServerSocketChannel channel = ServerSocketChannel.open();
        // 获取选择器实例对象
        Selector selector = Selector.open();
        // 在选择器注册通道之前,必须设置通道为非阻塞模式
        channel.configureBlocking(false);
        // 把通道注册到选择器,OP_ACCEPT(接收就绪)表示准备接收新的连接(兴趣集合,监听对什么事感兴趣)
        channel.register(selector, SelectionKey.OP_ACCEPT);
    }
}

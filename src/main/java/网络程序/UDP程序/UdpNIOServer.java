package 网络程序.UDP程序;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * 基于UDP的NIO服务端
 * UDP没有AIO
 */
public class UdpNIOServer {
    // 发数据和收数据的缓冲区
    private ByteBuffer inbuffer = ByteBuffer.allocate(1024);
    private ByteBuffer outbuffer = ByteBuffer.allocate(1024);

    /**
     * 初始化方法
     */
    public void init(){
        try {
            // 获取数据包通道
            DatagramChannel datagramChannel = DatagramChannel.open();
            // 非阻塞模式
            datagramChannel.configureBlocking(false);
            // 绑定固定IP和端口(接收数据需要绑定)
            datagramChannel.bind(new InetSocketAddress("127.0.0.1",40000));
            // 选择器
            Selector selector = Selector.open();
            // 把通道注册到选择器,兴趣为读
            datagramChannel.register(selector,SelectionKey.OP_READ);
            // 循环接收发送数据
            while (true){
                // 设置连接,超时5秒
                int count = selector.select(5000);
                // 如果等于0说明没有客户端连接
                if (count == 0){
                    // 重新循环
                    continue;
                }
                // 迭代器
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                // 判断迭代器有没有数据
                while (it.hasNext()){
                    // 取出数据
                    SelectionKey key = it.next();
                    // 是否读准备就绪
                    if(key.isReadable()){
                        // 读取
                        handlerRead(key);
                    }
                    // 移除处理过的数据
                    selector.selectedKeys().remove(key);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 处理读数据
     */
    public void handlerRead(SelectionKey key){
        try {
            // 拿到通道
            DatagramChannel datagramChannel = (DatagramChannel) key.channel();
            // 清空缓冲区
            inbuffer.clear();
            // 把数据写入到缓冲区返回一个IP地址
            InetSocketAddress sendAddr = (InetSocketAddress) datagramChannel.receive(inbuffer);
            // 反转缓冲区
            inbuffer.flip();
            // 输出收到的信息
            System.out.println("客户端信息:"+
                    StandardCharsets.UTF_8.decode(inbuffer).toString());
            // 回复信息
            String content = "服务器收到";
            // 清空缓冲区
            outbuffer.clear();
            // 写入缓冲区
            outbuffer.put(content.getBytes("UTF-8"));
            // 反转缓冲区
            outbuffer.flip();
            // 发送数据
            datagramChannel.send(outbuffer,
                    new InetSocketAddress(sendAddr.getHostName(),sendAddr.getPort()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        // 创建服务端类
        UdpNIOServer server = new UdpNIOServer();
        // 初始化服务端
        server.init();
    }
}

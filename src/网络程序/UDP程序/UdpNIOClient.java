package 网络程序.UDP程序;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 基于UDP的NIO客户端
 * UDP没有AIO
 */
public class UdpNIOClient {
    public static void main(String[] args) {
        // 发数据和收数据的缓冲区
        ByteBuffer inbuffer = ByteBuffer.allocate(1024);
        ByteBuffer outbuffer = ByteBuffer.allocate(1024);
        try {
            // 扫描器
            Scanner scanner = new Scanner(System.in);
            // 数据包通道
            DatagramChannel datagramChannel = DatagramChannel.open();
            // 设置非阻塞
            datagramChannel.configureBlocking(false);
            // 循环判断扫描器是否有数据
            while (scanner.hasNextLine()){
                // 获取扫描器数据
                String content = scanner.nextLine();
                // 清空缓冲区
                outbuffer.clear();
                // 把数据写入缓冲区
                outbuffer.put(content.getBytes("UTF-8"));
                // 反转缓冲区
                outbuffer.flip();
                // 发送数据包
                datagramChannel.send(outbuffer,new InetSocketAddress("127.0.0.1",40000));
                // 清空读缓冲区
                inbuffer.clear();
                // 把从服务器收到的数据放入inPacket
                datagramChannel.receive(inbuffer);
                // 反转读缓冲区
                inbuffer.flip();
                // 输出发来的数据
                System.out.println("服务器发来的信息:"+
                        StandardCharsets.UTF_8.decode(inbuffer).toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

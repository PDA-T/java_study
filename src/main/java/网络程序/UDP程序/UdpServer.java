package 网络程序.UDP程序;

import java.io.IOException;
import java.net.*;

/**
 * 服务端
 */
public class UdpServer {
    public static void main(String[] args) {
        try {
            // 创建套接字,接收需要绑定IP和端口
            DatagramSocket socket = new DatagramSocket(new InetSocketAddress("127.0.0.1",30000));
            // 接收的数据
            byte[] inbuffer = new byte[4096];
            // 接收的数据包
            DatagramPacket inPacket = new DatagramPacket(inbuffer,inbuffer.length);
            // 发送的数据包
            DatagramPacket outPacket;
            // 发送的数据内容
            String content = "服务器收到";
            // 如果客户端没有关闭
            while (socket.isClosed() == false){
                // 接收数据
                socket.receive(inPacket);
                // 输出收到的数据
                System.out.println(new String(inbuffer,0,inPacket.getLength()));
                // 拿到发送者地址
                SocketAddress clientAddr = inPacket.getSocketAddress();
                // 要发送的数据
                byte[] sendData = content.getBytes();
                // 放入数据包
                outPacket = new DatagramPacket(sendData,sendData.length,clientAddr);
                // 发送
                socket.send(outPacket);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

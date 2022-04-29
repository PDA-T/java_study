package 网络程序.UDP程序;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Scanner;

/**
 * 客户端
 */
public class UdpClient {
    public static void main(String[] args) {
        try {
            // UDP套接字
            DatagramSocket socket = new DatagramSocket();
            // 数据包最大长度
            int maxleng = 4096;
            // 要发送的数据
            byte[] inbuffer = new byte[maxleng];
            // 接收数据的数据包(接收数据不需要地址)
            DatagramPacket inPacket = new DatagramPacket(inbuffer,maxleng);
            // 发数据的数据包
            DatagramPacket outPacket = new DatagramPacket(new byte[0],0,
                    new InetSocketAddress("127.0.0.1",30000));
            // 扫描器
            Scanner sc = new Scanner(System.in);
            // 循环获取内容
            while (sc.hasNextLine()){
                // 获取到字符串
                byte[] datas = sc.nextLine().getBytes();
                // 把数据放入数据包
                outPacket.setData(datas);
                // 发送
                socket.send(outPacket);
                // 把从服务器收到的数据放入inPacket
                socket.receive(inPacket);
                // 输出服务器回复的信息
                System.out.println("服务器发送的信息:" +
                        new String(inbuffer,0,inPacket.getLength()));
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

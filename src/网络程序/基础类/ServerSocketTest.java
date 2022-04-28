package 网络程序.基础类;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

/**
 * 服务端套接字
 */
public class ServerSocketTest {
    public static void main(String[] args) {
        try {
            // 默认构造器
            ServerSocket serverSocket = new ServerSocket();
            // 绑定端口构造器
            ServerSocket serverSocket2 = new ServerSocket(5000);
            // 绑定端口和列队大小,如果请求的客户端多列队小会拒绝请求
            ServerSocket serverSocket3 = new ServerSocket(5000,60);
            // 绑定的IP构造器
            ServerSocket serverSocket4 = new ServerSocket(5000,60, InetAddress.getByName("127.0.0.1"));
            // 给套接字设置IP和端口
            serverSocket.bind(new InetSocketAddress("127.0.0.1",5000));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

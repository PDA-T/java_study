package 网络程序.BIO聊天室;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 服务端和客户端的简单通信
 */
public class Server {
    // 服务器监听端口
    public static final int SERVER_PORT = 40000;
    // 创建数据类型来保存用户名和对应的套接字输出流
    public static ChatRoomMap<String, PrintStream> clients = new ChatRoomMap<>();

    /**
     * 封装绑定ip地址和端口的启动服务器代码
     */
    public void init() {
        try {
            // 创建服务器套接字
            ServerSocket serverSocket = new ServerSocket();
            // 绑定IP和端口
            serverSocket.bind(new InetSocketAddress("127.0.0.1", SERVER_PORT));
            // 循环接收客户端的连接(监听)
            while (true) {
                // 接收客户端请求(获取连接进来的客户端套接字),会阻塞
                Socket clientSocket = serverSocket.accept();
                // 创建线程
                new Thread(new ServerThread(clientSocket)).start();
            }
        }catch (Exception e){
            System.out.println("服务器启动失败,请检查端口,端口号:"+SERVER_PORT);
        }
    }

    public static void main(String[] args) {
        // 创建服务器对象
        Server server = new Server();
        // 启动服务器
        server.init();
    }
}

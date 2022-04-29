package 网络程序.通信;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端和客户端的简单通信
 */
public class ServerTest {
    public static void main(String[] args) throws IOException {
        // 创建服务器套接字
        ServerSocket serverSocket = new ServerSocket();
        // 绑定IP和端口
        serverSocket.bind(new InetSocketAddress("127.0.0.1",40000));
        // 循环接收客户端的连接(监听)
        while (true){
            // 接收客户端请求(获取连接进来的客户端套接字),会阻塞
            Socket clientSocket = serverSocket.accept();
            // 通过客户端套接字对应的输入流来接受数据
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(),"GBK"));
            // 通过输入流获取客户端发来的信息
            String str = in.readLine();
            System.out.println("客户端:"+str);

            // 创建打印流
            PrintStream ps = new PrintStream(clientSocket.getOutputStream(),true,"GBK");
            // 发送信息
            ps.println("服务器");
            // 关闭流
            ps.close();
            in.close();
            clientSocket.close();
        }
    }
}

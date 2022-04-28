package 网络程序.通信;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 客户端
 */
public class ClientTest {
    public static void main(String[] args) throws IOException {
        // 创建客户端套接字
        Socket socket = new Socket();
        // 连接
        socket.connect(new InetSocketAddress("127.0.0.1",40000));
        // 获取输出流
        PrintStream ps = new PrintStream(socket.getOutputStream(),true,"GBK");
        // 发送数据
        ps.println("客户端");
        // 获取输入流
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(),"GBK"));
        // 接收数据
        String str = in.readLine();
        // 输出
        System.out.println("服务器:"+str);
        // 关闭流
        in.close();
        ps.close();
        socket.close();
    }
}

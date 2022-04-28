package 网络程序.通信;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class 服务器 {
    public static void main(String[] args) {
        try {
            ServerSocket server=new ServerSocket(1100);// 创建服务器套接字
            System.out.println("创建成功");// 输出
            Socket s=server.accept();// 等待客户端连接
            System.out.println("连接成功:"+s.getInetAddress());// 输出+获取客户端IP
            InputStream in=s.getInputStream();// 获取输入流
            byte bt[]=new byte[1024];// 创建缓冲区
            int len=in.read(bt);// 输入流
            String data=new String(bt,0,len);// 创建字符串
            System.out.println(data);// 输出
            OutputStream out=s.getOutputStream();// 获取输出流
            String st="开启端口";// 创建字符串
            out.write(st.getBytes());// 输出流
            server.close();// 关闭
            System.out.println("连接中断");// 输出
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

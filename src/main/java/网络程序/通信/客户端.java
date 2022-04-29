package 网络程序.通信;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class 客户端 {
    public static void main(String[] args) {
        try {
            Socket cli=new Socket("127.0.0.1",1100);// 创建套接字
            System.out.println("连接成功");// 输出
            OutputStream out=cli.getOutputStream();// 获取客户端套接字输出流
            String z="客户端接入";// 创建字符串
            out.write(z.getBytes());// 输出流,转换为字节
            InputStream in=cli.getInputStream();// 获取输入流
            byte bt[]=new byte[1024];// 创建缓冲区
            int i=in.read(bt);// 输入流
            String st=new String(bt,0,i);// 获取字符串
            System.out.println(st);// 输出
            cli.close();// 关闭
            System.out.println("连接中断");// 输出
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

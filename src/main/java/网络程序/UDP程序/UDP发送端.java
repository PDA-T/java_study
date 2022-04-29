package 网络程序.UDP程序;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class UDP发送端 extends Thread{
    int port=9000;// 创建端口
    InetAddress group;// 创建广播组地址
    MulticastSocket socket;// 创建多播数据包套接字
    public UDP发送端(){
        try {
            group=InetAddress.getByName("224.255.10.0");// 指定广播组地址
            socket=new MulticastSocket(port);// 实例化多播数据包套接字
            socket.joinGroup(group);// 加入广播组
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        while (true) {
            DatagramPacket packet;// 创建数据包
            String massege = "天气";// 创建字符串
            byte data[] = massege.getBytes();// 发的具体消息
            packet = new DatagramPacket(data, data.length, group, port);// 实例化数据包
            try {
                socket.send(packet);// 发送数据包
                System.out.println(massege);// 输出值
                Thread.sleep(1000);// 休眠
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        UDP发送端 send=new UDP发送端();// 创建类对象
        send.start();// 启动线程
    }
}

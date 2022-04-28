package 网络程序.基础类;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * IP类的各个方法
 */
public class InetAddress类 {
    public static void main(String[] args) {
        try {
            InetAddress local=InetAddress.getLocalHost();// 获取本地主机地址
            InetAddress local2=InetAddress.getByName("www.1lin.xyz");// 获取谷歌主机地址
            System.out.println("本地主机的信息:"+local);// 输出
            // 输出getByName方法内填写的值
            System.out.println("www.1lin.xyz的主机名是:"+local2.getHostName());
            System.out.println("www.1lin.xyz的IP是:"+local2.getHostAddress());// 输出IP地址
            InetAddress local3[]=InetAddress.getAllByName("www.google.com");// 获取谷歌全部地址
            for (InetAddress ad:local3){// 循环遍历
                System.out.println("谷歌全部IP地址:"+ad.getHostAddress());// 输出地址
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}

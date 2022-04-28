package 网络程序.基础类;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 获取网页的详细信息
 */
public class URLConnectionTest {
    public static void main(String[] args) {
        try {
            // 创建URL
            URL url = new URL("http://207.148.72.116");
            // 获取http协议
            System.out.println(url.getProtocol());
            // 获取端口,没有传入端口参数,返回-1
            System.out.println(url.getPort());
            // 获取主机名,获取的是自己写的URL参数
            System.out.println(url.getHost());
            /*
             * URLConnection的主要功能
             * 通过URL实例对象拿到URL连接(连接web服务器使用HttpURLConnection)
             */
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 正式和url对应地址的服务器建立TCP连接
            conn.connect();
            // 向服务器发送GET请求,让服务器把首页的内容响应回来,返回到in当中
            InputStream in = conn.getInputStream();
            // 创建字节数组
            byte[] buffer = new byte[1024];
            // 创建标记
            int hasRead = 0;
            // 循环判断缓冲区是否还有有数据
            while ((hasRead=in.read(buffer))!=-1){
                // 输出in中的信息
                System.out.println(new String(buffer,0,hasRead));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

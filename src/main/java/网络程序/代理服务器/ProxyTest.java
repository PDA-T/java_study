package 网络程序.代理服务器;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * 代理服务器
 */
public class ProxyTest {
    // 代理服务器的IP和端口
    private final String PROXY_ADDR = "202.55.5.209";
    private final int PROXY_PORT = 8090;
    /**
     * 初始化方法
     */
    public void init() throws IOException {
        // 访问的网站
        URL url = new URL("http://www.1lin.xyz");
        // 代理服务器类,使用HTTP协议
        Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress(PROXY_ADDR,PROXY_PORT));
        // 连接代理服务器
        URLConnection conn = url.openConnection(proxy);
        // 拿到数据
        Scanner scan = new Scanner(conn.getInputStream());
        // 如果有数据
        while (scan.hasNextLine()){
            // 输出
            System.out.println(scan.nextLine());
        }
    }

    public static void main(String[] args) throws IOException {
        // 创建代理服务器
        ProxyTest proxy = new ProxyTest();
        // 运行
        proxy.init();
    }
}

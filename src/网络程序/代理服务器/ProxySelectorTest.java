package 网络程序.代理服务器;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 代理服务器选择器
 * 可以绑定多个地址,第一个连接失败自动切换第二个
 */
public class ProxySelectorTest {
    // 代理服务器的IP和端口
    private final String PROXY_ADDR = "202.55.5.209";
    private final int PROXY_PORT = 8090;
    /**
     * 初始化方法
     */
    public void init() throws IOException {
        // 设置默认选择器
        ProxySelector.setDefault(new ProxySelector() {
            @Override
            public List<Proxy> select(URI uri) {
                // 创建列表
                List<Proxy> list = new ArrayList<>();
                // 添加服务器
                list.add(new Proxy(Proxy.Type.HTTP,new InetSocketAddress(PROXY_ADDR,PROXY_PORT)));
                list.add(new Proxy(Proxy.Type.HTTP,new InetSocketAddress("117.41.38.18",9000)));
                list.add(new Proxy(Proxy.Type.HTTP,new InetSocketAddress("139.9.2.31",8081)));
                // 返回集合
                return list;
            }

            @Override
            public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
                System.out.println("所有代理服务器失效");
            }
        });
        // 访问的网站
        URL url = new URL("http://www.1lin.xyz");
        // 连接代理服务器,不需要写后面参数
        URLConnection conn = url.openConnection();
        // 拿到数据
        Scanner scan = new Scanner(conn.getInputStream());
        // 如果有数据
        while (scan.hasNextLine()){
            // 输出
            System.out.println(scan.nextLine());
        }
    }

    public static void main(String[] args) throws IOException {
        // 创建代理服务器选择器
        ProxySelectorTest proxy = new ProxySelectorTest();
        // 运行
        proxy.init();
    }
}

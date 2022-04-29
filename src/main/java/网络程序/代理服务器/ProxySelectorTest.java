package �������.���������;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ���������ѡ����
 * ���԰󶨶����ַ,��һ������ʧ���Զ��л��ڶ���
 */
public class ProxySelectorTest {
    // �����������IP�Ͷ˿�
    private final String PROXY_ADDR = "202.55.5.209";
    private final int PROXY_PORT = 8090;
    /**
     * ��ʼ������
     */
    public void init() throws IOException {
        // ����Ĭ��ѡ����
        ProxySelector.setDefault(new ProxySelector() {
            @Override
            public List<Proxy> select(URI uri) {
                // �����б�
                List<Proxy> list = new ArrayList<>();
                // ��ӷ�����
                list.add(new Proxy(Proxy.Type.HTTP,new InetSocketAddress(PROXY_ADDR,PROXY_PORT)));
                list.add(new Proxy(Proxy.Type.HTTP,new InetSocketAddress("117.41.38.18",9000)));
                list.add(new Proxy(Proxy.Type.HTTP,new InetSocketAddress("139.9.2.31",8081)));
                // ���ؼ���
                return list;
            }

            @Override
            public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
                System.out.println("���д��������ʧЧ");
            }
        });
        // ���ʵ���վ
        URL url = new URL("http://www.1lin.xyz");
        // ���Ӵ��������,����Ҫд�������
        URLConnection conn = url.openConnection();
        // �õ�����
        Scanner scan = new Scanner(conn.getInputStream());
        // ���������
        while (scan.hasNextLine()){
            // ���
            System.out.println(scan.nextLine());
        }
    }

    public static void main(String[] args) throws IOException {
        // �������������ѡ����
        ProxySelectorTest proxy = new ProxySelectorTest();
        // ����
        proxy.init();
    }
}

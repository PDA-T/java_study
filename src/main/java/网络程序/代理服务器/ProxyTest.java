package �������.���������;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * ���������
 */
public class ProxyTest {
    // �����������IP�Ͷ˿�
    private final String PROXY_ADDR = "202.55.5.209";
    private final int PROXY_PORT = 8090;
    /**
     * ��ʼ������
     */
    public void init() throws IOException {
        // ���ʵ���վ
        URL url = new URL("http://www.1lin.xyz");
        // �����������,ʹ��HTTPЭ��
        Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress(PROXY_ADDR,PROXY_PORT));
        // ���Ӵ��������
        URLConnection conn = url.openConnection(proxy);
        // �õ�����
        Scanner scan = new Scanner(conn.getInputStream());
        // ���������
        while (scan.hasNextLine()){
            // ���
            System.out.println(scan.nextLine());
        }
    }

    public static void main(String[] args) throws IOException {
        // �������������
        ProxyTest proxy = new ProxyTest();
        // ����
        proxy.init();
    }
}

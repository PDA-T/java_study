package �������.������;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * ��ȡ��ҳ����ϸ��Ϣ
 */
public class URLConnectionTest {
    public static void main(String[] args) {
        try {
            // ����URL
            URL url = new URL("http://207.148.72.116");
            // ��ȡhttpЭ��
            System.out.println(url.getProtocol());
            // ��ȡ�˿�,û�д���˿ڲ���,����-1
            System.out.println(url.getPort());
            // ��ȡ������,��ȡ�����Լ�д��URL����
            System.out.println(url.getHost());
            /*
             * URLConnection����Ҫ����
             * ͨ��URLʵ�������õ�URL����(����web������ʹ��HttpURLConnection)
             */
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // ��ʽ��url��Ӧ��ַ�ķ���������TCP����
            conn.connect();
            // �����������GET����,�÷���������ҳ��������Ӧ����,���ص�in����
            InputStream in = conn.getInputStream();
            // �����ֽ�����
            byte[] buffer = new byte[1024];
            // �������
            int hasRead = 0;
            // ѭ���жϻ������Ƿ���������
            while ((hasRead=in.read(buffer))!=-1){
                // ���in�е���Ϣ
                System.out.println(new String(buffer,0,hasRead));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

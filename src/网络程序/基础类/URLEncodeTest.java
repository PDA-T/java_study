package �������.������;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * URL�ĺϷ�����ת��
 */
public class URLEncodeTest {
    public static void main(String[] args) {
        try {
            // ���ַ�������
            String urlEncode = URLEncoder.encode("����", "UTF-8");
            // ����������ַ���
            System.out.println(urlEncode);
            // �����������ַ���
            String urlDecode = urlEncode;
            // ���ַ�������
            System.out.println(URLDecoder.decode(urlDecode,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}

package 网络程序.基础类;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * URL的合法编码转换
 */
public class URLEncodeTest {
    public static void main(String[] args) {
        try {
            // 给字符串编码
            String urlEncode = URLEncoder.encode("春节", "UTF-8");
            // 输出编码后的字符串
            System.out.println(urlEncode);
            // 创建编码后的字符串
            String urlDecode = urlEncode;
            // 给字符串解码
            System.out.println(URLDecoder.decode(urlDecode,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}

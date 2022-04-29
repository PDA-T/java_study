package 网络程序.基础类;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 使用Post请求
 */
public class SendPostTest {
    public static void main(String[] args) throws IOException {
        // 创建URL地址
        String urlPath = "http://www.1lin.xyz";
        // 创建给服务器提交的数据(键值对)
        String param = "name" + URLEncoder.encode("abc","UTF-8");
        // 创建URL
        URL url = new URL(urlPath);
        // 拿到链接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 给链接对象设置参数,使用Post请求必须为true
        conn.setDoOutput(true);
        // 给链接对象设置参数,使用Post请求必须为true(此选项默认为true)
        conn.setDoInput(true);
        // 设置禁止浏览器使用缓存
        conn.setUseCaches(false);
        // 设置请求方式
        conn.setRequestMethod("POST");
        // 设置请求头信息,设置字符集
        conn.setRequestProperty("Charset","UTF-8");
        // 设置连接模式,设置响应后不立即中断连接
        conn.setRequestProperty("Connection","Keep-Alive");
        // 设置提交的内容编码格式
        conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

        // 连接(可不写,默认会连接)
        conn.connect();
        // 获取输出流,把数据放到请求体(包装为数据输出流),写入数据依赖的就是与请求体关联的输出流
        DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
        // 给输出流写入参数(把数据放到内存中的缓冲区里)
        dos.writeBytes(param);
        // 刷新缓冲区(真正写入)
        dos.flush();
        // 关闭流
        dos.close();

        // 获取服务器返回回来的状态代码
        int resultCode = conn.getResponseCode();
        // 如果状态值等于200(200为成功状态)
        if (resultCode == HttpURLConnection.HTTP_OK){
            // 把获取的输入流转换为字符流,之后在包装成带缓冲区的字符流
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            // 创建字符串
            String line = null;
            // 如果不等于空(里面有数据)
            while ((line=reader.readLine())!=null){
                // 输出数据
                System.out.println(line);
            }
        }
    }
}

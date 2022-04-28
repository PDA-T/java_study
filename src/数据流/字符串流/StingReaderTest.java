package 数据流.字符串流;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * 字符串输入流
 */
public class StingReaderTest {
    public static void main(String[] args) throws IOException {
        // 创建字符串
        String s = "Google 1";
        // 创建字符串输入流
        StringReader sr = new StringReader(s);
        // 创建字符串输出流
        StringWriter sw = new StringWriter();
        // 创建字符数组
        char[] c = new char[1024];
        // 记录已经读取到的数组的字节个数
        int hasRead = 0;
        // 循环读取
        while ((hasRead = sr.read(c))!=-1){
            // 把输入流的数据放入输出流
            sw.write(c,0,hasRead);
        }
        // 输出字符串
        System.out.println(sw.toString());
    }
}

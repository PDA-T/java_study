package 数据流.字符串流;

import java.io.StringWriter;

/**
 * 字符串输出流
 */
public class StringWriterTest {
    public static void main(String[] args) {
        // 创建字符串
        String s = "Goole";
        // 创建字符串输出流
        StringWriter sw = new StringWriter();
        // 写入字符串
        sw.write(s);
        // 追加字符串
        sw.append("Java");
        // 追加字符串
        sw.append("GitHub");
        // 刷新缓冲区,把输出流的数据传递出去
        sw.flush();
        // 获取输出流的字符串,StringBuffer和String一样
        StringBuffer sb = sw.getBuffer();
        // 和上面方式一样(转换为Sting)
        String s1 = sw.toString();
        // 输出字符串,效果一样
        System.out.println(s1);
        System.out.println(sb.toString());
    }
}

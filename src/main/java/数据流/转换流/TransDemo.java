package 数据流.转换流;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 输出字符流
 * 可对读取到的字符数据经过指定编码转换成字节
 */
public class TransDemo {
    public static void main(String[] args) {
        // 创建字符输入流和字符输出流
        try(InputStreamReader isr = new InputStreamReader(
                new FileInputStream("D:/abc.txt"),"GBK");
            OutputStreamWriter osw = new OutputStreamWriter(
                new FileOutputStream("D:/abc2.txt"),"UTF-8")) {
            // 定义字符数组
            char[] c = new char[1024];
            // 已经读取到的数据的长度
            int hasRead = 0;
            // 循环
            while ((hasRead=isr.read(c))!=-1){
                // 写入
                osw.write(c,0,hasRead);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

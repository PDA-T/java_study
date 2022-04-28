package 数据流.转换流;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 输入字符流
 * 可对读取到的字节数据经过指定编码转换成字符
 */
public class ReaderDemo {
    public static void main(String[] args) throws IOException {
        // 创建文件输入流
        FileInputStream in=new FileInputStream("C:/Users/15811/Desktop/abc.txt");
        // 创建字符输入流,默认项目编码
        InputStreamReader isr=new InputStreamReader(in,"GBK");
//        int c;// 创建读取字节变量
//        while ((c=isr.read())!=-1){// 读取字节变量不是-1开始循环
//            System.out.print((char) c);// 打印字符
//        }
        // 创建字符数组
        char[] buffer=new char[8*1204];
        // 创建读取字节变量
        int c;
        // 读取字节变量不是-1开始循环
        while ((c=isr.read(buffer,0,buffer.length))!=-1){
            // 创建字符串
            String s=new String(buffer,0,c);
            // 打印字符串
            System.out.print(s);
        }
    }
}
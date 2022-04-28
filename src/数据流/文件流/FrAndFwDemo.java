package 数据流.文件流;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FrAndFwDemo {
    public static void main(String[] args) throws IOException {
        FileReader fr=new FileReader("C:/Users/15811/Desktop/abc.txt");// 创建文件字符流
        FileWriter fw=new FileWriter("C:/Users/15811/Desktop/abc.txt");// 创建文件输出流
        char[] buffer=new char[2056];// 创建字符数组
        int c;// 创建读取字节变量
        while ((c=fr.read(buffer,0,buffer.length))!=-1){// 读取字节变量不是-1开始循环
            fw.write(buffer,0,c);// 写入字符
            fw.flush();// 刷新缓冲区
        }
        fr.close();// 关闭文件字符流
        fw.close();// 关闭文件输出流
    }
}

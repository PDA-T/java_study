package 数据流.File类;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件输出流
 */
public class FileOutDemo1 {
    public static void main(String[] args) throws IOException {
        FileOutputStream out=new FileOutputStream("C:/Users/15811/Desktop/abc.txt");// 创建文件输出流
        out.write('A');// 写出A的第八位
        out.write('B');// 写出B的第八位
        int a=10;// 创建变量,write只能写八位,写一个int需要写四次每次八位
        out.write(a>>>24);//写入int
        out.write(a>>>16);//写入int
        out.write(a>>>8);//写入int
        out.write(a);//写入最低8位
    }
}

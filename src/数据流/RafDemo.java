package 数据流;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class RafDemo {
    public static void main(String[] args) throws IOException {
        File demo=new File("C:/Users/15811/Desktop/abc.txt");// 创建文件类
        if (demo.exists()){// 如果文件不存在
            demo.mkdir();// 创建文件
        }
        File file=new File(demo,"C:/Users/15811/Desktop/abc2.txt");// 创建文件类
        if (!file.exists()){// 如果文件不存在
            file.createNewFile();// 如果该文件已经存在,则不创建,返回一个false,如果没有,则返回true
        }
        RandomAccessFile raf=new RandomAccessFile(file,"rw");// 创建随机读写类
        System.out.println(raf.getFilePointer());// 打印指针位置
        raf.write('A');// 只写了一个字节
        System.out.println(raf.getFilePointer());// 打印指针位置
        raf.write('B');// 只写了一个字节
        int i=0x7fffffff;// 创建最大的整数型数int
        // 每次只能写一个字节,如果要吧i写进去就要写4次
//        raf.writeInt(i);// 底层和如下原理一样
        raf.write(i>>>24);// 最高8位
        raf.write(i>>>16);// 写入
        raf.write(i>>>8);// 写入
        raf.write(i);// 写入
        System.out.println(raf.getFilePointer());// 打印指针位置
        String s="中";// 创建字符串
        byte[] gbk=s.getBytes("GBK");// 使用GBK编码解析
        raf.write(gbk);// 写入已经解析的字节码
        System.out.println(raf.length());// 输出长度
        raf.seek(0);// 读取文件需要吧指针归0
        byte[] buf=new byte[(int) raf.length()];// 一次性读取,把文件中的内容都读到字节数组中
        raf.read(buf);// 读取一个字节
        System.out.println(Arrays.toString(buf));// 输出结果
        for (byte b:buf){// 循环遍历
            System.out.println(Integer.toHexString(b&0xff)+" ");// 打印输出16进制
        }
    }
}

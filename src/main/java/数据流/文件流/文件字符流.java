package 数据流.文件流;

import java.io.*;

/**
 * 文件字符流
 * 对于文本的传输使用字符流
 * 使用方式和字节流几乎一样
 */
public class 文件字符流 {
    public static void main(String[] args) {
        File f=new File("C:/Users/15811/Desktop/abc.txt");// 创建文件对象
        FileWriter fr=null;// 创建字符输出流
        try {
            fr=new FileWriter(f,true);// 实例化字符输出流,第二个参数为true则会不会覆盖
            String s="Google";// 创建字符串
            fr.write(s);// 将字符串写入文本文档
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fr!=null){// 判断流是否为空，不为空则关闭
                try {
                    fr.close();// 关闭字符输出流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // 创建字符输入流
        FileReader re=null;
        try {
            re=new FileReader(f);// 实例化字符输入流
            char c[]=new char[1024];// 缓冲区
            int i;// 已读出字符数
            while ((i=re.read(c))!=-1){// 循环读取文件中的数据，直到所有字符都读完，将读取的数据写入数组(缓冲区)
                System.out.println(i);
                System.out.println("文件中的内容为:"+new String(c,0,i));// String可将字符数组转换成对应字符串
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (re!=null){//判断是否为空
                try {
                    re.close();// 关闭字符输入流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

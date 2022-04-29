package 数据流.文件流;

import java.io.*;

/**
 * 文件字节流
 * 对于图片或视频的传输使用字节流
 * 使用方式和字符流几乎一样
 */
public class 文件字节流 {
    public static void main(String[] args) {
        File f=new File("C:/Users/15811/Desktop/abc.txt");// 创建文件对象
        FileOutputStream out=null;// 创建文件输出流
        try {
            // 实例化文件输出流对象,如果后面参数为true则运行后会追加内容，为false则运行后替换内容
            out=new FileOutputStream(f,true);
            String s="Google";// 创建字符串
            byte b[]=s.getBytes();// 将字符串转换为字节数组
            out.write(b);// 将字节数组中的数据写入到文件中
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out!=null){// 判断文件输出流对象是否为空，不是空则关闭
                try {
                    out.close();// 关闭文件输出流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // 文件输入流
        FileInputStream in=null;
        // java7以后可以这样写,如果写在try里,则可以不需要关闭,自动关闭(不需要finally)
        try(FileInputStream in2 = new FileInputStream(f)) {
            in=new FileInputStream(f);// 实例化文件输入流对象
            byte b2[]=new byte[200];// 缓冲区
            /*
             * 将读取的数据写入数组(缓冲区),len为读入缓冲区的总字节数
             * read会返回读取到的字节长度,如果读取完毕会返回0
             */
            int len=in.read(b2);
            // String可将字节数组转换成对应字符串,从0开始取值到len变量
            System.out.println("文件中的数据是:"+new String(b2,0,len));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in!=null){// 判断文件输入流是否为空，不是空则关闭
                try {
                    // IO资源不属于内存的资源,垃圾回收机制无法回收,所以必须关闭
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

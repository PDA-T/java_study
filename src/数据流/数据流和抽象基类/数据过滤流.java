package 数据流.数据流和抽象基类;

import java.io.*;

/**
 * 数据流
 * 允许应用程序将最基本的Java数据类型写到基础输出流,或者从底层读取基本的Java类型
 * 二进制写入,文件打开为乱码
 */
public class 数据过滤流 {
    public static void main(String[] args) {
        File f=new File("C:/Users/15811/Desktop/abc.txt");// 创建文件对象
        FileOutputStream out=null;// 创建文件输出流
        DataOutputStream dos=null;// 创建数据输出流
        FileInputStream in=null;// 创建文件输入流
        DataInputStream di=null;// 创建数据输入流
        try {
            out=new FileOutputStream(f);// 实例化文件输出流
            dos=new DataOutputStream(out);// 实例化数据输出流,将文件流包装成数据流
            in=new FileInputStream(f);// 实例化文件输入流
            di=new DataInputStream(in);// 实例化数据输入流
            dos.writeUTF("写入字符串数据");// 写入字符串数据
            dos.writeInt(123);// 写入整型数据
            dos.writeDouble(3.14);// 写入浮点型数据
            dos.writeBoolean(true);// 写入布尔型型数据
            System.out.println("UTF:"+di.readUTF());// 读取字符串
            System.out.println("Int:"+di.readInt());// 读取整型
            System.out.println("Double:"+di.readDouble());// 读取浮点型
            System.out.println("Boolean:"+di.readBoolean());// 读取布尔型
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dos!=null){// 判断是否为空
                try {
                    dos.close();// 关闭数据输出流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out!=null){// 判断是否为空
                try {
                    out.close();// 关闭文件输出流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(di!=null){// 判断是否为空
                try {
                    di.close();//关闭数据输入流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in!=null){// 判断是否为空
                try {
                    in.close();//关闭文件输入流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

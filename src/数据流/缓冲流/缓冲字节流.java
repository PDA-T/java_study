package 数据流.缓冲流;

import java.io.*;

public class 缓冲字节流 {
    public static void main(String[] args) {
        File f=new File("C:/Users/15811/Desktop/abc.txt");// 创建文件对象
        BufferedInputStream bi=null;// 创建缓冲输入流
        FileOutputStream out=null;// 创建文件输出流
        FileInputStream in=null;// 创建文件输入流
        BufferedOutputStream si=null;// 创建缓冲输出流
        long start=System.currentTimeMillis();// 获得当前毫秒
        try {
            in=new FileInputStream(f);// 实例化文件输入流
            out=new FileOutputStream(f);// 实例化文件输出流
            bi=new BufferedInputStream(in);// 实例化缓冲输入流,将文件字节流包装成缓冲流
            si=new BufferedOutputStream(out);// 实例化缓冲输出流
            String s="Google";// 创建字符串
            byte t[]=s.getBytes();// 将字符串转换为字节数组
            si.write(t);// 写入字节数组
            si.flush();// 刷新,强制将缓冲区数据写入文件,即使缓冲区没有写满
            byte b[]=new byte[1024];// 创建缓冲区字节数组
            while (bi.read(b)!=-1){// 使用缓冲流读取数据
            }
            long end=System.currentTimeMillis();// 获取当前毫秒
            System.out.println("运行经历的毫秒数:"+(end-start));// 获取程序一共运行多长时间
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in!=null){// 判断文件输入流是否为空
                try {
                    in.close();// 关闭文件输入流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bi!=null){// 判断缓冲输入流是否为空
                try {
                    bi.close();// 关闭缓冲输入流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out!=null){// 判断文件输出流是否为空
                try {
                    out.close();// 关闭文件输出流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

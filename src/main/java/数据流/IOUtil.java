package 数据流;

import java.io.*;

public class IOUtil {
    /**
     * 读取指定文件内容,按照16进制输出到控制台
     * 每输出10个byte换行
     * @param fileName 文件名字
     */
    public static void printHex(String fileName)throws IOException {
        FileInputStream in=new FileInputStream(fileName);// 创建文件输入流
        int b;// 创建读取字节变量
        int i=1;// 创建每次读取次数变量
        while ((b=in.read())!=-1){// 创建循环
            if (b<=0xf){// 如果读取的字节小于1位
                System.out.print("0");// 前面补0
            }
            System.out.print(Integer.toHexString(b)+" ");// 打印输出16进制
            if (i++%10==0){// 判断是否为10个byte
                System.out.println();// 换行
            }
        }
        in.close();// 关闭文件输入流
    }
    /**
     * 文件拷贝操作,字节批量读取
     * @param srcFile
     * @param destFile
     * @throws IOException
     */
    public static void copyFile(File srcFile,File destFile)throws IOException{
        if (!srcFile.exists()){// 判断文件是否存在
            throw new IllegalArgumentException("文件:"+srcFile+"不存在");// 抛出异常
        }
        if (!srcFile.isFile()){// 判断是否是文件
            throw new IllegalArgumentException("文件:"+srcFile+"不是文件");// 抛出异常
        }
        FileInputStream in=new FileInputStream(srcFile);// 创建文件输入流
        FileOutputStream out=new FileOutputStream(destFile);// 创建文件输出流
        byte[] buf=new byte[8*1024];// 创建缓冲区
        int b;// 创建读取字节变量
        while ((b=in.read(buf,0,buf.length))!=-1){// 读取字节变量不是-1开始循环
            out.write(buf,0,b);// 写入文件
            out.flush();// 刷新缓冲区
        }
        in.close();// 关闭文件输入流
        out.close();// 关闭文件输出流
    }
    /**
     * 利用带缓冲的字节流进行文件拷贝
     * @param srcFile
     * @param destFile
     * @throws IOException
     */
    public static void copyFileByBuffer(File srcFile,File destFile)throws IOException{
        if (!srcFile.exists()){// 判断文件是否存在
            throw new IllegalArgumentException("文件:"+srcFile+"不存在");// 抛出异常
        }
        if (!srcFile.isFile()){// 判断是否是文件
            throw new IllegalArgumentException("文件:"+srcFile+"不是文件");// 抛出异常
        }
        BufferedInputStream bis=new BufferedInputStream(new FileInputStream(srcFile));// 创建缓冲输入流
        BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(destFile));// 创建缓冲输出流
        int c;// 创建读取字节变量
        while ((c=bis.read())!=-1){// 读取字节变量不是-1开始循环
            bos.write(c);// 写入字节
            bos.flush();// 刷新缓冲区
        }
        bis.close();// 关闭缓冲输入流
        bos.close();// 关闭缓冲输出流
    }

    /**
     * 单字节,不带缓冲进行文件拷贝
     * @param srcFile
     * @param destFile
     * @throws IOException
     */
    public static void copyFileByByte(File srcFile,File destFile)throws IOException{
        if (!srcFile.exists()){// 判断文件是否存在
            throw new IllegalArgumentException("文件:"+srcFile+"不存在");// 抛出异常
        }
        if (!srcFile.isFile()){// 判断是否是文件
            throw new IllegalArgumentException("文件:"+srcFile+"不是文件");// 抛出异常
        }
        FileInputStream in=new FileInputStream(srcFile);// 创建文件输入流
        FileOutputStream out=new FileOutputStream(destFile);// 创建文件输出流
        int c;// 创建读取字节变量
        while ((c=in.read())!=-1){// 读取字节变量不是-1开始循环
            out.write(c);// 写入字节
            out.flush();// 刷新缓冲区
        }
        in.close();// 关闭文件输入流
        out.close();// 关闭文件输出流
    }
    public static void printHexByByteArray(String fileName)throws IOException{
        FileInputStream in=new FileInputStream(fileName);// 创建文件输入流
        byte[] buf=new byte[20*1024];// 创建20k大小的缓冲区
        int bytes=in.read(buf,0,buf.length);// 从in中读取字节,从第0读取到结束
        int j=1;// 创建每次读取次数变量
        for (int i=0;i<bytes;i++){// 循环遍历
            if (buf[i]<=0xf){// 如果数组的值小于等于一位
                System.out.print("0");// 前面补0
            }
            System.out.println(Integer.toHexString(buf[i])+" ");// 输出16进制
            if (j++%10==0){// 如果读取10个byte
                System.out.println();// 换行
            }
        }
        /**
         * 第二种方法
         * 不需要创建缓冲区
         */
        int bytes2=0;// 创建读取字节变量
        int j2=1;// 创建每次读取次数变量
        while ((bytes2=in.read(buf,0,buf.length))!=-1){// 读取字节变量不是-1开始循环
            for (int i=0;i<bytes2;i++){// 每次读取次数变量小于读取字节变量开始循环
                System.out.println(Integer.toHexString(buf[i]&0xff)+" ");// 输出16进制
                if (j2++%10==0){// 如果读取10个byte
                    System.out.println();// 换行
                }
            }
        }
    }
}
/**
 * 创建测试类
 */
class IOUtilTest1{
    public static void main(String[] args) {
        try {// 捕捉异常
            IOUtil.printHex("C:/Users/15811/Desktop/abc.txt");// 传入文件路径
            IOUtil.printHexByByteArray("C:/Users/15811/Desktop/abc.txt");// 传入文件路径
            // 调用方法
            IOUtil.copyFile(new File("C:/Users/15811/Desktop/abc.txt"),new File("C:/Users/15811/Desktop/abc.txt"));
            // 调用缓冲流方法
            IOUtil.copyFileByBuffer(new File("C:/Users/15811/Desktop/abc.txt"),new File("C:/Users/15811/Desktop/abc.txt"));
            // 调用单字节方法
            IOUtil.copyFileByByte(new File("C:/Users/15811/Desktop/abc.txt"),new File("C:/Users/15811/Desktop/abc.txt"));
        } catch (IOException e) {// 处理异常
            e.printStackTrace();// 打印异常
        }
    }
}

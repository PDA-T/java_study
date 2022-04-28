package 数据流.子进程流;

import java.io.*;
import java.util.Scanner;

/**
 * 对子进程进行读写
 * 给子进程写入信息
 * 父进程
 */
public class ReadFromProcess2 {
    public static void main(String[] args) {
        PrintStream ps = null;
        try {
            // 创建子进程
            Process p = Runtime.getRuntime().exec("java ReceiveInfo");
            // 拿到输出流
            OutputStream out = p.getOutputStream();
            // 把输出流封装到打印流
            ps = new PrintStream(out);
            // 发送信息,任何信息都可以发送
            ps.print("字符串");
            ps.print(123);
            ps.print(new ReadFromProcess2());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            // 关闭打印流
            if (ps!=null){
                ps.close();
            }
        }
    }
}

/**
 * 把这个类放到子进程里去执行
 * 可以接收父进程(ReadFromProcess2)传来的数据
 * 并且把接收到的数据保存到文件
 */
class ReceiveInfo{
    public static void main(String[] args) throws FileNotFoundException {
        // 创建文件输出流
        OutputStream out = new FileOutputStream("D:/abc.txt");
        // 封装成打印流(也可以先封装成缓冲流在封装成打印流)
        PrintStream ps = new PrintStream(out);
        // 创建扫描器,标准输入流in在这里不是键盘,是父进程
        Scanner scanner = new Scanner(System.in);
        // 用回车做分割
        scanner.useDelimiter("\n");
        // 判断扫描器是否有数据,会阻塞线程
        while (scanner.hasNext()){
            // 写入到文件
            ps.print(scanner.next());
        }
        // 关闭流
        ps.close();
    }
}

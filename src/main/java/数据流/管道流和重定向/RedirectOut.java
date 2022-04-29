package 数据流.管道流和重定向;

import java.io.*;
import java.util.Scanner;

/**
 * 重定向标准输入或输出
 * 项目中很少用
 */
public class RedirectOut {
    public static void main(String[] args) throws FileNotFoundException {
        // 创建文件输出流
        FileOutputStream fos = new FileOutputStream("D:/abc.txt");
        // 创建打印输出流
        PrintStream ps = new PrintStream(fos);
        // 重定向标准输出,本来是打印到控制台,重定向到文件
        System.setOut(ps);
        // 运行打印方法,不会打印到控制台,而是打印到文件
        System.out.println();
        // 关闭打印流
        ps.close();
    }

    /**
     * 重定向标准输入
     */
    public void RedirectIn() throws FileNotFoundException {
        // 创建文件输入流
        InputStream in = new FileInputStream("D:/abc.txt");
        // 重定向标准输出,本来是键盘输入,现在重定向到文件
        System.setIn(in);
        // 创建扫描器
        Scanner sc = new Scanner(System.in);
        // 设置间隔符号
        sc.useDelimiter("\n");
        // 如果输入流扫描到有数据,循环读取文件
        while (sc.hasNext()){
            // 打印数据
            System.out.println(sc.next());
        }
    }
}
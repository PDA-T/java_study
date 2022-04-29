package 数据流.打印推回流;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * 打印流就是为了输出更加方便
 * 包含字节打印流和字符打印流
 */
public class PrintStreamTest {
    public static void main(String[] args) throws FileNotFoundException {
        // 创建文件输出流
        FileOutputStream fos = new FileOutputStream("D:/abc.txt");
        // 封装为打印流
        PrintStream ps = new PrintStream(fos);
        // 可以写入任何类型数据
        ps.print("Google");
        ps.print(123);
        ps.print(12.3);
        ps.print(new Object());
        ps.println();
        ps.print("1+1="+2);
        // 关闭输入流
        ps.close();
    }

    /**
     * 打印流可以格式化输出
     */
    public void PrintStreamTest2() throws FileNotFoundException {
        // 创建文件输出流
        FileOutputStream fos = new FileOutputStream("D:/abc.txt");
        // 封装为打印流
        PrintStream ps = new PrintStream(fos);
        // 定义整数
        int i = 10;
        // 定义字符串
        String s = "打印流";
        // 定义小数
        float f = 15.5f;
        // 打印格式化,整数%d,字符串%s,小数%f,前面先定义,后面补全
        ps.printf("整数%d,Google%s,GitHub%f",i,s,f);
    }
}

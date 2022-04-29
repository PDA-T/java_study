package 数据流.打印推回流;

import java.io.*;

public class BrAndBwOrPwDemo {
    /**
     * 字符打印流
     */
    public static void main(String[] args) throws IOException {
        // 创建过滤字符流
        BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/15811/Desktop/abc.txt")));
        // 创建过滤输出流
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:/Users/15811/Desktop/abc.txt")));
        // 创建输出字符流
        PrintWriter pw=new PrintWriter("C:/Users/15811/Desktop/abc.txt");
        String line;// 创建字符串
        while((line=br.readLine())!=null){// 如果读取的字符不是空开始循环,一次读取一行无法识别换行
            System.out.println(line);// 打印结果
            bw.write(line);// 写入字符
            bw.newLine();// 换行
            bw.flush();// 刷新缓冲区
            pw.print(line);// 输出一个字符串
            pw.flush();// 刷新缓冲区
        }
        br.close();// 关闭过滤字符流
        bw.close();// 关闭过滤输出流
        pw.close();// 关闭输出字符流
    }
}

package 数据流.打印推回流;

import java.io.*;

/**
 * 推回输入流
 * 将已经读取出来的字节或字符数组的内容,从新推回到缓冲区
 * 从而允许重复读取刚刚读取的不想要的东西之前的内容(想要的内容)
 */
public class PushbackInputTest {
    public static void main(String[] args) throws IOException {
        // 创建字符输入流
        Reader reader = new FileReader("D:/abc.txt");
        // 使用推回流,封装字符输入流,要设置缓冲区大小,不然默认1
        PushbackReader pr = new PushbackReader(reader,1024);
        // 创建字符数组
        char[] c = new char[5];
        // 创建已经读取出来的个数的计数器
        int hasReadCount = 0;
        // 创建已经读取出来的老字符串
        String sumString = "";
        // 循环读取
        while ((hasReadCount=pr.read(c))!=-1){
            // 本次循环获取的数据,转换成字符串
            String curString = new String(c,0,hasReadCount);
            // 获取总字符串
            sumString = sumString + curString;
            // 查有没有不想要的字符串(aaa)
            int aaaIndex = sumString.indexOf("aaa");
            // 判断如果有不想要的字符串
            if (aaaIndex>-1){
                // 推回数据(所有内容),推回的数据不能比缓冲区大
                pr.unread(sumString.toCharArray());
                // 重新读取需要的内容,如果比数组缓冲区大
                if (aaaIndex > 5){
                    // 重新定义数组,大小为索引值的大小
                    c = new char[aaaIndex];
                }
                // 读取需要的内容
                pr.read(c,0,c.length);
                // 输出需要的内容
                System.out.println(new String(c));
                // 已经拿到了要的内容,结束循环
                break;
                // 没有不想要的内容
            }else {
                // 直接输出
                System.out.println(new String(c));
            }
        }
    }
}

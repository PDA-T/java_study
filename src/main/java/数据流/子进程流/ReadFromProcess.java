package 数据流.子进程流;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 对子进程进行读写
 * 拿取子进程信息
 */
public class ReadFromProcess {
    public static void main(String[] args) throws IOException {
        // 用Runtime里的exec方法,调用平台上的javac命令,Process就代表子进程
        Process p = Runtime.getRuntime().exec("javac");
        // 把字节流转换成字符流,从p里面获取信息(错误信息)
        InputStreamReader reader = new InputStreamReader(p.getErrorStream(),"GBK");
        // 封装成缓冲流
        BufferedReader br = new BufferedReader(reader);
        // 创建字符串
        String buff = null;
        // 创建循环,读取一行
        while ((buff = br.readLine())!=null){
            // 输出结果
            System.out.println(buff);
        }
        // 关闭流
        br.close();
    }
}

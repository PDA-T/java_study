package 数据流.缓冲流;

import java.io.*;

public class 缓冲字符流 {
    public static void main(String[] args) {
        File f=new File("C:/Users/15811/Desktop/abc.txt");// 创建文件对象
        FileWriter fw=null;// 创建字符输出流
        BufferedWriter bw=null;// 创建缓冲字符输出流
        FileReader fr=null;// 创建字符输入流
        BufferedReader br=null;// 创建缓冲字符输入流
        try {
            fw=new FileWriter(f);// 实例化字符输出流
            bw=new BufferedWriter(fw);// 实例化缓冲字符输出流
            fr=new FileReader(f);// 实例化字符输入流
            br=new BufferedReader(fr);// 实例化缓冲字符输入流
            String s="Google";// 字符串1
            String s2="IDEA";// 字符串2
            bw.write(s);// 写入字符串1
            bw.newLine();// 创建新一行
            bw.write(s2);// 写入字符串2
            String tmp=null;// 创建空字符串
            int i=1;// 计数器
            while ((tmp=br.readLine())!=null){// 读取一行,循环读取文件内容
                System.out.println("第"+i+"行:"+tmp);// 输出结果
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {// 先创建的流后关闭
            if (bw!=null){// 判断流是否为空
                try {
                    bw.close();// 关闭缓冲字符输出流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fw!=null){// 判断流是否为空
                try {
                    fw.close();// 关闭字符输出流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br!=null){// 判断流是否为空
                try {
                    br.close();// 关闭缓冲字符输入流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fr!=null){// 判断流是否为空
                try {
                    fr.close();// 关闭字符输入流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

package 数据流.File类;

import java.io.File;
import java.io.IOException;

/**
 * 列出File的一些常用操作比如过滤、遍历等
 */
public class FileUtils {
    /**
     * 列出指定目录下(包括子目录)的所有文件
     * @param dir
     * @throws IOException
     */
    public static void listDirectory(File dir)throws IOException{
        if (!dir.exists()){// 判断文件是否存在
            throw new IllegalArgumentException("目录:"+dir+"不存在");// 抛出异常
        }
        if (!dir.isDirectory()){// 判断File类的对象是否是目录
            throw new IllegalArgumentException(dir+"不是目录");// 抛出异常
        }
        /*String[] filenames=dir.list();// 返回字符串数组
        for (String string:filenames){// 遍历数组
            System.out.println(dir+"\\"+string);// 打印结果
        }*/
        File[] files=dir.listFiles();// 返回的是直接子目录(文件)的抽象
        if (files!=null&&files.length>0){// 判断文件是否为空并且数组长度大于0
            for (File file:files){// 遍历数组
                if (file.isDirectory()){// 如果文件是目录
                    listDirectory(file);// 递归
                }else {// 否则
                    System.out.println(file);// 打印结果
                }
            }
        }
    }
}

/**
 * 测试类
 */
class FileUtilTest1{
    public static void main(String[] args) throws IOException{
        FileUtils.listDirectory(new File("C:/"));// 调用列出目录方法
    }
}

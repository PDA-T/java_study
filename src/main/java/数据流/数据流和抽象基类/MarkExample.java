package 数据流.数据流和抽象基类;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * 抽象基类
 * 用于封装其他流,提供更多的功能
 */
public class MarkExample {
    public static void main(String[] args) throws IOException {
        // 定义一个字节数组
        byte[] b = new byte[]{1,2,3,4,5};
        // 把数组转换成数组流
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        // 把数组流封装成为缓冲流,封装过程中需要制定缓冲区大小(2个字节)
        BufferedInputStream bis = new BufferedInputStream(bais,2);
        // 读取第一个字节
        System.out.println(bis.read());
        // 判断一个流是否支持标记
        System.out.println(bis.markSupported());
        // 指针后移一位
//        bis.skip(1);
        // 在第二个位置上打一个标记(读取一个后指针在第二个位置)
        bis.mark(1);
        // 按照文档,写的mark标记有效性参数是一个字节,读取两个字节后标记失效
        System.out.println(bis.read());
        System.out.println(bis.read());
        // 读取第三个超出缓冲区标记方法才会失效(报错)
//        System.out.println(bis.read());
        // 返回标记方法,读取超过写的参数1个字节不会报错(文档是错的)
        bis.reset();
        // 执行了标记方法,指针会从新回到标记的位置(读取第二个)
        System.out.println(bis.read());
    }
}

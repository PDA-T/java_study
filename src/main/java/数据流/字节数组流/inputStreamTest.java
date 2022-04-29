package 数据流.字节数组流;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * 字节数组输入流
 */
public class inputStreamTest {
    public static void main(String[] args) throws IOException {
        // 定义一个数组,一个数占用一个字节
        byte[] b1 = new byte[]{1,2,3,4};
        // 把数组转换为数组输入流
        ByteArrayInputStream bais = new ByteArrayInputStream(b1);
        // 显示输入流中的剩余数组数量
        System.out.println(bais.available());
        // 定义一个数组
        byte[] b2 = new byte[2];
        // 从输入流中拿出两个字节的数据放入b2
        bais.read(b2);
        // 把字节转换为字符,输出b2的值
        System.out.println(Arrays.toString(b2));
        System.out.println("读取一次之后,输入流剩下的数据量:"+bais.available());
        // 从输入流中拿出两个字节的数据放入b2
        bais.read(b2);
        // 把字节转换为字符,输出b2的值
        System.out.println(Arrays.toString(b2));
        System.out.println("读取二次之后,输入流剩下的数据量:"+bais.available());
        // 从输入流中拿出两个字节的数据放入b2
        bais.read(b2);
        // 把字节转换为字符,输出b2的值
        System.out.println(Arrays.toString(b2));
        // 一共只放了四个字节的数据,每次拿出两个之后,第三次已经没有数据
        System.out.println("读取三次之后,输入流剩下的数据量:"+bais.available());
    }
}

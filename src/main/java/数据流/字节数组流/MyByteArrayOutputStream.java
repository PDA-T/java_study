package 数据流.字节数组流;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 验证数组扩容问题
 * 默认初始化32个字节大小,最大容量约为2G,只要数据不超过2G,都可以往里写
 * 每次写数据之前会先计算大小,如果需要扩容,扩大到原来的两倍大小
 */
public class MyByteArrayOutputStream {
    public static void main(String[] args) throws IOException {
        // 创建数组输出流
        MyByteArrayOutputStream2 out = new MyByteArrayOutputStream2();
        // 输出默认情况,里面存放数组的buf的长度
        System.out.println(out.getBuf().length);
        // 输出里面有多少数据
        System.out.println(out.size());
        // 分隔
        System.out.println("----------------------");
        // 写入32个字节的数据
        for (int i=0;i<32;i++){
            out.write(1);
        }
        // 输出数组,里面存放数组的buf的长度
        System.out.println(out.getBuf().length);
        // 输出里面有多少数据
        System.out.println(out.size());
        // 分隔
        System.out.println("----------------------");
        // 再次写入一个数据
        out.write(1);
        // 输出数组,里面存放数组的buf的长度
        System.out.println(out.getBuf().length);
        // 输出里面有多少数据
        System.out.println(out.size());
        // 分隔
        System.out.println("----------------------");
        // 放入98个数据
        out.write(new byte[98]);
        // 输出数组,里面存放数组的buf的长度
        System.out.println(out.getBuf().length);
        // 输出里面有多少数据
        System.out.println(out.size());
    }
}
class MyByteArrayOutputStream2 extends ByteArrayOutputStream {
    // 父类的buf数组内为缓冲区的信息
    public byte[] getBuf(){
        // 拿到父类的buf数组
        return super.buf;
    }
}

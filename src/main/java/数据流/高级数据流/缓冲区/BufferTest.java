package 数据流.高级数据流.缓冲区;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * 缓冲区的各个方法
 */
public class BufferTest {
    public static void main(String[] args) {
        // 创建字节缓冲区,长度为9
        ByteBuffer buffer = ByteBuffer.allocate(9);
        // 缓冲区容量
        System.out.println("缓冲区容量:"+buffer.capacity());
        // 缓冲区上界
        System.out.println("缓冲区上界:"+buffer.limit());
        // 缓冲区指针位置
        System.out.println("缓冲区指针位置:"+buffer.position());
        // 创建字符串
        String str = "123456";
        // 把字符串转换为字节数组
        byte[] strbyte = str.getBytes();
        // 放入缓冲区
        buffer.put(strbyte);
        // 缓冲区上界
        System.out.println("缓冲区上界:"+buffer.limit());
        // 缓冲区指针位置
        System.out.println("缓冲区指针位置:"+buffer.position());
        // 分割
        System.out.println("----------------------------------");
        /*
         * 反转缓冲区(变为读取模式)
         * 把上界移动到指针的位置(如果不设置上界到指针位置,读取时会一直读取到缓冲区最大容量的位置)
         * 把指针移动到0(从0开始读取)
         * 把标记移动到-1(清空标记)
         */
        buffer.flip();
        // 缓冲区上界
        System.out.println("缓冲区上界:"+buffer.limit());
        // 缓冲区指针位置
        System.out.println("缓冲区指针位置:"+buffer.position());
        // 判断是不是只读缓冲区
        System.out.println(buffer.isReadOnly());
        // 创建一个字符数组,大小为上界(元素的个数)
        char[] dataarr = new char[buffer.limit()];
        // 循环判断缓冲区是否还有数据
        for (int i=0;buffer.hasRemaining();i++){
            // 获取数据
            dataarr[i] = (char) buffer.get();
        }
        // 输出数据
        System.out.println(Arrays.toString(dataarr));





        // 分割,因为已经变为只读缓冲区,上界问题导致以下代码会报错
        System.out.println("=================================");
        // 更改指针位置
        buffer.position(5);
        // 缓冲区指针位置
        System.out.println("缓冲区指针位置:"+buffer.position());
        // 在5的位置上打标记
        buffer.mark();
        // 分割
        System.out.println("---------------------------------");
        // 改变指针位置
        buffer.position(8);
        // 缓冲区指针位置
        System.out.println("缓冲区指针位置:"+buffer.position());
        // 把指针位置更改到标记位置
        buffer.reset();
        // 缓冲区指针位置
        System.out.println("缓冲区指针位置:"+buffer.position());
    }
}

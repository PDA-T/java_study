package 数据流.高级数据流.缓冲区;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Arrays;

/**
 * 缓冲区比大小和判断相等
 * get方法的不常用构造器(容易报异常,推荐使用循环方法)
 */
public class BufferTest2 {
    public static void main(String[] args) {
        // 创建两个缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(9);
        ByteBuffer buffer2 = ByteBuffer.allocate(9);
        // 存入三个字节
        buffer.put("123".getBytes());
        buffer2.put("456".getBytes());
        /*
         * 判断相等条件
         * 1.缓冲区指针位置和上界之间的元素个数一样
         * 2.缓冲区指针位置和上界之间的元素值一样
         * 相等
         */
        System.out.println(buffer.equals(buffer2));
        // 反转缓冲区,改变了指针位置和上界位置所以不相等
        buffer.flip();
        buffer2.flip();
        // 不相等
        System.out.println(buffer.equals(buffer2));
        /*
         * 判断大小,大了返回正整数,小了返回负整数,0为相等
         * 一个元素大返回的值+1,小则-1(如果一共10个元素都大则返回10)
         * 1.缓冲区指针位置和上界之间元素多的一定大
         * 2.从调用者开始对比,一旦第一个元素大小确定了,则返回值也确定
         * 如调用者第一个小,那么返回值最小为-1
         */
        System.out.println(buffer.compareTo(buffer2));


        // 创建一个字节数组
        byte[] arr = new byte[10];
        // 使用get方法,从缓冲区批量读取数据(因为缓冲区只有三个元素,而数组要求10个,报错)
        buffer.get(arr);
        // 指定释放字节个数不会报错
        buffer.get(arr,0,3);
        // 缓冲区指针位置
        System.out.println("缓冲区指针位置:"+buffer.position());
        // 缓冲区上界
        System.out.println("缓冲区上界:"+buffer.limit());
        // 输出数组(二进制)
        System.out.println(Arrays.toString(arr));
    }
}

/**
 * 缓冲区的创建
 * 间接的缓冲区,使用的是备份的数组
 */
class BufferTest2_1 {
    public static void main(String[] args) {
        // 创建一个容量为100个char变量的缓冲区
        CharBuffer buffer = CharBuffer.allocate(100);
        // 创建一个数组(另一种创建方法)
        char[] chararr = new char[100];
        // 通过数组长度创建缓冲区(如果对数组操作会直接影响缓冲区)
        CharBuffer buffer2 = CharBuffer.wrap(chararr);
    }
}

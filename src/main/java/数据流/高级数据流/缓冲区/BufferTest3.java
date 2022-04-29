package 数据流.高级数据流.缓冲区;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * 视图缓冲区
 */
public class BufferTest3 {
    public static void main(String[] args) {
        // 创建原始缓冲区
        ByteBuffer buffery = ByteBuffer.allocate(9);
        // 根据原始缓冲区,创建视图缓冲区
        ByteBuffer buffers = buffery.duplicate();
        // 在原始缓冲区中添加值
        buffery.put("123456".getBytes());
        // 创建数组
        char[] c = new char[6];
        // 循环读取数据
        for (int i=0;i<6;i++){
            /*
             * 视图缓冲区和原始缓冲区数据共享,但是指针不共享
             * 所以现在视图缓冲区位置指针为0,但是已经有数据
             * 不用反转缓冲区即可取出
             */
            c[i] = (char)buffers.get();
        }
        // 输出数组
        System.out.println(Arrays.toString(c));

        // 创建原始缓冲区
        ByteBuffer buffery2 = ByteBuffer.allocate(9);
        // 根据原始缓冲区,创建视图缓冲区(只读),除了不能写入其他和视图缓冲区一样
        ByteBuffer buffers2 = buffery2.asReadOnlyBuffer();
        // 判断是否是只读缓冲区
        System.out.println(buffers2.isReadOnly());

        // 创建原始缓冲区
        ByteBuffer buffery3 = ByteBuffer.allocate(8);
        // 存入8个字节
        buffery3.put("12345678".getBytes());
        // 反转缓冲区
        buffery3.flip();
        // 移动位置指针到4
        buffery3.position(4);
        // 创建视图缓冲区(分割)
        ByteBuffer buffers3 = buffery3.slice();
        // 输出缓冲区容量,分割缓冲区容量为原始缓冲区当前的上界指针-位置指针的值
        System.out.println(buffers3.capacity());
        // 创建数组
        char[] c2 = new char[9];
        // 循环读取数据
        for (int i=0;buffers3.hasRemaining();i++){
            // 读取数据
            c2[i] = (char)buffers3.get();
        }
        // 输出数组,只能输出一段,因为不是完全共享,只共享上界指针-位置指针的数据
        System.out.println(Arrays.toString(c2));

        // 调用另一个类
        new BufferTest3_1();
    }
}

/**
 * 字节二进制读取机制
 * 分为大端序和小端序,大端则从二进制最左边开始读取,小端则从右边开始读取
 */
class BufferTest3_1{
    BufferTest3_1(){
        // 创建字节缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(9);
        // 输出默认的读取顺序
        System.out.println(buffer.order());
        // 把读取顺序改为小端
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        // 输出读取顺序
        System.out.println(buffer.order());
        // 输出当前系统CPU的读取顺序
        System.out.println(ByteOrder.nativeOrder());

        // 给缓冲区放入字节
        buffer.put("12345678".getBytes());
        // 反转缓冲区
        buffer.flip();
        // 用大端序模式取出
        int v1 = buffer.order(ByteOrder.BIG_ENDIAN).getInt();
        // 用小端序模式取出
        int v2 = buffer.order(ByteOrder.LITTLE_ENDIAN).getInt();
        // 输出差别
        System.out.println(v1+","+v2);
    }
}

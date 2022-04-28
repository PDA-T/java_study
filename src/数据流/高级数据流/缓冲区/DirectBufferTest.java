package 数据流.高级数据流.缓冲区;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 直接缓冲区
 * 绕过了虚拟机内存,在大数据和大文件处理的情况下效率高
 * 小文件和数据量不大情况下效率低
 */
public class DirectBufferTest {
    public static void main(String[] args) throws IOException {
        /*
         * 创建直接缓冲区(100M)
         * 创建的大小默认为JVM堆的最大值,如果超出会报错(OutOfMemoryError)
         * 但是不受堆的限制,是由JVM参数MaxDirectMemorySize单独控制
         * 解决办法,可以更改缓冲区大小,或者更改JVM参数大小
         */
        ByteBuffer.allocateDirect(100*1024*1024);
        // 间接缓冲区
        new DirectBufferTest_0().DirectBufferTest_1();
        // 直接缓冲区
        new DirectBufferTest_0().DirectBufferTest_2();
    }
}

/**
 * 对比一个大文件和一个小文件读取速度
 */
class DirectBufferTest_0{
    void DirectBufferTest_1() throws IOException {
        // 创建文件类,GTA5中2.6G的文件
        File file = new File("D:\\Epic Games\\GTAV\\x64q.rpf");
        // 创建文件输入流
        FileInputStream fin = new FileInputStream(file);
        // 获取文件通道
        FileChannel channel = fin.getChannel();
        // 创建间接缓冲区(缓冲区越大越快)
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 获取程序开始时间
        long start = System.currentTimeMillis();
        // 循环把文件存入缓冲区,判断是否把文件读取完毕
        while (channel.read(buffer)!=-1){
            // 反转缓冲区
            buffer.flip();
            // 清空缓冲区
            buffer.clear();
        }
        // 获取程序结束时间
        long end = System.currentTimeMillis();
        // 输出程序执行时间
        System.out.println("间接缓冲区需要时间:" + (end-start));
    }

    /**
     * 直接缓冲区
     * @throws IOException
     */
    void DirectBufferTest_2() throws IOException {
        // 创建文件类,GTA5中2.6G的文件
        File file = new File("D:\\Epic Games\\GTAV\\x64q.rpf");
        // 创建文件输入流
        FileInputStream fin = new FileInputStream(file);
        // 获取文件通道
        FileChannel channel = fin.getChannel();
        // 创建直接缓冲区(缓冲区越大越快)
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        // 获取程序开始时间
        long start = System.currentTimeMillis();
        // 循环把文件存入缓冲区,判断是否把文件读取完毕
        while (channel.read(buffer)!=-1){
            // 反转缓冲区
            buffer.flip();
            // 清空缓冲区
            buffer.clear();
        }
        // 获取程序结束时间
        long end = System.currentTimeMillis();
        // 输出程序执行时间
        System.out.println("直接缓冲区需要时间:" + (end-start));
    }
}

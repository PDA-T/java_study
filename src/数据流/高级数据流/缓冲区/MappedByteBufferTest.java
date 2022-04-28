package 数据流.高级数据流.缓冲区;

import jdk.internal.ref.Cleaner;
import sun.nio.ch.DirectBuffer;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 映射字节缓冲区
 * 项目中推荐使用
 */
public class MappedByteBufferTest {
    public static void main(String[] args) throws IOException {
        // 创建文件类,GTA5中1.8G的文件(map限制2G)
        File file = new File("D:\\Epic Games\\GTAV\\x64v.rpf");
        // 创建随机文件读取类
        RandomAccessFile raf = new RandomAccessFile(file,"rw");
        // 拿到文件通道
        FileChannel channel = raf.getChannel();
        // 获取程序开始时间
        long start = System.currentTimeMillis();
        // 映射字节缓冲区,把文件从0开始到文件结尾的长度在内存和硬盘之间建立映射关系(READ_ONLY只读),限制2G
        MappedByteBuffer mb = channel.map(FileChannel.MapMode.READ_ONLY,0,file.length());
        // 循环判断缓冲区是否有数据
        while (mb.hasRemaining()){
            // 获取数据,不需要反转缓冲区
            mb.get();
        }
        // 获取程序结束时间
        long end = System.currentTimeMillis();
        // 输出程序执行时间
        System.out.println("需要时间:" + (end-start));
        // 关闭通道
        channel.close();
        // 关闭流
        raf.close();
        /*
         * 给文件改名,因为文件映射不会因为关闭了流或者通道而关闭
         * 文件映射中是无法修改文件的,全局GC回收才可以清除掉映射关系
         */
        file.renameTo(new File("D:\\Epic Games\\GTAV\\x64v.rpf"));

        /*
         * 解决无法断开映射的问题
         */
        Cleaner cleaner = ((DirectBuffer) mb).cleaner();
        // 判断是否为空
        if (channel!=null){
            // 断开连接
            cleaner.clean();
        }

        /*
         * 解决不能一次性处理超过2G的文件
         * 创建缓冲区
         */
        MappedByteBuffer mb2 = null;
        // 创建文件长度
        long fileLen = file.length();
        // 创建文件内容的隐藏指针位置
        long cur = 0;
        // 使用位移运算,每次映射512M
        long mapSize = 1L << 29;
        // 如果文件指针位置小于文件长度开始循环,循环完毕之后文件指针位置加上每次映射的文件大小(512M)
        for (;cur < fileLen;cur += mapSize){
            // 如果文件长度减去指针位置小于512M,说明剩下的内容不足以512M,通过判断单独处理
            if (fileLen - cur < mapSize){
                // 说明此次拿出的容量不足512M,需要使用文件长度减去当前指针位置
                mb2 = channel.map(FileChannel.MapMode.READ_ONLY,cur,fileLen-cur);
            }else {
                // 说明此次拿出的容量足够512M,直接移动mapSize(512M)即可
                mb2 = channel.map(FileChannel.MapMode.READ_ONLY,cur,mapSize);
            }
            // 循环判断缓冲区是否有数据
            while (mb2.hasRemaining()){
                // 读取数据
                mb2.get();
            }
        }

        /**
         * 写文件
         * 创建文件类
         */
        File file2 = new File("D:\\Epic Games\\GTAV\\x64v.rpf");
        // 创建随机文件读取类
        RandomAccessFile raf2 = new RandomAccessFile(file2,"rw");
        // 拿到文件通道
        FileChannel channel2 = raf2.getChannel();
        // 映射字节缓冲区,把文件从0开始到文件结尾的长度在内存和硬盘之间建立映射关系(READ_WRITE读写),限制2G
        MappedByteBuffer mb3 = channel2.map(FileChannel.MapMode.READ_WRITE,0,file.length());
        // 循环判断缓冲区是否有数据
        while (mb.hasRemaining()){
            // 获取第一个文件内容写入第二个缓冲区
            mb3.put(mb.get());
        }
    }
}

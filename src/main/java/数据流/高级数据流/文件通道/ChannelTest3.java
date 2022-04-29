package 数据流.高级数据流.文件通道;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * 通过一个方法从一个数据通道传入到另一个数据通道
 */
public class ChannelTest3 {
    public static void main(String[] args) throws IOException {
        // 创建随机文件类
        RandomAccessFile fileFrom = new RandomAccessFile("D:/abc.txt","rw");
        // 拿到文件通道
        FileChannel channelFrom = fileFrom.getChannel();
        // 创建随机文件类(要复制的地方)
        RandomAccessFile fileTo = new RandomAccessFile("E:/abc.txt","rw");
        // 拿到文件通道
        FileChannel channelTo = fileTo.getChannel();
        // 传输,第一个参数为原数据的文件通道,第二为起始位置,第三个为最多传输多少字节
        channelTo.transferFrom(channelFrom,0,channelFrom.size());
        // 和上面的方法效果一样,只是参数位置和通道不一样
        channelFrom.transferTo(0,channelFrom.size(),channelTo);
    }
}

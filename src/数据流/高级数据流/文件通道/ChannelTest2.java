package 数据流.高级数据流.文件通道;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIO流的写入操作
 */
public class ChannelTest2 {
    public static void main(String[] args) {
        // 创建一个字符串
        String str = "Google";
        // 创建文件通道
        FileChannel fc = null;
        try {
            // 创建文件输出流
            FileOutputStream fout = new FileOutputStream("D:/abc.txt");
            // 通过文件输出流获取文件通道(文件通道必须通过输入输出流获取)
            fc = fout.getChannel();
            // 创建缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // 创建字节数组,把字符变成字节
            byte[] data = str.getBytes();
            // 循环遍历数组
            for (int i=0;i<data.length;i++){
                // 把数据放入缓冲区
                buffer.put(data[i]);
            }
            // 把缓冲区从写入模式,变为读取模式(因为要把缓冲区数据写出到通道,就需要读取缓冲区)
            buffer.flip();
            // 截取文件字节(截取文件前30字节)
//            FileChannel fc2 = fc.truncate(30);
            // 如果缓冲区还有数据
            while (buffer.hasRemaining()){
                // 输出指针位置
                System.out.println(fc.position());
                // 写入文件通道
                fc.write(buffer);
                // 输出指针位置
                System.out.println(fc.position());
                // 输出通道关联的文件大小
                System.out.println(fc.size());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            while (fc!=null){
                try {
                    // 关闭通道
                    fc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

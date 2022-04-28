package 数据流.高级数据流.文件通道;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIO流,必须通过缓冲区来读取写入
 * NIO流是IO(BIO)流的更新版,支持同步,非阻塞线程读取写入文件
 * 但是本地一般使用IO(BIO)流,NIO流主要为网络编程开发使用
 */
public class ChannelTest {
    public static void main(String[] args) {
        // 创建NIO流通道
        FileChannel fc = null;
        try {
            // 创建随机访问文件类
            RandomAccessFile raf = new RandomAccessFile("D:/abc.txt","rw");
            // 获取NIO流通道实例
            fc = raf.getChannel();
            // 获取缓冲区,传入缓冲区大小
            ByteBuffer buffer = ByteBuffer.allocate(48);
            // 表示了有多少字节被读到了Buffer中,返回-1表示读到了文件末尾
            int hasRead = fc.read(buffer);
            // 循环判断是否读到文件末尾
            while (hasRead!=-1){
                // 把缓冲区从写入模式,改为读取模式
                buffer.flip();
                // 如果缓冲区还有未读取的内容存在
                while (buffer.hasRemaining()){
                    // 输出数据
                    System.out.println((char) buffer.get());
                }
                // 缓冲区数据已经读完了,需要再次从通道里获取,清空缓冲区
                buffer.clear();
                // 在从通道里面读取数据
                hasRead = fc.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fc!=null){
                try {
                    // 关闭流
                    fc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

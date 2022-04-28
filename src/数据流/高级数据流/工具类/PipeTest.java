package 数据流.高级数据流.工具类;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * 2个线程之间单向数据连接
 */
public class PipeTest {
    public static void main(String[] args) throws IOException {
        // 获取pipe实例
        Pipe pipe = Pipe.open();
        // 启动线程
        new Thread(new ThreadA(pipe)).start();
        new Thread(new ThreadB(pipe)).start();
    }
}

/**
 * 定义一个线程类
 */
class ThreadA implements Runnable{
    // 创建属性
    private Pipe pipe;
    // 创建构造器
    public ThreadA(Pipe pipe){
        this.pipe = pipe;
    }

    /**
     * 写入
     */
    @Override
    public void run() {
        // 获取通道
        Pipe.SinkChannel sink = pipe.sink();
        // 创建字符串
        String data = "aaaaaaaaaaaaaaaaaaaaa";
        // 创建缓冲区
        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
        // 把数据放入缓冲区
        writeBuffer.put(data.getBytes());
        // 反转缓冲区
        writeBuffer.flip();
        try {
            // 循环判断缓冲区是否有数据
            while (writeBuffer.hasRemaining()){
                // 把数据写入通道
                sink.write(writeBuffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class ThreadB implements Runnable{
    // 创建属性
    private Pipe pipe;
    // 创建构造器
    public ThreadB(Pipe pipe){
        this.pipe = pipe;
    }

    /**
     * 读取
     */
    @Override
    public void run() {
        // 获取通道
        Pipe.SourceChannel source = pipe.source();
        // 创建缓冲区
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        try {
            // 读取线程A的缓冲区
            source.read(readBuffer);
            // 反转缓冲区
            readBuffer.flip();
            // 输出数据
            System.out.println(new String(readBuffer.array()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

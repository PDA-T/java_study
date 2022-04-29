package 数据流.高级数据流.AIO;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

/**
 * AIO为NIO的进阶版(NIO2.0)
 * 异步非阻塞
 */
public class FutureTest {
    public static void main(String[] args) {
        // 创建路径
        Path path = Paths.get("D:\\Epic Games\\GTAV\\x64q.rpf");
        // 创建读缓冲区
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        try {
            // 构造出异步文件通道,READ表示未读
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
            // 读缓冲区,0表示从文件的最开始位置读
            Future<Integer> future = channel.read(readBuffer, 0);
            /*
             * 因为是异步的操作方式,所以不能直接读取
             * 在读取之前,必须保证从通道中取出数据,并且写入到缓冲区
             * 循环判断通道数据是否全部读完写入到读缓冲区(循环没有方法体)
             */
            while (!future.isDone());
            // 反转缓冲区
            readBuffer.flip();
            // 输出缓冲区的数据
            System.out.println("缓冲区里的数据:" + new String(readBuffer.array()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
         * 第二个方法
         * 使用异步,不在主线程内执行读取方法
         * 主线程不会等待
         */
        try {
            // 构造出异步文件通道,READ表示未读
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
            /*
             * 调用四参数read方法,第三个参数为第四个参数的附加对象
             * 会被传入到第四个参数的匿名内部类方法的参数上
             * 传入到completed方法的ByteBuffer attachment内
             */
            channel.read(readBuffer, 0, readBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                /**
                 * 从通道里把数据写入读缓冲区,写入完成时调用
                 * @param result
                 * @param attachment
                 */
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    // 输出读取了多少字节
                    System.out.println(result);
                    // 反转缓冲区
                    attachment.flip();
                    // 输出缓冲区内容
                    System.out.println(new String(attachment.array()));
                }

                /**
                 * 从通道里把数据写入读缓冲区,写入失败时调用
                 * @param exc
                 * @param attachment
                 */
                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // 等待子线程读取完毕在结束主线程
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写数据
     */
    FutureTest(){
        // 创建路径
        Path path = Paths.get("D:/abc.txt");
        // 判断文件是否存在
        if (!Files.exists(path)){
            try {
                // 创建文件
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 创建写缓冲区
        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
        // 放入数据
        writeBuffer.put("测试".getBytes());
        // 反转缓冲区
        writeBuffer.flip();
        try {
            // 获取文件的异步通道,WRITE表示写
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(path,StandardOpenOption.WRITE);
            // 写入通道,和读参数一样
            Future<Integer> fu = channel.write(writeBuffer, 0);
            // 等待写入完成之后在执行,和读一样
            while (!fu.isDone());
            System.out.println("写入完成");
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
         * 第二个方法
         * 使用异步,不在主线程内执行写入方法
         * 主线程不会等待
         */
        try {
            // 获取文件的异步通道,WRITE表示写
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(path,StandardOpenOption.WRITE);
            // 写入通道,和读参数一样
            channel.write(writeBuffer, 0, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                /**
                 * 写入完成调用
                 * @param result
                 * @param attachment
                 */
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    // 输出写入了多少字节
                    System.out.println(result);
                }

                /**
                 * 写入失败调用
                 * @param exc
                 * @param attachment
                 */
                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // 等待子线程读取完毕在结束主线程
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

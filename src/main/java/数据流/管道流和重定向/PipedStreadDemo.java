package 数据流.管道流和重定向;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 管道输出输入流
 * 用来进行线程之间的相互通信
 */
public class PipedStreadDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        // 使用线程池创建多线程(核心线程,最大线程,时间,时间名称,5个工作队列)
        ThreadPoolExecutor executor = new ThreadPoolExecutor(6,12,
                200,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(5));
        // 先创建管道输出流
        PipedOutputStream pout = new PipedOutputStream();
        // 创建管道输入流,需要连接输出流(进行通信)
        PipedInputStream pin = new PipedInputStream(pout);
        // 实例化线程类
        Sender sender = new Sender(pout);
        // 实例化线程类
        Receiver receiver = new Receiver(pin);
        // 放入线程池里去分配线程执行
        executor.execute(sender);
        // 放入线程池里去分配线程执行
        executor.execute(receiver);
        // 关闭线程池(非强制关闭)
        executor.shutdown();
        // 设置超时未关闭的时间(超过一小时未关闭强制关闭)
        executor.awaitTermination(1,TimeUnit.HOURS);
    }
}

/**
 * 生产者线程类
 */
class Sender implements Runnable{
    // 创建管道输出流
    private PipedOutputStream pout;
    // 创建初始化构造方法
    public Sender(PipedOutputStream pout){
        this.pout = pout;
    }
    @Override
    public void run() {
        // 定义一个字符串
        String s = "发送数据";
        // 把字符串转换成数组
        byte[] b = s.getBytes();
        try {
            // 写入缓冲数组,发送信息
            pout.write(b);
            System.out.println("发送出去的信息是:"+s);
            // 关闭之后,对面输入流使用read方法才能返回-1
            pout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 创建消费者类
 */
class Receiver implements Runnable{
    // 创建管道输入流
    private PipedInputStream pin;
    // 创建初始化构造方法
    public Receiver(PipedInputStream pin){
        this.pin = pin;
    }
    @Override
    public void run() {
        // 创建字节数组输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 创建字节数组
        byte[] b = new byte[1024];
        // 记录已经读取到的数组的字节个数
        int hasRead = 0;
        try {
            while ((hasRead = pin.read(b))!=-1){
                // 把数据写入数组输出流
                baos.write(b,0,hasRead);
            }
            // 获取数组输出流的内容
            byte[] rs = baos.toByteArray();
            // 把字节数组转换成字符串
            String s = new String(rs,0,rs.length);
            // 打印数组输出流的内容
            System.out.println("接收到的信息是:"+s);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

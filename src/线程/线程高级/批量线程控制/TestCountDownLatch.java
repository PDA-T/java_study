package 线程.线程高级.批量线程控制;

import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {
    public static void main(String[] args) {
        // 创建计数器初始化50的值,当计数器的值减到0时主线程才会被释放
        CountDownLatch latch = new CountDownLatch(50);
        // 实例化线程类
        CountDownLatchThread cdlt = new CountDownLatchThread(latch);
        // 线程开始的时间
        long start = System.currentTimeMillis();
        // 创建50条线程
        for (int i=1;i<51;i++){
            new Thread(cdlt,"线程"+i).start();
        }
        try {
            // 在主方法里调用await方法就会阻塞主线程
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 线程结束的时间
        long end = System.currentTimeMillis();
        // 输出一共消耗了多长时间
        System.out.println("一共消耗了:"+((end-start)/1000)+"秒");
    }
}
class CountDownLatchThread implements Runnable{
    // 创建计数器闭锁的工具类对象
    private CountDownLatch latch;
    // 初始化计数器闭锁
    public CountDownLatchThread(CountDownLatch latch){
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            // 循环50000遍
            for (int i=0;i<50000;i++){
                // 如果i为偶数
                if (i%2==0){
                    // 输出线程名
                    System.out.println(Thread.currentThread().getName()+"偶数为:"+i);
                }
            }
        }finally {
            // 让计数器闭锁(CountDownLatch)里的计数器-1
            latch.countDown();
        }
    }
}

package 线程.线程高级.线程池;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 */
public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        // 核心线程池大小
        int corePoolSize = 5;
        // 线程池最多能接受多少线程
        int maximumPoolSize = 10;
        // 当前线程数大于corePoolSize,小于maximumPoolSize时,超出corePoolSize的线程数的生命周期
        long keepActiveTime = 200;
        // 设置时间单位,秒
        TimeUnit timeUnit = TimeUnit.SECONDS;
        // 设置线程池缓存队列的排序策略为FIFO,并且指定缓存队列大小
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(5);
        // 根据以上参数,创建线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,
                keepActiveTime,timeUnit,workQueue);
        // 在线程池中循环提交线程
        for (int i=1;i<=15;i++){
            // 创建线程类
            PoolThread pt = new PoolThread(i);
            // 调用线程池的execute方法创建线程
            executor.execute(pt);
            System.out.println("线程池中数目:"+
                    executor.getPoolSize()+"列队中等待执行的任务数:"+
                    executor.getQueue().size()+"已执行完的任务数目"+
                    executor.getCompletedTaskCount());
        }
        // 使用线程池后要关闭
        executor.shutdown();
    }
}
class PoolThread implements Runnable{
    // 创建一个任务编号
    private int num;
    // 构造器
    public PoolThread(int num){
        this.num = num;
    }
    @Override
    public void run() {
        System.out.println("任务"+num+"开始执行");
        try {
            // 沉睡5秒
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("任务"+num+"执行完毕");
    }
}

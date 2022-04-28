package 线程.线程高级.线程池;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 可周期性或者延迟执行任务
 */
public class TestScheduledThread {
    public static void main(String[] args) {
        // 创建10个核心线程数的线程池
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);
        // 周期性执行
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        },0,1, TimeUnit.SECONDS);
    }
}

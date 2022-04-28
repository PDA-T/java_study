package 线程.线程高级.批量线程控制;

import java.util.concurrent.Semaphore;

/**
 * 信号量
 * 拿到许可才能运行
 */
public class TestSemaphore {
    public static void main(String[] args) {
        // 创建5个许可证
        Semaphore semaphore = new Semaphore(5);
        // 创建线程类,初始化许可证
        SemaphoreThread spt = new SemaphoreThread(semaphore);
        // 循环启动8个线程
        for (int i=1;i<9;i++){
            new Thread(spt,"线程"+i).start();
        }
    }
}
class SemaphoreThread implements Runnable{
    // 创建许可证(信号量)
    private Semaphore semaphore;
    // 初始化信号量内许可证的值
    public SemaphoreThread(Semaphore semaphore){
        this.semaphore = semaphore;
    }
    @Override
    public void run() {
        try {
            // 线程等待许可证,拿到许可证才可继续执行
            semaphore.acquire();
            // 输出占用许可证的线程
            System.out.println(Thread.currentThread().getName()+"正在占用许可证");
            // 线程休眠两秒
            Thread.sleep(2000);
            // 输出释放许可证
            System.out.println(Thread.currentThread().getName()+"释放许可证");
            // 释放许可证
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

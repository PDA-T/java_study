package 线程.线程高级.批量线程控制;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 回环栅栏
 * 可以让一组线程等待至某个状态之后在全部同时执行
 */
public class TestCyclicBarrier {
    public static void main(String[] args) {
        // 实例化3个栅栏
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        // 实例化线程类
        CyclicBarrierThread cbt = new CyclicBarrierThread(cyclicBarrier);
        // 创建三个线程
        for (int i=1;i<4;i++){
            new Thread(cbt,"线程"+i).start();
        }
    }
}
class CyclicBarrierThread implements Runnable{
    // 创建栅栏
    private CyclicBarrier cyclicBarrier;
    // 初始化栅栏
    public CyclicBarrierThread(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            // 线程随机沉睡
            Thread.sleep((long) (Math.random()*1000));
            // 输出线程情况
            System.out.println(Thread.currentThread().getName()+"到达第一个同步点,"+
                    "已经有"+cyclicBarrier.getNumberWaiting()+"个线程到达这里");
            // 创建同步点,线程全部执行到这里之后一起向下运行
            cyclicBarrier.await();

            // 线程随机沉睡
            Thread.sleep((long) (Math.random()*1000));
            // 输出线程情况
            System.out.println(Thread.currentThread().getName()+"到达第二个同步点,"+
                    "已经有"+cyclicBarrier.getNumberWaiting()+"个线程到达这里");
            // 创建同步点,线程全部执行到这里之后一起向下运行
            cyclicBarrier.await();

            // 线程随机沉睡
            Thread.sleep((long) (Math.random()*1000));
            // 输出线程情况
            System.out.println(Thread.currentThread().getName()+"到达第三个同步点,"+
                    "已经有"+cyclicBarrier.getNumberWaiting()+"个线程到达这里");
            // 创建同步点,线程全部执行到这里之后一起向下运行
            cyclicBarrier.await();
            System.out.println("结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

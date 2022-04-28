package 线程.线程高级.线程通信;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 创建三条线程,在屏幕上按顺序打印ABCABC...
 */
public class ABCABCThread {
    public static void main(String[] args) {
        // 创建类实例
        ABCThread t = new ABCThread();
        // 创建并启动三条线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i<11;i++){
                    t.loopA(i);
                }
            }
        },"A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i<11;i++){
                    t.loopB(i);
                }
            }
        },"B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i<11;i++){
                    t.loopC(i);
                }
            }
        },"C").start();
    }
}
class ABCThread{
    // 创建一个标记,来判断执行的是哪个线程
    private int number = 1;
    // 创建一个锁
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    // A
    public void loopA(int totalLoop){
        // 加锁保护线程
        lock.lock();
        try {
            // 如果不等于1
            if (number != 1){
                // 阻塞当前线程
                condition1.await();
            }
            // \t为制表符
            System.out.println(Thread.currentThread().getName()+"\t"+totalLoop);
            // 修改标记
            number = 2;
            // 唤醒B线程
            condition2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 释放锁
            lock.unlock();
        }
    }
    // B
    public void loopB(int totalLoop){
        // 加锁保护线程
        lock.lock();
        try {
            // 如果不等于2
            if (number != 2){
                // 阻塞当前线程
                condition2.await();
            }
            // \t为制表符
            System.out.println(Thread.currentThread().getName()+"\t"+totalLoop);
            // 修改标记
            number = 3;
            // 唤醒C线程
            condition3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 释放锁
            lock.unlock();
        }
    }
    // C
    public void loopC(int totalLoop){
        // 加锁保护线程
        lock.lock();
        try {
            // 如果不等于3
            if (number != 3){
                // 阻塞当前线程
                condition3.await();
            }
            // \t为制表符
            System.out.println(Thread.currentThread().getName()+"\t"+totalLoop);
            // 修改标记
            number = 1;
            // 唤醒A线程
            condition1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 释放锁
            lock.unlock();
        }
    }
}

package 线程.线程高级;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁,JDK1.5新增的juc包内的功能
 * 如果资源竞争激烈,大量线程同时竞争,则使用Lock控制线程锁
 */
public class ReentrantLockClass {
    public static void main(String[] args) {
        // 创建实现接口的类对象
        SychronizedThread3 st = new SychronizedThread3();
        // 创建四个线程
        Thread t1 = new Thread(st,"线程1");
        t1.start();
        Thread t2 = new Thread(st,"线程2");
        t2.start();
        Thread t3 = new Thread(st,"线程3");
        t3.start();
        Thread t4 = new Thread(st,"线程4");
        t4.start();
    }
}
class SychronizedThread3 implements Runnable{
    private int ticketNumber = 100;// 一共100张票
    Lock lock = new ReentrantLock();// 创建一个Lock锁

    @Override
    public void run() {
        // 死循环
        while (true){
            // 在读取共享数据前,加上锁
            lock.lock();// 加锁
            try {
                // 如果总票大于0
                if (ticketNumber > 0){
                    try {
                        // 线程沉睡,起到放大线程安全问题的作用
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 输出卖票
                    System.out.println("线程:"+Thread.currentThread().getName()+"卖掉第"+
                            ticketNumber+"张票");
                    // 总票-1
                    ticketNumber--;
                }else {
                    // 跳出循环
                    break;
                }
            }catch (Exception e){
                System.out.println("意外退出");
                // 无论程序执行完毕还是意外退出,都会执行finally
            }finally {
                // 释放锁
                lock.unlock();
            }
        }
    }
}

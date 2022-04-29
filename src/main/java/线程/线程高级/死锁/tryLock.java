package 线程.线程高级.死锁;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 定时锁
 * 先判断锁是否被占用,之后在决定做什么
 */
public class tryLock {
    public static void main(String[] args) {
        System.out.println("开始");
        // 创建lock锁
        Lock lock = new ReentrantLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 无参tryLock不会等待
                    if (lock.tryLock(1, TimeUnit.SECONDS)){// 等待一秒
                        System.out.println("获取到了锁");
                        try {
                            // 循环输出线程名字
                            for (int i=0;i<5;i++){
                                System.out.println(Thread.currentThread().getName()+"--"+i);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }finally {
                            // 释放锁
                            lock.unlock();
                        }
                    }else {
                        System.out.println("没获取到锁");
                        // 终止方法
                        return;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 无参tryLock不会等待
                    if (lock.tryLock(1, TimeUnit.SECONDS)){// 等待一秒
                        System.out.println("获取到了锁");
                        try {
                            // 循环输出线程名字
                            for (int i=0;i<5;i++){
                                System.out.println(Thread.currentThread().getName()+"--"+i);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }finally {
                            // 释放锁
                            lock.unlock();
                        }
                    }else {
                        System.out.println("没获取到锁");
                        // 终止方法
                        return;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程2").start();
        System.out.println("结束");
    }
}

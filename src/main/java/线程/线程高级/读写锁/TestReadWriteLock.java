package 线程.线程高级.读写锁;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * 可以让多个线程同时读取,写入时无法读取
 * 当线程多,程序大,并且读取比写入多的时候使用读写锁
 */
public class TestReadWriteLock {
    public static void main(String[] args) {
        ReadWriteLockTest rwlt = new ReadWriteLockTest();
        // 创建十条线程读取
        for (int i=1;i<=10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 读取共享资源
                    rwlt.get();
                }
            },"A").start();
        }
        // 创建一条线程写入
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 写入
                rwlt.set(12);
            }
        },"B").start();
    }
}
class ReadWriteLockTest{
    // 定义一个线程间会共享的数据
    private int num = 0;
    // 创建读写锁
    ReadWriteLock rwl = new ReentrantReadWriteLock();
    // 定义读取方法
    public void get(){
        // 使用读锁
        rwl.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+":"+num);
        }finally {
            // 释放读锁
            rwl.readLock().unlock();
        }
    }
    // 定义写入方法
    public void set(int num){
        // 使用写锁
        rwl.writeLock().lock();
        try {
            // 修改共享值
            this.num = num;
        }finally {
            // 释放写锁
            rwl.writeLock().unlock();
        }
    }
}

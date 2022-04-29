package 线程.线程高级.读写锁;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 锁降级
 * 先获取写锁,在获取读锁,在释放写锁才可称为锁降级
 */
public class CachedData {
    public static void main(String[] args) {
        cachedData2 cd = new cachedData2();
        // 创建10条线程
        for (int i=1;i<=10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 更改新数据
                    cd.putCachedData(Thread.currentThread().getName()+"放入新数据");
                }
            },"t"+i).start();
        }
    }
}
class cachedData2{
    // 定义线程共享变量
    private String data = "原有值";
    // 定义标记变量,需要内存可见
    private volatile boolean isUpadte;
    // 拿到读写锁
    private ReadWriteLock rwl = new ReentrantReadWriteLock();
    // 放置数据到缓存方法
    public void putCachedData(String data){
        // 获取写锁前,先获取读锁(判断缓存数据是否是新的)
        rwl.readLock().lock();
        // 对标记进行判断,如果是过期的缓存
        if (!isUpadte){
            // 释放读锁(锁降级不能先获取读锁)
            rwl.readLock().unlock();
            // 获取写锁
            rwl.writeLock().lock();
            try {
                // 如果缓存数据不是最新的
                if (!isUpadte){
                    // 更新缓存
                    this.data = data;
                    // 更新标记
                    isUpadte = true;
                }
                // 获取读锁(此时有两个锁)
                rwl.readLock().lock();
            }finally {
                // 释放写锁(锁降级)
                rwl.writeLock().unlock();
            }
        }
        try {
            // 使用读锁获取缓存
            System.out.println("最新的缓存:"+this.data);
        }finally {
            // 释放读锁
            rwl.readLock().unlock();
        }
    }
}

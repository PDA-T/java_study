package 线程.线程高级.线程通信;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 新的线程通信类
 */
public class newValueObject {
    public static void main(String[] args) {
        // 实例化lock锁
        Lock lock = new ReentrantLock();
        // 实例化Condition对象
        Condition condition = lock.newCondition();
        // 实例化两个线程类
        P2 p = new P2(lock,condition);
        C2 c = new C2(lock,condition);
        // 启动两个线程
        p.start();
        c.start();
    }
}
/**
 * 模拟生产者和消费者之间的缓冲区
 */
class ThreadTest2{
    // 创建缓冲区
    public static String value = "";
}

/**
 * 实现生产者的线程类
 */
class P2 extends Thread{
    // 创建一把锁
    private Lock lock;
    private Condition condition;
    // 初始化锁对象
    public P2(Lock lock,Condition condition){
        this.lock = lock;
        this.condition = condition;
    }
    @Override
    public void run() {
        while (true){
            try {
                // JUC加锁
                lock.lock();
                // 作为生产者的基本功能,生产数据,判断缓冲区是否占满
                if (!ThreadTest2.value.equals("")){
                    // 缓冲区有数据不生产,让当前线程阻塞,释放同步锁
                    condition.await();
                }
                // 缓冲区为空,生产数据
                System.out.println("缓冲区没用数据,开始生产");
                // 在缓冲区里放入系统当前时间
                ThreadTest2.value = System.currentTimeMillis()+"";
                // 通知消费者线程消费数据
                condition.signal();
            }catch (Exception e){

            }finally {
                // JUC释放锁
                lock.unlock();
            }
        }
    }
}

/**
 * 实现消费者线程类
 */
class C2 extends Thread{
    // 创建一把锁
    private Lock lock;
    private Condition condition;
    // 初始化锁对象
    public C2(Lock lock,Condition condition){
        this.lock = lock;
        this.condition = condition;
    }
    @Override
    public void run() {
        while (true){
            try {
                // JUC加锁
                lock.lock();
                // 判断缓冲区是否有数据
                if (ThreadTest2.value.equals("")){
                    // 没有数据不消费,让当前线程阻塞,释放同步锁
                    condition.await();
                }
                // 缓冲区有数据,消费掉
                System.out.println("缓冲区有数据,消费数据");
                // 覆盖缓冲区数据
                ThreadTest2.value = "";
                // 消费后,通知生产者生产数据
                condition.signal();
            }catch (Exception e){

            }finally {
                // JUC释放锁
                lock.unlock();
            }
        }
    }
}

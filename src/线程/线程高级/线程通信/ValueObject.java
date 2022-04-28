package 线程.线程高级.线程通信;

public class ValueObject {
    public static void main(String[] args) {
        // 实例化lock锁
        Object lock = new Object();
        // 实例化两个线程类
        P p = new P(lock);
        C c = new C(lock);
        // 启动两个线程
        p.start();
        c.start();
    }
}

/**
 * 模拟生产者和消费者之间的缓冲区
 */
class ThreadTest{
    // 创建缓冲区
    public static String value = "";
}

/**
 * 实现生产者的线程类
 */
class P extends Thread{
    // 创建一把锁
    private Object lock;
    // 初始化锁对象
    public P(Object lock){
        this.lock = lock;
    }
    @Override
    public void run() {
        while (true){
            try {
                synchronized (lock){
                    // 作为生产者的基本功能,生产数据,判断缓冲区是否占满
                    if (!ThreadTest.value.equals("")){
                        // 缓冲区有数据不生产,让当前线程阻塞,释放同步锁
                        lock.wait();
                    }
                    // 缓冲区为空,生产数据
                    System.out.println("缓冲区没用数据,开始生产");
                    // 在缓冲区里放入系统当前时间
                    ThreadTest.value = System.currentTimeMillis()+"";
                    // 通知消费者线程消费数据
                    lock.notify();
                }
            }catch (Exception e){

            }
        }
    }
}

/**
 * 实现消费者线程类
 */
class C extends Thread{
    // 创建一把锁
    private Object lock;
    // 初始化锁对象
    public C(Object lock){
        this.lock = lock;
    }
    @Override
    public void run() {
        while (true){
            try {
                synchronized (lock){
                    // 判断缓冲区是否有数据
                    if (ThreadTest.value.equals("")){
                        // 没有数据不消费,让当前线程阻塞,释放同步锁
                        lock.wait();
                    }
                    // 缓冲区有数据,消费掉
                    System.out.println("缓冲区有数据,消费数据");
                    // 覆盖缓冲区数据
                    ThreadTest.value = "";
                    // 消费后,通知生产者生产数据
                    lock.notify();
                }
            }catch (Exception e){

            }
        }
    }
}
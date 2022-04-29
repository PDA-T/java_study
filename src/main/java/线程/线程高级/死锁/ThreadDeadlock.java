package 线程.线程高级.死锁;

/**
 * 普通顺序死锁
 */
public class ThreadDeadlock {
    public static void main(String[] args) {
        DeadLockDemo dl = new DeadLockDemo();
        new Thread(dl,"线程1").start();
        new Thread(dl,"线程2").start();
    }
}
class DeadLockDemo implements Runnable{
    // 定义两把锁
    private Object suo1 = new Object();
    private Object suo2 = new Object();
    @Override
    public void run() {
        // 调用m1和m2
        m1();
        m2();
    }
    public void m1(){
        // 拿到锁1,才能执行
        synchronized (suo1){
            System.out.println(Thread.currentThread().getName()+"的m1方法拿到了锁1还要锁2");
            // 拿到锁2
            synchronized (suo2){
                System.out.println(Thread.currentThread().getName()+"m1的方法拿到了锁1和锁2");
            }
        }
    }
    public void m2(){
        // 先拿锁2,和m1方法获取锁的顺序相反
        synchronized (suo2){
            System.out.println(Thread.currentThread().getName()+"的m2方法拿到了锁2还要锁1");
            // 拿到锁2
            synchronized (suo1){
                System.out.println(Thread.currentThread().getName()+"m2的方法拿到了锁2和锁1");
            }
        }
    }
}
package 线程.线程高级.死锁;

/**
 * 普通顺序死锁解决方法
 */
public class ThreadDeadlockCorrect {
    public static void main(String[] args) {
        DeadLockDemoCorrect dl = new DeadLockDemoCorrect();
        new Thread(dl,"线程1").start();
        new Thread(dl,"线程2").start();
    }
}
class DeadLockDemoCorrect implements Runnable{
    // 定义两把锁
    private Object suo1 = new Object();
    private Object suo2 = new Object();
    // 加一把锁,在极少的情况下才会用到,但是需要准备
    private Object jiaSuo = new Object();
    // 拿到原来的两把锁的hashcode
    int suo1HashCode = System.identityHashCode(suo1);
    int suo2HashCode = System.identityHashCode(suo2);
    // 定义加锁的规则:加锁的顺序和hash值的大小关联起来,如果hash值一样则加第三把锁(jiaSuo,极少出现)
    @Override
    public void run() {
        // 调用m1和m2
        m1();
        m2();
    }
    public void m1(){
        // 判断锁的hash值
        if (suo1HashCode>suo2HashCode){
            // 拿到锁1,才能执行
            synchronized (suo1){
                System.out.println(Thread.currentThread().getName()+"的m1方法拿到了锁1还要锁2");
                // 拿到锁2
                synchronized (suo2){
                    System.out.println(Thread.currentThread().getName()+"m1的方法拿到了锁1和锁2");
                }
            }
        }else if (suo1HashCode<suo2HashCode){
            // 先拿锁2,和m1方法获取锁的顺序相反
            synchronized (suo2){
                System.out.println(Thread.currentThread().getName()+"的m2方法拿到了锁2还要锁1");
                // 拿到锁2
                synchronized (suo1){
                    System.out.println(Thread.currentThread().getName()+"m2的方法拿到了锁2和锁1");
                }
            }
        }else {
            // 如果相等先加第三把锁(极少情况)
            synchronized (jiaSuo){
                // 拿到锁1,才能执行
                synchronized (suo1){
                    System.out.println(Thread.currentThread().getName()+"的m1方法拿到了锁1还要锁2");
                    // 拿到锁2
                    synchronized (suo2){
                        System.out.println(Thread.currentThread().getName()+"m1的方法拿到了锁1和锁2");
                    }
                }
            }
        }
    }
    public void m2(){
        // 判断锁的hash值
        if (suo1HashCode>suo2HashCode){
            // 拿到锁1,才能执行
            synchronized (suo1){
                System.out.println(Thread.currentThread().getName()+"的m1方法拿到了锁1还要锁2");
                // 拿到锁2
                synchronized (suo2){
                    System.out.println(Thread.currentThread().getName()+"m1的方法拿到了锁1和锁2");
                }
            }
        }else if (suo1HashCode<suo2HashCode){
            // 先拿锁2,和m1方法获取锁的顺序相反
            synchronized (suo2){
                System.out.println(Thread.currentThread().getName()+"的m2方法拿到了锁2还要锁1");
                // 拿到锁2
                synchronized (suo1){
                    System.out.println(Thread.currentThread().getName()+"m2的方法拿到了锁2和锁1");
                }
            }
        }else {
            // 如果相等先加第三把锁(极少情况)
            synchronized (jiaSuo){
                // 拿到锁1,才能执行
                synchronized (suo1){
                    System.out.println(Thread.currentThread().getName()+"的m1方法拿到了锁1还要锁2");
                    // 拿到锁2
                    synchronized (suo2){
                        System.out.println(Thread.currentThread().getName()+"m1的方法拿到了锁1和锁2");
                    }
                }
            }
        }
    }
}

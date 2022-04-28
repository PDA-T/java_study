package 线程.线程高级;

/**
 * yield方法能让当前线程由运行状态进入到就绪状态
 * 让其他等待中的线程获取执行权,但不保证一定可以获取执行权
 * (有可能退回就绪状态之后又抢到了运行权限)
 */
public class yieldTest {
    public static void main(String[] args) {
        Thread t1 = new PriorityThread("线程1");
        Thread t2 = new PriorityThread("线程2");
        t1.start();
        t2.start();
    }
}
class PriorityThread extends Thread{
    public PriorityThread(String name){
        // 将参数传入Thread类的构造器中
        super(name);
    }
    @Override
    public void run() {
        for (int i=1;i<=50;i++){
            System.out.println(this.getName()+":"+this.getPriority()+":"+i);
            // 如果变量能整除4
            if (i % 4 == 0){
                // 线程让步
                Thread.yield();
            }
        }
    }
}

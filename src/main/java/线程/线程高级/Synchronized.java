package 线程.线程高级;

/**
 * 同步代码块
 */
public class Synchronized {
    public static void main(String[] args) {
        // 创建实现接口的类对象
        SychronizedThread st = new SychronizedThread();
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
class SychronizedThread implements Runnable{
    private int ticketNumber = 100;// 一共100张票
    private Object obj = new Object();// 创建一个线程的同步锁

    @Override
    public void run() {
        // 死循环
        while (true){
            /*
             * 同步代码块
             * 线程锁可以传入任意对象的参数
             */
            synchronized (obj){
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
            }
        }
    }
}

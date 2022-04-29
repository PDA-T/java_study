package 线程.线程高级;

/**
 * 线程同步方法
 */
public class SynchronizedMethod {
    public static void main(String[] args) {
        // 创建实现接口的类对象
        SychronizedThread2 st = new SychronizedThread2();
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
class SychronizedThread2 implements Runnable {
    private static int ticketNumber = 100;// 一共100张票

    @Override
    public void run() {
        // 死循环
        while (true) {
            /*
             * 调用卖票方法,this表示调用run方法的对象(st)
             * 因为是同一把锁,调用两次也不会出错
             */
            this.sell();
//            this.sell();
            // 因为是同一把锁,调用两次也不会出现安全问题
//            SychronizedThread2.sells();
//            SychronizedThread2.sells();
            // 不是同一把锁,会出现安全问题
//            this.sell();
//            SychronizedThread2.sells();
        }
    }

    /**
     * 普通线程同步方法,线程锁为当前类的对象实例(st)
     */
    public synchronized void sell(){
        // 如果总票大于0
        if (ticketNumber > 0) {
            try {
                // 线程沉睡,起到放大线程安全问题的作用
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 输出卖票
            System.out.println("线程:" + Thread.currentThread().getName() + "卖掉第" +
                    ticketNumber + "张票");
            // 总票-1
            ticketNumber--;
        } else {
            // 结束程序
            System.exit(0);
        }
    }

    /**
     * 静态线程同步方法,线程锁为类的信息类(SychronizedThread2.class)
     */
    public synchronized static void sells(){
        // 如果总票大于0
        if (ticketNumber > 0) {
            try {
                // 线程沉睡,起到放大线程安全问题的作用
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 输出卖票
            System.out.println("线程:" + Thread.currentThread().getName() + "卖掉第" +
                    ticketNumber + "张票");
            // 总票-1
            ticketNumber--;
        } else {
            // 结束程序
            System.exit(0);
        }
    }
}


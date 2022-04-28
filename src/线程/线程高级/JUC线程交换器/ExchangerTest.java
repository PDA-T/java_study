package 线程.线程高级.JUC线程交换器;

import java.util.concurrent.Exchanger;

/**
 * 线程交换器
 * 一个线程在完成一定的事务后想与另一个线程交换数据
 * 则第一个先拿出数据的线程会一直等待第二个线程
 * 直到第二个线程拿着数据到来时才能彼此交换对应数据
 */
public class ExchangerTest {
    private static String msg1 = null;
    private static String msg2 = null;

    /**
     * 创建两个方法,他们分别会在两个线程里被调用
     * @param exchanger
     */
    public void a(Exchanger<String> exchanger){
        // 交换前,msg1的值
        msg1 = "1111111";
        // 输出值
        System.out.println("msg1:"+msg1);
        try {
            /*
             * 等待另一个线程到达此交换点（除非当前线程被中断），
             * 然后将给定的对象传送给该线程，并接收该线程的对象
             */
            msg1 = exchanger.exchange(msg1);
            System.out.println("调用a方法的线程交换完成");
            // 休眠线程
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void b(Exchanger<String> exchanger){
        // 交换前,msg2的值
        msg2 = "22222222";
        // 输出值
        System.out.println("msg2:"+msg2);
        try {
            /*
             * 等待另一个线程到达此交换点（除非当前线程被中断），
             * 然后将给定的对象传送给该线程，并接收该线程的对象
             */
            msg2 = exchanger.exchange(msg2);
            System.out.println("调用b方法的线程交换完成");
            // 休眠线程
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 实例化交换器
        final Exchanger<String> exchanger = new Exchanger<String>();
        // 实例化类对象
        final ExchangerTest exchangerTest = new ExchangerTest();
        // 创建两个线程
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                exchangerTest.a(exchanger);
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                exchangerTest.b(exchanger);
            }
        });
        t1.start();
        t2.start();
        try {
            // 保证t1执行完成后在执行下面的代码
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("交换完成后msg1:"+msg1);
        System.out.println("交换完成后msg2:"+msg2);
    }
}

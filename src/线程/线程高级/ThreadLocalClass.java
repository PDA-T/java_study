package 线程.线程高级;

/**
 * 使用局部变量实现线程同步
 * 同步领域和线程锁不一样
 * 可以隔离多个线程的数据共享,可以防止多个线程共享同一个数据造成的线程安全问题
 * 适用场景是多个线程需要使用一个变量,但这个变量的值不需要在各个线程间共享
 * 各个线程都只使用自己的这个变量值
 */
public class ThreadLocalClass {
    public static void main(String[] args) {
        // 创建实现接口的类对象
        SychronizedThread4 st = new SychronizedThread4();
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
class SychronizedThread4 implements Runnable{
    // 创建线程局部变量(副本),所有线程共享的变量数据
    ThreadLocal<Integer> ticketNumber = new ThreadLocal<Integer>(){
        /**
         * 覆盖初始化方法
         * @return
         */
        @Override
        protected Integer initialValue() {
            return 10;// ticketNumber的初始值
        }
    };

    @Override
    public void run() {
        // 死循环
        while (true){
            // 如果总票大于0
            if (ticketNumber.get() > 0){
                try {
                    // 线程沉睡,起到放大线程安全问题的作用
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 输出卖票
                System.out.println("线程:"+Thread.currentThread().getName()+"卖掉第"+
                        ticketNumber.get()+"张票");
                // 总票-1
                ticketNumber.set(ticketNumber.get()-1);
            }else {
                // 跳出循环
                break;
            }
        }
    }
}
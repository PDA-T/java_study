package 线程.线程高级;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 程序的原子性
 */
public class TestAtomicDemo {
    public static void main(String[] args) {
        AtomicThread at = new AtomicThread();
        // 创建10条线程
        for (int i=0;i<10;i++){
            new Thread(at,"线程"+i).start();
        }
    }
}
class AtomicThread implements Runnable{
    // 线程共享变量,要加内存可见关键字
//    private volatile int serialNum = 0;
    // 使用JUC工具包进行整型包装
    private AtomicInteger serialNum = new AtomicInteger(0);

    public int getSerialNum() {
        // 返回自增1
        return serialNum.getAndIncrement();
    }

    @Override
    public void run() {
        try {
            // 放大线程安全问题
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("显示serialNum:"+getSerialNum());
    }
}

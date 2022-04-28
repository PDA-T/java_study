package 线程.线程高级;

/**
 * 线程控制
 */
public class ControlThread {
    public static void main(String[] args) {
        Thread myt = new MyThread();
        myt.start();
//        // 给此线程打一个中断标记(myt线程)
//        myt.interrupt();
//        // 判断是否有标记
//        System.out.println(myt.isInterrupted());
//        // 第二次判断是否有标记
//        System.out.println(myt.isInterrupted());
//        // 给本线程打中断标记
//        Thread.currentThread().interrupt();
//        // 判断本线程是否有标记,会清除标记
//        System.out.println(Thread.interrupted());
//        // 判断本线程是否有标记,会清除标记
//        System.out.println(Thread.interrupted());
//        // 判断线程是否活跃
//        System.out.println(myt.isAlive());
        // 给此线程打中断标记
        myt.interrupt();
    }
}
class MyThread extends Thread{
    @Override
    public void run() {
        for (int i=0;i<100;i++){
            System.out.println(Thread.currentThread().getName()+"--"+i);
            // 判断线程是否被打中断标记
            if (Thread.currentThread().isInterrupted()){
                System.out.println("检测到中断标记");
                // 结束run方法,中断线程
                return;
            }
        }
    }
}

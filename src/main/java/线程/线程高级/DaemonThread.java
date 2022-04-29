package 线程.线程高级;

/**
 * 后台线程,辅助前台线程的服务提供,前台线程死亡后台线程也会死亡
 */
public class DaemonThread {
    public static void main(String[] args) {
        // 创建后台线程
        Thread daemonThread = new Daemon_Thread("后台子线程");
        // 设置为后台线程,必须要写在启动线程前
        daemonThread.setDaemon(true);
        // 启动线程
        daemonThread.start();
        try {
            // 主线程休眠
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 循环输出主线程名字
        for (int i=1;i<101;i++){
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
        // 创建前台线程并启动
        new ForegroundThread("前台线程").start();
    }
}

/**
 * 创建后台线程
 */
class Daemon_Thread extends Thread{
    public Daemon_Thread(String name){
        super(name);
    }

    @Override
    public void run() {
        for (int i=1;i<101;i++){
            try {
                // 线程休眠
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 输出线程名
            System.out.println(this.getName()+":"+i);
        }
    }
}

/**
 * 创建前台线程
 */
class ForegroundThread extends Thread{
    public ForegroundThread(String name){
        super(name);
    }

    @Override
    public void run() {
        for (int i=1;i<101;i++){
            try {
                // 线程休眠
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 输出线程名
            System.out.println(this.getName()+":"+i);
        }
    }
}

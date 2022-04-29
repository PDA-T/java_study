package 线程;

public class 线程同步 implements Runnable{
    int i=10;// 全部值
    @Override
    public void run() {
        //thre();// 调用线程锁方法
        while (true){// 无限循环
            synchronized (this){// 线程锁代码块
                if (i>0){// 如果值大于0执行
                    try {
                        Thread.sleep(300);// 休眠
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i);// 输出
                    i--;// 输出后值减1
                }
            }
        }
    }
    public synchronized void thre(){// 线程锁方法
        while (true){// 无限循环
            if (i>0){// 如果值大于0执行
                try {
                    Thread.sleep(300);// 休眠
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);// 输出
                i--;// 输出后值减1
            }
        }
    }
    public static void main(String[] args) {
        线程同步 x=new 线程同步();// 实例化类
        Thread t1=new Thread(x);t1.start();// 实例化线程
        Thread t2=new Thread(x);t2.start();// 实例化线程
        Thread t3=new Thread(x);t3.start();// 实例化线程
        Thread t4=new Thread(x);t4.start();// 实例化线程
    }
}

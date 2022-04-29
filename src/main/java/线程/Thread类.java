package 线程;

public class Thread类 {
    public static void main(String[] args) {
        Thread a=new ThreadA();// 父类实例化子类
        a.start();// 启动A线程
        Thread b=new ThreadB();// 父类实例化子类
        b.start();// 启动B线程
    }
}
class ThreadA extends Thread{// 定义线程类
    @Override
    public void run() {// 创建run方法
        for (int i=0;i<=100;i++){// 创建循环
            System.out.println(i);// 输出值
            try {
                Thread.sleep(1000);// 休眠
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class ThreadB extends Thread{
    @Override
    public void run() {
        for (char i='a';i<='z';i++){// 创建循环，char类型兼容int类型
            System.out.println(i);// 输出值
            try {
                Thread.sleep(1000);// 休眠
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

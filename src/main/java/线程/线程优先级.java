package 线程;

public class 线程优先级 {
    public static void main(String[] args) {
        for (int i=0;i<10;i++){
            MyThread t1=new MyThread("加","+");t1.start();// 实例化线程
            t1.setPriority(1);// 设置优先级1~10
            MyThread t2=new MyThread("减","-");t2.start();// 实例化线程
            t2.setPriority(2);// 设置优先级1~10
            MyThread t3=new MyThread("乘","*");t3.start();// 实例化线程
            t3.setPriority(3);// 设置优先级1~10
            MyThread t4=new MyThread("除","/");t4.start();// 实例化线程
            t4.setPriority(10);// 设置优先级1~10
        }
    }
}
class MyThread extends Thread{
    String name;// 名字
    String ouput;// 输出
    MyThread(String name,String ouput){// 构造方法
        this.name=name;// 初始化
        this.ouput=ouput;// 初始化
    }
    @Override
    public void run() {
        System.out.println(name+":"+ouput);// 输出
    }
}

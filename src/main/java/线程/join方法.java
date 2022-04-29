package 线程;

import javax.swing.*;
import java.awt.*;

public class join方法 extends JFrame {
    static JProgressBar jp=new JProgressBar();// 创建进度条
    static JProgressBar jp2=new JProgressBar();// 创建进度条
    static Thread tb=new Thread(new Threadt());// 创建线程
    public static void main(String[] args) {
        JFrame f=new JFrame();// 创建窗口
        Thread ta=new Threads();// 创建进程
        Container a=f.getContentPane();// 获取窗口面板
        f.setSize(300,200);// 设置窗口大小
        f.setTitle("join");// 设置窗口名字
        f.setLocationRelativeTo(null);// 设置窗口居中
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);// 设置窗口关闭即结束运行
        f.setVisible(true);// 设置窗口可见
        f.setLayout(new GridLayout(6,0));// 设置网格布局
        jp.setStringPainted(true);// 设置进度条可见值
        jp2.setStringPainted(true);// 设置进度条可见值
        a.add(jp);// 添加进度条
        a.add(jp2);// 添加进度条
        ta.start();// 启动进程
        tb.start();// 启动线程
    }
}
class Threads extends Thread{
    @Override
    public void run() {
        //Thread tb=new Thread(new Threadb());// 创建线程
        join方法 j=new join方法();// 实例化主类
        for (int i=0;i<=100;i++){// 循环100次
            j.jp.setValue(i);// 设置进度条的值
            try {
                Thread.sleep(100);// 设置休眠
                if (j.jp.getValue()==50){// 如果进度条值等于50
                    // join方法可以传入参数,传入10则只阻塞10毫秒
                    j.tb.join();// 优先执行
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Threadt implements Runnable{
    @Override
    public void run() {
        for (int i=0;i<=100;i++){// 循环100次
            new join方法().jp2.setValue(i);// 设置进度条的值
            try {
                Thread.sleep(100);// 设置休眠
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
package 线程;

import javax.swing.*;
import java.awt.*;

public class Runnable接口 extends JFrame implements Runnable{// 继承窗口类同时实现线程
    static Label l=new Label();// 创建标签
    public static void main(String[] args) {
        Thread t=new Thread(new Runnable接口());// 创建线程
        t.start();// 启动线程
        JFrame j=new JFrame();// 创建窗口面板
        Container a=j.getContentPane();// 获取窗口面板
        l.setText("Thread");// 设置标签名字
        j.setTitle("Runnable");// 设置窗口名字
        j.setSize(300,200);// 设置窗口大小
        j.setLocationRelativeTo(null);// 设置窗口居中
        j.setDefaultCloseOperation(EXIT_ON_CLOSE);// 设置窗口关闭即结束运行
        j.setVisible(true);// 设置窗口可见
        j.setLayout(null);// 设置窗口空布局
        l.setBounds(10,10,40,10);// 设置标签位置大小
        a.add(l);// 添加标签
        int i=0;// 设置循环次数
        for (;i<300;i++){// 设置标签循环
            l.setBounds(i,l.getY(),40,10);// 设置标签位置大小
            try {
                Thread.sleep(100);// 设置休眠
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void run() {// 线程类
        for (int i=0;i<300;i++){
            l.setBounds(l.getX(),i,40,10);// 设置标签位置大小
            try {
                Thread.sleep(100);// 设置休眠
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

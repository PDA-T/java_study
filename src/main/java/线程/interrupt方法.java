package 线程;

import javax.swing.*;
import java.awt.*;

public class interrupt方法 extends JFrame {
    static JProgressBar jp = new JProgressBar();// 创建进度条
    public static void main(String[] args) {
        Thread th;// 创建线程
        JFrame j = new JFrame();// 创建窗体
        Container c = j.getContentPane();// 获取窗口面板
        j.setSize(300, 200);// 设置窗口大小
        j.setLocationRelativeTo(null);// 设置居中
        j.setDefaultCloseOperation(EXIT_ON_CLOSE);// 设置关闭即结束运行
        j.setVisible(true);// 设置可见
        j.setTitle("interrupt");// 设置名字
        j.setLayout(new GridLayout(5, 0));// 设置流布局
        jp.setStringPainted(true);// 设置进度可见
        c.add(jp);// 添加进度条
        th=new Thread(){// 使用匿名内部类创建线程
            @Override
            public void run() {
                try {// try包括for循环可以暂停，不包括无法暂停
                for (int i=0;i<101;i++) {// 循环
                    jp.setValue(i);// 设置进度条进度
                    if (i == 50) {// 如果到达50
                        this.interrupt();// 中断线程
                    }
                    Thread.sleep(100);// 设置休眠
                    }
                }catch (InterruptedException e){
                    System.out.println("中断");// 控制台输出中断
                }
            }
        };
        th.start();// 启动线程
    }
}
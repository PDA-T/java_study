package �߳�;

import javax.swing.*;
import java.awt.*;

public class join���� extends JFrame {
    static JProgressBar jp=new JProgressBar();// ����������
    static JProgressBar jp2=new JProgressBar();// ����������
    static Thread tb=new Thread(new Threadt());// �����߳�
    public static void main(String[] args) {
        JFrame f=new JFrame();// ��������
        Thread ta=new Threads();// ��������
        Container a=f.getContentPane();// ��ȡ�������
        f.setSize(300,200);// ���ô��ڴ�С
        f.setTitle("join");// ���ô�������
        f.setLocationRelativeTo(null);// ���ô��ھ���
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);// ���ô��ڹرռ���������
        f.setVisible(true);// ���ô��ڿɼ�
        f.setLayout(new GridLayout(6,0));// �������񲼾�
        jp.setStringPainted(true);// ���ý������ɼ�ֵ
        jp2.setStringPainted(true);// ���ý������ɼ�ֵ
        a.add(jp);// ��ӽ�����
        a.add(jp2);// ��ӽ�����
        ta.start();// ��������
        tb.start();// �����߳�
    }
}
class Threads extends Thread{
    @Override
    public void run() {
        //Thread tb=new Thread(new Threadb());// �����߳�
        join���� j=new join����();// ʵ��������
        for (int i=0;i<=100;i++){// ѭ��100��
            j.jp.setValue(i);// ���ý�������ֵ
            try {
                Thread.sleep(100);// ��������
                if (j.jp.getValue()==50){// ���������ֵ����50
                    // join�������Դ������,����10��ֻ����10����
                    j.tb.join();// ����ִ��
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
        for (int i=0;i<=100;i++){// ѭ��100��
            new join����().jp2.setValue(i);// ���ý�������ֵ
            try {
                Thread.sleep(100);// ��������
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
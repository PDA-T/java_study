package �߳�;

import javax.swing.*;
import java.awt.*;

public class Runnable�ӿ� extends JFrame implements Runnable{// �̳д�����ͬʱʵ���߳�
    static Label l=new Label();// ������ǩ
    public static void main(String[] args) {
        Thread t=new Thread(new Runnable�ӿ�());// �����߳�
        t.start();// �����߳�
        JFrame j=new JFrame();// �����������
        Container a=j.getContentPane();// ��ȡ�������
        l.setText("Thread");// ���ñ�ǩ����
        j.setTitle("Runnable");// ���ô�������
        j.setSize(300,200);// ���ô��ڴ�С
        j.setLocationRelativeTo(null);// ���ô��ھ���
        j.setDefaultCloseOperation(EXIT_ON_CLOSE);// ���ô��ڹرռ���������
        j.setVisible(true);// ���ô��ڿɼ�
        j.setLayout(null);// ���ô��ڿղ���
        l.setBounds(10,10,40,10);// ���ñ�ǩλ�ô�С
        a.add(l);// ��ӱ�ǩ
        int i=0;// ����ѭ������
        for (;i<300;i++){// ���ñ�ǩѭ��
            l.setBounds(i,l.getY(),40,10);// ���ñ�ǩλ�ô�С
            try {
                Thread.sleep(100);// ��������
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void run() {// �߳���
        for (int i=0;i<300;i++){
            l.setBounds(l.getX(),i,40,10);// ���ñ�ǩλ�ô�С
            try {
                Thread.sleep(100);// ��������
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

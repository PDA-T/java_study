package �߳�;

import javax.swing.*;
import java.awt.*;

public class interrupt���� extends JFrame {
    static JProgressBar jp = new JProgressBar();// ����������
    public static void main(String[] args) {
        Thread th;// �����߳�
        JFrame j = new JFrame();// ��������
        Container c = j.getContentPane();// ��ȡ�������
        j.setSize(300, 200);// ���ô��ڴ�С
        j.setLocationRelativeTo(null);// ���þ���
        j.setDefaultCloseOperation(EXIT_ON_CLOSE);// ���ùرռ���������
        j.setVisible(true);// ���ÿɼ�
        j.setTitle("interrupt");// ��������
        j.setLayout(new GridLayout(5, 0));// ����������
        jp.setStringPainted(true);// ���ý��ȿɼ�
        c.add(jp);// ��ӽ�����
        th=new Thread(){// ʹ�������ڲ��ഴ���߳�
            @Override
            public void run() {
                try {// try����forѭ��������ͣ���������޷���ͣ
                for (int i=0;i<101;i++) {// ѭ��
                    jp.setValue(i);// ���ý���������
                    if (i == 50) {// �������50
                        this.interrupt();// �ж��߳�
                    }
                    Thread.sleep(100);// ��������
                    }
                }catch (InterruptedException e){
                    System.out.println("�ж�");// ����̨����ж�
                }
            }
        };
        th.start();// �����߳�
    }
}
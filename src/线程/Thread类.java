package �߳�;

public class Thread�� {
    public static void main(String[] args) {
        Thread a=new ThreadA();// ����ʵ��������
        a.start();// ����A�߳�
        Thread b=new ThreadB();// ����ʵ��������
        b.start();// ����B�߳�
    }
}
class ThreadA extends Thread{// �����߳���
    @Override
    public void run() {// ����run����
        for (int i=0;i<=100;i++){// ����ѭ��
            System.out.println(i);// ���ֵ
            try {
                Thread.sleep(1000);// ����
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class ThreadB extends Thread{
    @Override
    public void run() {
        for (char i='a';i<='z';i++){// ����ѭ����char���ͼ���int����
            System.out.println(i);// ���ֵ
            try {
                Thread.sleep(1000);// ����
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

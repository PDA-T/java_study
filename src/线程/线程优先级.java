package �߳�;

public class �߳����ȼ� {
    public static void main(String[] args) {
        for (int i=0;i<10;i++){
            MyThread t1=new MyThread("��","+");t1.start();// ʵ�����߳�
            t1.setPriority(1);// �������ȼ�1~10
            MyThread t2=new MyThread("��","-");t2.start();// ʵ�����߳�
            t2.setPriority(2);// �������ȼ�1~10
            MyThread t3=new MyThread("��","*");t3.start();// ʵ�����߳�
            t3.setPriority(3);// �������ȼ�1~10
            MyThread t4=new MyThread("��","/");t4.start();// ʵ�����߳�
            t4.setPriority(10);// �������ȼ�1~10
        }
    }
}
class MyThread extends Thread{
    String name;// ����
    String ouput;// ���
    MyThread(String name,String ouput){// ���췽��
        this.name=name;// ��ʼ��
        this.ouput=ouput;// ��ʼ��
    }
    @Override
    public void run() {
        System.out.println(name+":"+ouput);// ���
    }
}

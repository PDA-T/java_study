package �߳�;

public class �߳�ͬ�� implements Runnable{
    int i=10;// ȫ��ֵ
    @Override
    public void run() {
        //thre();// �����߳�������
        while (true){// ����ѭ��
            synchronized (this){// �߳��������
                if (i>0){// ���ֵ����0ִ��
                    try {
                        Thread.sleep(300);// ����
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i);// ���
                    i--;// �����ֵ��1
                }
            }
        }
    }
    public synchronized void thre(){// �߳�������
        while (true){// ����ѭ��
            if (i>0){// ���ֵ����0ִ��
                try {
                    Thread.sleep(300);// ����
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);// ���
                i--;// �����ֵ��1
            }
        }
    }
    public static void main(String[] args) {
        �߳�ͬ�� x=new �߳�ͬ��();// ʵ������
        Thread t1=new Thread(x);t1.start();// ʵ�����߳�
        Thread t2=new Thread(x);t2.start();// ʵ�����߳�
        Thread t3=new Thread(x);t3.start();// ʵ�����߳�
        Thread t4=new Thread(x);t4.start();// ʵ�����߳�
    }
}

package �߳�.�̸߳߼�;

/**
 * �߳̿���
 */
public class ControlThread {
    public static void main(String[] args) {
        Thread myt = new MyThread();
        myt.start();
//        // �����̴߳�һ���жϱ��(myt�߳�)
//        myt.interrupt();
//        // �ж��Ƿ��б��
//        System.out.println(myt.isInterrupted());
//        // �ڶ����ж��Ƿ��б��
//        System.out.println(myt.isInterrupted());
//        // �����̴߳��жϱ��
//        Thread.currentThread().interrupt();
//        // �жϱ��߳��Ƿ��б��,��������
//        System.out.println(Thread.interrupted());
//        // �жϱ��߳��Ƿ��б��,��������
//        System.out.println(Thread.interrupted());
//        // �ж��߳��Ƿ��Ծ
//        System.out.println(myt.isAlive());
        // �����̴߳��жϱ��
        myt.interrupt();
    }
}
class MyThread extends Thread{
    @Override
    public void run() {
        for (int i=0;i<100;i++){
            System.out.println(Thread.currentThread().getName()+"--"+i);
            // �ж��߳��Ƿ񱻴��жϱ��
            if (Thread.currentThread().isInterrupted()){
                System.out.println("��⵽�жϱ��");
                // ����run����,�ж��߳�
                return;
            }
        }
    }
}

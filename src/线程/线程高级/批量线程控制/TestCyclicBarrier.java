package �߳�.�̸߳߼�.�����߳̿���;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * �ػ�դ��
 * ������һ���̵߳ȴ���ĳ��״̬֮����ȫ��ͬʱִ��
 */
public class TestCyclicBarrier {
    public static void main(String[] args) {
        // ʵ����3��դ��
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        // ʵ�����߳���
        CyclicBarrierThread cbt = new CyclicBarrierThread(cyclicBarrier);
        // ���������߳�
        for (int i=1;i<4;i++){
            new Thread(cbt,"�߳�"+i).start();
        }
    }
}
class CyclicBarrierThread implements Runnable{
    // ����դ��
    private CyclicBarrier cyclicBarrier;
    // ��ʼ��դ��
    public CyclicBarrierThread(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            // �߳������˯
            Thread.sleep((long) (Math.random()*1000));
            // ����߳����
            System.out.println(Thread.currentThread().getName()+"�����һ��ͬ����,"+
                    "�Ѿ���"+cyclicBarrier.getNumberWaiting()+"���̵߳�������");
            // ����ͬ����,�߳�ȫ��ִ�е�����֮��һ����������
            cyclicBarrier.await();

            // �߳������˯
            Thread.sleep((long) (Math.random()*1000));
            // ����߳����
            System.out.println(Thread.currentThread().getName()+"����ڶ���ͬ����,"+
                    "�Ѿ���"+cyclicBarrier.getNumberWaiting()+"���̵߳�������");
            // ����ͬ����,�߳�ȫ��ִ�е�����֮��һ����������
            cyclicBarrier.await();

            // �߳������˯
            Thread.sleep((long) (Math.random()*1000));
            // ����߳����
            System.out.println(Thread.currentThread().getName()+"���������ͬ����,"+
                    "�Ѿ���"+cyclicBarrier.getNumberWaiting()+"���̵߳�������");
            // ����ͬ����,�߳�ȫ��ִ�е�����֮��һ����������
            cyclicBarrier.await();
            System.out.println("����");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

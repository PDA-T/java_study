package �߳�.�̸߳߼�.�����߳̿���;

import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {
    public static void main(String[] args) {
        // ������������ʼ��50��ֵ,����������ֵ����0ʱ���̲߳Żᱻ�ͷ�
        CountDownLatch latch = new CountDownLatch(50);
        // ʵ�����߳���
        CountDownLatchThread cdlt = new CountDownLatchThread(latch);
        // �߳̿�ʼ��ʱ��
        long start = System.currentTimeMillis();
        // ����50���߳�
        for (int i=1;i<51;i++){
            new Thread(cdlt,"�߳�"+i).start();
        }
        try {
            // �������������await�����ͻ��������߳�
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // �߳̽�����ʱ��
        long end = System.currentTimeMillis();
        // ���һ�������˶೤ʱ��
        System.out.println("һ��������:"+((end-start)/1000)+"��");
    }
}
class CountDownLatchThread implements Runnable{
    // ���������������Ĺ��������
    private CountDownLatch latch;
    // ��ʼ������������
    public CountDownLatchThread(CountDownLatch latch){
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            // ѭ��50000��
            for (int i=0;i<50000;i++){
                // ���iΪż��
                if (i%2==0){
                    // ����߳���
                    System.out.println(Thread.currentThread().getName()+"ż��Ϊ:"+i);
                }
            }
        }finally {
            // �ü���������(CountDownLatch)��ļ�����-1
            latch.countDown();
        }
    }
}

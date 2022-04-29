package �߳�.�̸߳߼�.�����߳̿���;

import java.util.concurrent.Semaphore;

/**
 * �ź���
 * �õ���ɲ�������
 */
public class TestSemaphore {
    public static void main(String[] args) {
        // ����5�����֤
        Semaphore semaphore = new Semaphore(5);
        // �����߳���,��ʼ�����֤
        SemaphoreThread spt = new SemaphoreThread(semaphore);
        // ѭ������8���߳�
        for (int i=1;i<9;i++){
            new Thread(spt,"�߳�"+i).start();
        }
    }
}
class SemaphoreThread implements Runnable{
    // �������֤(�ź���)
    private Semaphore semaphore;
    // ��ʼ���ź��������֤��ֵ
    public SemaphoreThread(Semaphore semaphore){
        this.semaphore = semaphore;
    }
    @Override
    public void run() {
        try {
            // �̵߳ȴ����֤,�õ����֤�ſɼ���ִ��
            semaphore.acquire();
            // ���ռ�����֤���߳�
            System.out.println(Thread.currentThread().getName()+"����ռ�����֤");
            // �߳���������
            Thread.sleep(2000);
            // ����ͷ����֤
            System.out.println(Thread.currentThread().getName()+"�ͷ����֤");
            // �ͷ����֤
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package �߳�.�̸߳߼�;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * �����ԭ����
 */
public class TestAtomicDemo {
    public static void main(String[] args) {
        AtomicThread at = new AtomicThread();
        // ����10���߳�
        for (int i=0;i<10;i++){
            new Thread(at,"�߳�"+i).start();
        }
    }
}
class AtomicThread implements Runnable{
    // �̹߳������,Ҫ���ڴ�ɼ��ؼ���
//    private volatile int serialNum = 0;
    // ʹ��JUC���߰��������Ͱ�װ
    private AtomicInteger serialNum = new AtomicInteger(0);

    public int getSerialNum() {
        // ��������1
        return serialNum.getAndIncrement();
    }

    @Override
    public void run() {
        try {
            // �Ŵ��̰߳�ȫ����
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("��ʾserialNum:"+getSerialNum());
    }
}

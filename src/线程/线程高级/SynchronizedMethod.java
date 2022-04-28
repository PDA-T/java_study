package �߳�.�̸߳߼�;

/**
 * �߳�ͬ������
 */
public class SynchronizedMethod {
    public static void main(String[] args) {
        // ����ʵ�ֽӿڵ������
        SychronizedThread2 st = new SychronizedThread2();
        // �����ĸ��߳�
        Thread t1 = new Thread(st,"�߳�1");
        t1.start();
        Thread t2 = new Thread(st,"�߳�2");
        t2.start();
        Thread t3 = new Thread(st,"�߳�3");
        t3.start();
        Thread t4 = new Thread(st,"�߳�4");
        t4.start();
    }
}
class SychronizedThread2 implements Runnable {
    private static int ticketNumber = 100;// һ��100��Ʊ

    @Override
    public void run() {
        // ��ѭ��
        while (true) {
            /*
             * ������Ʊ����,this��ʾ����run�����Ķ���(st)
             * ��Ϊ��ͬһ����,��������Ҳ�������
             */
            this.sell();
//            this.sell();
            // ��Ϊ��ͬһ����,��������Ҳ������ְ�ȫ����
//            SychronizedThread2.sells();
//            SychronizedThread2.sells();
            // ����ͬһ����,����ְ�ȫ����
//            this.sell();
//            SychronizedThread2.sells();
        }
    }

    /**
     * ��ͨ�߳�ͬ������,�߳���Ϊ��ǰ��Ķ���ʵ��(st)
     */
    public synchronized void sell(){
        // �����Ʊ����0
        if (ticketNumber > 0) {
            try {
                // �̳߳�˯,�𵽷Ŵ��̰߳�ȫ���������
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // �����Ʊ
            System.out.println("�߳�:" + Thread.currentThread().getName() + "������" +
                    ticketNumber + "��Ʊ");
            // ��Ʊ-1
            ticketNumber--;
        } else {
            // ��������
            System.exit(0);
        }
    }

    /**
     * ��̬�߳�ͬ������,�߳���Ϊ�����Ϣ��(SychronizedThread2.class)
     */
    public synchronized static void sells(){
        // �����Ʊ����0
        if (ticketNumber > 0) {
            try {
                // �̳߳�˯,�𵽷Ŵ��̰߳�ȫ���������
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // �����Ʊ
            System.out.println("�߳�:" + Thread.currentThread().getName() + "������" +
                    ticketNumber + "��Ʊ");
            // ��Ʊ-1
            ticketNumber--;
        } else {
            // ��������
            System.exit(0);
        }
    }
}


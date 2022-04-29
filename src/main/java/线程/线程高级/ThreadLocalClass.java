package �߳�.�̸߳߼�;

/**
 * ʹ�þֲ�����ʵ���߳�ͬ��
 * ͬ��������߳�����һ��
 * ���Ը������̵߳����ݹ���,���Է�ֹ����̹߳���ͬһ��������ɵ��̰߳�ȫ����
 * ���ó����Ƕ���߳���Ҫʹ��һ������,�����������ֵ����Ҫ�ڸ����̼߳乲��
 * �����̶߳�ֻʹ���Լ����������ֵ
 */
public class ThreadLocalClass {
    public static void main(String[] args) {
        // ����ʵ�ֽӿڵ������
        SychronizedThread4 st = new SychronizedThread4();
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
class SychronizedThread4 implements Runnable{
    // �����ֲ߳̾�����(����),�����̹߳���ı�������
    ThreadLocal<Integer> ticketNumber = new ThreadLocal<Integer>(){
        /**
         * ���ǳ�ʼ������
         * @return
         */
        @Override
        protected Integer initialValue() {
            return 10;// ticketNumber�ĳ�ʼֵ
        }
    };

    @Override
    public void run() {
        // ��ѭ��
        while (true){
            // �����Ʊ����0
            if (ticketNumber.get() > 0){
                try {
                    // �̳߳�˯,�𵽷Ŵ��̰߳�ȫ���������
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // �����Ʊ
                System.out.println("�߳�:"+Thread.currentThread().getName()+"������"+
                        ticketNumber.get()+"��Ʊ");
                // ��Ʊ-1
                ticketNumber.set(ticketNumber.get()-1);
            }else {
                // ����ѭ��
                break;
            }
        }
    }
}
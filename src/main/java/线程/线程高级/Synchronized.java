package �߳�.�̸߳߼�;

/**
 * ͬ�������
 */
public class Synchronized {
    public static void main(String[] args) {
        // ����ʵ�ֽӿڵ������
        SychronizedThread st = new SychronizedThread();
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
class SychronizedThread implements Runnable{
    private int ticketNumber = 100;// һ��100��Ʊ
    private Object obj = new Object();// ����һ���̵߳�ͬ����

    @Override
    public void run() {
        // ��ѭ��
        while (true){
            /*
             * ͬ�������
             * �߳������Դ����������Ĳ���
             */
            synchronized (obj){
                // �����Ʊ����0
                if (ticketNumber > 0){
                    try {
                        // �̳߳�˯,�𵽷Ŵ��̰߳�ȫ���������
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // �����Ʊ
                    System.out.println("�߳�:"+Thread.currentThread().getName()+"������"+
                            ticketNumber+"��Ʊ");
                    // ��Ʊ-1
                    ticketNumber--;
                }else {
                    // ����ѭ��
                    break;
                }
            }
        }
    }
}

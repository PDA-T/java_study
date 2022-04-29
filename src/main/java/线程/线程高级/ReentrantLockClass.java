package �߳�.�̸߳߼�;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ������,JDK1.5������juc���ڵĹ���
 * �����Դ��������,�����߳�ͬʱ����,��ʹ��Lock�����߳���
 */
public class ReentrantLockClass {
    public static void main(String[] args) {
        // ����ʵ�ֽӿڵ������
        SychronizedThread3 st = new SychronizedThread3();
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
class SychronizedThread3 implements Runnable{
    private int ticketNumber = 100;// һ��100��Ʊ
    Lock lock = new ReentrantLock();// ����һ��Lock��

    @Override
    public void run() {
        // ��ѭ��
        while (true){
            // �ڶ�ȡ��������ǰ,������
            lock.lock();// ����
            try {
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
            }catch (Exception e){
                System.out.println("�����˳�");
                // ���۳���ִ����ϻ��������˳�,����ִ��finally
            }finally {
                // �ͷ���
                lock.unlock();
            }
        }
    }
}

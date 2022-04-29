package �߳�.�̸߳߼�.�߳�ͨ��;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ���������߳�,����Ļ�ϰ�˳���ӡABCABC...
 */
public class ABCABCThread {
    public static void main(String[] args) {
        // ������ʵ��
        ABCThread t = new ABCThread();
        // ���������������߳�
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i<11;i++){
                    t.loopA(i);
                }
            }
        },"A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i<11;i++){
                    t.loopB(i);
                }
            }
        },"B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i<11;i++){
                    t.loopC(i);
                }
            }
        },"C").start();
    }
}
class ABCThread{
    // ����һ�����,���ж�ִ�е����ĸ��߳�
    private int number = 1;
    // ����һ����
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    // A
    public void loopA(int totalLoop){
        // ���������߳�
        lock.lock();
        try {
            // ���������1
            if (number != 1){
                // ������ǰ�߳�
                condition1.await();
            }
            // \tΪ�Ʊ��
            System.out.println(Thread.currentThread().getName()+"\t"+totalLoop);
            // �޸ı��
            number = 2;
            // ����B�߳�
            condition2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // �ͷ���
            lock.unlock();
        }
    }
    // B
    public void loopB(int totalLoop){
        // ���������߳�
        lock.lock();
        try {
            // ���������2
            if (number != 2){
                // ������ǰ�߳�
                condition2.await();
            }
            // \tΪ�Ʊ��
            System.out.println(Thread.currentThread().getName()+"\t"+totalLoop);
            // �޸ı��
            number = 3;
            // ����C�߳�
            condition3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // �ͷ���
            lock.unlock();
        }
    }
    // C
    public void loopC(int totalLoop){
        // ���������߳�
        lock.lock();
        try {
            // ���������3
            if (number != 3){
                // ������ǰ�߳�
                condition3.await();
            }
            // \tΪ�Ʊ��
            System.out.println(Thread.currentThread().getName()+"\t"+totalLoop);
            // �޸ı��
            number = 1;
            // ����A�߳�
            condition1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // �ͷ���
            lock.unlock();
        }
    }
}

package �߳�.�̸߳߼�.�߳�ͨ��;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * �µ��߳�ͨ����
 */
public class newValueObject {
    public static void main(String[] args) {
        // ʵ����lock��
        Lock lock = new ReentrantLock();
        // ʵ����Condition����
        Condition condition = lock.newCondition();
        // ʵ���������߳���
        P2 p = new P2(lock,condition);
        C2 c = new C2(lock,condition);
        // ���������߳�
        p.start();
        c.start();
    }
}
/**
 * ģ�������ߺ�������֮��Ļ�����
 */
class ThreadTest2{
    // ����������
    public static String value = "";
}

/**
 * ʵ�������ߵ��߳���
 */
class P2 extends Thread{
    // ����һ����
    private Lock lock;
    private Condition condition;
    // ��ʼ��������
    public P2(Lock lock,Condition condition){
        this.lock = lock;
        this.condition = condition;
    }
    @Override
    public void run() {
        while (true){
            try {
                // JUC����
                lock.lock();
                // ��Ϊ�����ߵĻ�������,��������,�жϻ������Ƿ�ռ��
                if (!ThreadTest2.value.equals("")){
                    // �����������ݲ�����,�õ�ǰ�߳�����,�ͷ�ͬ����
                    condition.await();
                }
                // ������Ϊ��,��������
                System.out.println("������û������,��ʼ����");
                // �ڻ����������ϵͳ��ǰʱ��
                ThreadTest2.value = System.currentTimeMillis()+"";
                // ֪ͨ�������߳���������
                condition.signal();
            }catch (Exception e){

            }finally {
                // JUC�ͷ���
                lock.unlock();
            }
        }
    }
}

/**
 * ʵ���������߳���
 */
class C2 extends Thread{
    // ����һ����
    private Lock lock;
    private Condition condition;
    // ��ʼ��������
    public C2(Lock lock,Condition condition){
        this.lock = lock;
        this.condition = condition;
    }
    @Override
    public void run() {
        while (true){
            try {
                // JUC����
                lock.lock();
                // �жϻ������Ƿ�������
                if (ThreadTest2.value.equals("")){
                    // û�����ݲ�����,�õ�ǰ�߳�����,�ͷ�ͬ����
                    condition.await();
                }
                // ������������,���ѵ�
                System.out.println("������������,��������");
                // ���ǻ���������
                ThreadTest2.value = "";
                // ���Ѻ�,֪ͨ��������������
                condition.signal();
            }catch (Exception e){

            }finally {
                // JUC�ͷ���
                lock.unlock();
            }
        }
    }
}

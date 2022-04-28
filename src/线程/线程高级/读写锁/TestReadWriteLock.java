package �߳�.�̸߳߼�.��д��;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ��д��
 * �����ö���߳�ͬʱ��ȡ,д��ʱ�޷���ȡ
 * ���̶߳�,�����,���Ҷ�ȡ��д����ʱ��ʹ�ö�д��
 */
public class TestReadWriteLock {
    public static void main(String[] args) {
        ReadWriteLockTest rwlt = new ReadWriteLockTest();
        // ����ʮ���̶߳�ȡ
        for (int i=1;i<=10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // ��ȡ������Դ
                    rwlt.get();
                }
            },"A").start();
        }
        // ����һ���߳�д��
        new Thread(new Runnable() {
            @Override
            public void run() {
                // д��
                rwlt.set(12);
            }
        },"B").start();
    }
}
class ReadWriteLockTest{
    // ����һ���̼߳�Ṳ�������
    private int num = 0;
    // ������д��
    ReadWriteLock rwl = new ReentrantReadWriteLock();
    // �����ȡ����
    public void get(){
        // ʹ�ö���
        rwl.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+":"+num);
        }finally {
            // �ͷŶ���
            rwl.readLock().unlock();
        }
    }
    // ����д�뷽��
    public void set(int num){
        // ʹ��д��
        rwl.writeLock().lock();
        try {
            // �޸Ĺ���ֵ
            this.num = num;
        }finally {
            // �ͷ�д��
            rwl.writeLock().unlock();
        }
    }
}

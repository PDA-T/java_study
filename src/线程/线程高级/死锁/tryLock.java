package �߳�.�̸߳߼�.����;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ��ʱ��
 * ���ж����Ƿ�ռ��,֮���ھ�����ʲô
 */
public class tryLock {
    public static void main(String[] args) {
        System.out.println("��ʼ");
        // ����lock��
        Lock lock = new ReentrantLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // �޲�tryLock����ȴ�
                    if (lock.tryLock(1, TimeUnit.SECONDS)){// �ȴ�һ��
                        System.out.println("��ȡ������");
                        try {
                            // ѭ������߳�����
                            for (int i=0;i<5;i++){
                                System.out.println(Thread.currentThread().getName()+"--"+i);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }finally {
                            // �ͷ���
                            lock.unlock();
                        }
                    }else {
                        System.out.println("û��ȡ����");
                        // ��ֹ����
                        return;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "�߳�1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // �޲�tryLock����ȴ�
                    if (lock.tryLock(1, TimeUnit.SECONDS)){// �ȴ�һ��
                        System.out.println("��ȡ������");
                        try {
                            // ѭ������߳�����
                            for (int i=0;i<5;i++){
                                System.out.println(Thread.currentThread().getName()+"--"+i);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }finally {
                            // �ͷ���
                            lock.unlock();
                        }
                    }else {
                        System.out.println("û��ȡ����");
                        // ��ֹ����
                        return;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "�߳�2").start();
        System.out.println("����");
    }
}

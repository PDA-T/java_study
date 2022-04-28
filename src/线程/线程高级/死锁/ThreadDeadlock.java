package �߳�.�̸߳߼�.����;

/**
 * ��ͨ˳������
 */
public class ThreadDeadlock {
    public static void main(String[] args) {
        DeadLockDemo dl = new DeadLockDemo();
        new Thread(dl,"�߳�1").start();
        new Thread(dl,"�߳�2").start();
    }
}
class DeadLockDemo implements Runnable{
    // ����������
    private Object suo1 = new Object();
    private Object suo2 = new Object();
    @Override
    public void run() {
        // ����m1��m2
        m1();
        m2();
    }
    public void m1(){
        // �õ���1,����ִ��
        synchronized (suo1){
            System.out.println(Thread.currentThread().getName()+"��m1�����õ�����1��Ҫ��2");
            // �õ���2
            synchronized (suo2){
                System.out.println(Thread.currentThread().getName()+"m1�ķ����õ�����1����2");
            }
        }
    }
    public void m2(){
        // ������2,��m1������ȡ����˳���෴
        synchronized (suo2){
            System.out.println(Thread.currentThread().getName()+"��m2�����õ�����2��Ҫ��1");
            // �õ���2
            synchronized (suo1){
                System.out.println(Thread.currentThread().getName()+"m2�ķ����õ�����2����1");
            }
        }
    }
}
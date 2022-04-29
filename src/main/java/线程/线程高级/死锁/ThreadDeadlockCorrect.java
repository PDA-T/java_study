package �߳�.�̸߳߼�.����;

/**
 * ��ͨ˳�������������
 */
public class ThreadDeadlockCorrect {
    public static void main(String[] args) {
        DeadLockDemoCorrect dl = new DeadLockDemoCorrect();
        new Thread(dl,"�߳�1").start();
        new Thread(dl,"�߳�2").start();
    }
}
class DeadLockDemoCorrect implements Runnable{
    // ����������
    private Object suo1 = new Object();
    private Object suo2 = new Object();
    // ��һ����,�ڼ��ٵ�����²Ż��õ�,������Ҫ׼��
    private Object jiaSuo = new Object();
    // �õ�ԭ������������hashcode
    int suo1HashCode = System.identityHashCode(suo1);
    int suo2HashCode = System.identityHashCode(suo2);
    // ��������Ĺ���:������˳���hashֵ�Ĵ�С��������,���hashֵһ����ӵ�������(jiaSuo,���ٳ���)
    @Override
    public void run() {
        // ����m1��m2
        m1();
        m2();
    }
    public void m1(){
        // �ж�����hashֵ
        if (suo1HashCode>suo2HashCode){
            // �õ���1,����ִ��
            synchronized (suo1){
                System.out.println(Thread.currentThread().getName()+"��m1�����õ�����1��Ҫ��2");
                // �õ���2
                synchronized (suo2){
                    System.out.println(Thread.currentThread().getName()+"m1�ķ����õ�����1����2");
                }
            }
        }else if (suo1HashCode<suo2HashCode){
            // ������2,��m1������ȡ����˳���෴
            synchronized (suo2){
                System.out.println(Thread.currentThread().getName()+"��m2�����õ�����2��Ҫ��1");
                // �õ���2
                synchronized (suo1){
                    System.out.println(Thread.currentThread().getName()+"m2�ķ����õ�����2����1");
                }
            }
        }else {
            // �������ȼӵ�������(�������)
            synchronized (jiaSuo){
                // �õ���1,����ִ��
                synchronized (suo1){
                    System.out.println(Thread.currentThread().getName()+"��m1�����õ�����1��Ҫ��2");
                    // �õ���2
                    synchronized (suo2){
                        System.out.println(Thread.currentThread().getName()+"m1�ķ����õ�����1����2");
                    }
                }
            }
        }
    }
    public void m2(){
        // �ж�����hashֵ
        if (suo1HashCode>suo2HashCode){
            // �õ���1,����ִ��
            synchronized (suo1){
                System.out.println(Thread.currentThread().getName()+"��m1�����õ�����1��Ҫ��2");
                // �õ���2
                synchronized (suo2){
                    System.out.println(Thread.currentThread().getName()+"m1�ķ����õ�����1����2");
                }
            }
        }else if (suo1HashCode<suo2HashCode){
            // ������2,��m1������ȡ����˳���෴
            synchronized (suo2){
                System.out.println(Thread.currentThread().getName()+"��m2�����õ�����2��Ҫ��1");
                // �õ���2
                synchronized (suo1){
                    System.out.println(Thread.currentThread().getName()+"m2�ķ����õ�����2����1");
                }
            }
        }else {
            // �������ȼӵ�������(�������)
            synchronized (jiaSuo){
                // �õ���1,����ִ��
                synchronized (suo1){
                    System.out.println(Thread.currentThread().getName()+"��m1�����õ�����1��Ҫ��2");
                    // �õ���2
                    synchronized (suo2){
                        System.out.println(Thread.currentThread().getName()+"m1�ķ����õ�����1����2");
                    }
                }
            }
        }
    }
}

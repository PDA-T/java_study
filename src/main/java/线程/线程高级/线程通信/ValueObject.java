package �߳�.�̸߳߼�.�߳�ͨ��;

public class ValueObject {
    public static void main(String[] args) {
        // ʵ����lock��
        Object lock = new Object();
        // ʵ���������߳���
        P p = new P(lock);
        C c = new C(lock);
        // ���������߳�
        p.start();
        c.start();
    }
}

/**
 * ģ�������ߺ�������֮��Ļ�����
 */
class ThreadTest{
    // ����������
    public static String value = "";
}

/**
 * ʵ�������ߵ��߳���
 */
class P extends Thread{
    // ����һ����
    private Object lock;
    // ��ʼ��������
    public P(Object lock){
        this.lock = lock;
    }
    @Override
    public void run() {
        while (true){
            try {
                synchronized (lock){
                    // ��Ϊ�����ߵĻ�������,��������,�жϻ������Ƿ�ռ��
                    if (!ThreadTest.value.equals("")){
                        // �����������ݲ�����,�õ�ǰ�߳�����,�ͷ�ͬ����
                        lock.wait();
                    }
                    // ������Ϊ��,��������
                    System.out.println("������û������,��ʼ����");
                    // �ڻ����������ϵͳ��ǰʱ��
                    ThreadTest.value = System.currentTimeMillis()+"";
                    // ֪ͨ�������߳���������
                    lock.notify();
                }
            }catch (Exception e){

            }
        }
    }
}

/**
 * ʵ���������߳���
 */
class C extends Thread{
    // ����һ����
    private Object lock;
    // ��ʼ��������
    public C(Object lock){
        this.lock = lock;
    }
    @Override
    public void run() {
        while (true){
            try {
                synchronized (lock){
                    // �жϻ������Ƿ�������
                    if (ThreadTest.value.equals("")){
                        // û�����ݲ�����,�õ�ǰ�߳�����,�ͷ�ͬ����
                        lock.wait();
                    }
                    // ������������,���ѵ�
                    System.out.println("������������,��������");
                    // ���ǻ���������
                    ThreadTest.value = "";
                    // ���Ѻ�,֪ͨ��������������
                    lock.notify();
                }
            }catch (Exception e){

            }
        }
    }
}
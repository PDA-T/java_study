package �߳�.�̸߳߼�.�̳߳�;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * �������Ի����ӳ�ִ������
 */
public class TestScheduledThread {
    public static void main(String[] args) {
        // ����10�������߳������̳߳�
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);
        // ������ִ��
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        },0,1, TimeUnit.SECONDS);
    }
}

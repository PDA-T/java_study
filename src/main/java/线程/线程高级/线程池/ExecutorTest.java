package �߳�.�̸߳߼�.�̳߳�;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * �̳߳�(��̬��������)
 * ���ʺϿ���,��ֹʹ��
 */
public class ExecutorTest {
    public static void main(String[] args) {
        // ����10���̵߳��̳߳�
        ExecutorService service = Executors.newFixedThreadPool(10);
        // ���̳߳��ύ�߳�
        service.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
        // �ر��̳߳�
        service.shutdown();
    }
}

package �߳�.�̸߳߼�;

/**
 * ��̨�߳�,����ǰ̨�̵߳ķ����ṩ,ǰ̨�߳�������̨�߳�Ҳ������
 */
public class DaemonThread {
    public static void main(String[] args) {
        // ������̨�߳�
        Thread daemonThread = new Daemon_Thread("��̨���߳�");
        // ����Ϊ��̨�߳�,����Ҫд�������߳�ǰ
        daemonThread.setDaemon(true);
        // �����߳�
        daemonThread.start();
        try {
            // ���߳�����
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // ѭ��������߳�����
        for (int i=1;i<101;i++){
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
        // ����ǰ̨�̲߳�����
        new ForegroundThread("ǰ̨�߳�").start();
    }
}

/**
 * ������̨�߳�
 */
class Daemon_Thread extends Thread{
    public Daemon_Thread(String name){
        super(name);
    }

    @Override
    public void run() {
        for (int i=1;i<101;i++){
            try {
                // �߳�����
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // ����߳���
            System.out.println(this.getName()+":"+i);
        }
    }
}

/**
 * ����ǰ̨�߳�
 */
class ForegroundThread extends Thread{
    public ForegroundThread(String name){
        super(name);
    }

    @Override
    public void run() {
        for (int i=1;i<101;i++){
            try {
                // �߳�����
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // ����߳���
            System.out.println(this.getName()+":"+i);
        }
    }
}

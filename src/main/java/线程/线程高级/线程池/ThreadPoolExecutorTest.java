package �߳�.�̸߳߼�.�̳߳�;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * �̳߳�
 */
public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        // �����̳߳ش�С
        int corePoolSize = 5;
        // �̳߳�����ܽ��ܶ����߳�
        int maximumPoolSize = 10;
        // ��ǰ�߳�������corePoolSize,С��maximumPoolSizeʱ,����corePoolSize���߳�������������
        long keepActiveTime = 200;
        // ����ʱ�䵥λ,��
        TimeUnit timeUnit = TimeUnit.SECONDS;
        // �����̳߳ػ�����е��������ΪFIFO,����ָ��������д�С
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(5);
        // �������ϲ���,�����̳߳�
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,
                keepActiveTime,timeUnit,workQueue);
        // ���̳߳���ѭ���ύ�߳�
        for (int i=1;i<=15;i++){
            // �����߳���
            PoolThread pt = new PoolThread(i);
            // �����̳߳ص�execute���������߳�
            executor.execute(pt);
            System.out.println("�̳߳�����Ŀ:"+
                    executor.getPoolSize()+"�ж��еȴ�ִ�е�������:"+
                    executor.getQueue().size()+"��ִ�����������Ŀ"+
                    executor.getCompletedTaskCount());
        }
        // ʹ���̳߳غ�Ҫ�ر�
        executor.shutdown();
    }
}
class PoolThread implements Runnable{
    // ����һ��������
    private int num;
    // ������
    public PoolThread(int num){
        this.num = num;
    }
    @Override
    public void run() {
        System.out.println("����"+num+"��ʼִ��");
        try {
            // ��˯5��
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("����"+num+"ִ�����");
    }
}

package �߳�.��������;

/**
 * �����߳�,ģ��˫����ս����Ϊ
 */
public class ArmyRunnable implements Runnable{
    volatile boolean keepRunning=true;// ����,û�յ�ֹͣ�����߳�һֱ����,volatile��֤���߳̿�����ȷ��ȡ�����߳�д���ֵ
    @Override// ע��
    public void run() {// ��д�̷߳���
        while (keepRunning){// ��������ѭ��
            for (int i=0;i<5;i++){// ÿ�η�������5��
                System.out.println(Thread.currentThread().getName()+":��["+i+"]�ν���");// ��ӡ�߳���,������Ϣ
                Thread.yield();// ��ͣ��ǰ����ִ�е��߳�,��ִ�������߳�(����û��Ч��)
            }
        }
        System.out.println(Thread.currentThread().getName()+":��������");// ��ӡ��ʾ
    }
}

package �߳�.�̸߳߼�;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * ʹ��Callable(�ӿ�)��Future�����߳�
 */
public class CallableClass {
    public static void main(String[] args) {
        // ʵ�����Զ�����߳���
        ThirdThread thirdThread = new ThirdThread();
        // ��װ�߳���,���ڷ���ֵ��int,���Է���дint�İ�װ��
        FutureTask<Integer> futureTask = new FutureTask<>(thirdThread);
        // �����߳�
        new Thread(futureTask,"1").start();
        try {
            // ��ȡcall����ֵ
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
class ThirdThread implements Callable{
    /**
     * ����run����,call�����з���ֵ
     * @return
     * @throws Exception
     */
    @Override
    public Object call() throws Exception {
        int i=1;
        // ѭ����ӡ���߳�����
        for (;i<=10;i++){
            System.out.println("���߳�����"+Thread.currentThread().getName()+"-"+i);
        }
        return i;
    }
}

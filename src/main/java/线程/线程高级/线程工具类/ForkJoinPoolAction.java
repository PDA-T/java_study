package �߳�.�̸߳߼�.�̹߳�����;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * �̹߳�����
 * �߳��������޴��ʱ��,���Բ��ִ��,��������ִ��Ч��
 */
public class ForkJoinPoolAction {
    public static void main(String[] args) throws InterruptedException {
        // ����������
        PrintTask pt = new PrintTask(1,300);
        // ��ȡ������,���������Դ����߳�����,Ĭ�Ͽջ��Զ����ü������CPU����
        ForkJoinPool fjp = new ForkJoinPool();
        // ִ���̳߳�
        fjp.submit(pt);
        // ���ùر��̳߳صȴ�ʱ��,2��
        fjp.awaitTermination(2, TimeUnit.SECONDS);
        // �ر��̳߳�
        fjp.shutdown();
    }
}

/**
 * ��ӡ1-300֮�������
 */
class PrintTask extends RecursiveAction{
    // ����һ������ֵ(������һ���߳�,һ������ӡ30����),�ɴ������ֳ�С����,һ���߳�������30��
    private static final int THRESHOLD = 30;
    // С�����ӡ�Ŀ�ͷ��
    private int start;
    // С�����ӡ��β��
    private int end;
    public PrintTask(int start,int end){
        this.start = start;
        this.end = end;
    }

    /**
     * ʵ�ֲ������
     * ʹ�õݹ�ķ�ʽ,�Ѵ�����һ��һ���Ĳ�ֳ�С����
     */
    @Override
    protected void compute() {
        if ((end-start)<=THRESHOLD){
            for (int i=start;i<=end;i++){
                System.out.println(Thread.currentThread().getName()+"��iֵ:"+i);
            }
        }else {
            /*
             * �����,���,�����㷨
             * �������β�����Զ����Եõ��м����
             * ����������һ��
             * �����������Էֿ�����,һ��һ��Ĵ���,���һ�뻹������ڲ��
             */
            int middle = (end+start)/2;
            // ��ֳ����δ���,�԰���,ʹ���˵ݹ�:�����ֺ󻹴�,���Լ������
            PrintTask left = new PrintTask(start,middle);
            PrintTask right = new PrintTask(middle+1,end);
            // �ϲ�����
            left.fork();
            right.fork();
        }
    }
}

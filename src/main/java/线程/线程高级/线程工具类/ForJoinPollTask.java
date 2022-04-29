package �߳�.�̸߳߼�.�̹߳�����;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * �̹߳�����(�з���ֵ)
 * �߳��������޴��ʱ��,���Բ��ִ��,��������ִ��Ч��
 */
public class ForJoinPollTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Ҫ��300Ԫ�ص�������ĳ�Ա֮��
        int[] arr = new int[300];
        // �����
        Random random = new Random();
        int total = 0;
        for (int i=0;i<arr.length;i++){
            // ��ȡ�����
            int tmp = random.nextInt(20);
            // ���������������
            arr[i] = tmp;
            total += arr[i];
        }
        System.out.println("��������ݵĺ�:"+total);
        // �ù�����ʵ�����
        SumTask st = new SumTask(arr,0,arr.length-1);// ���һ��Ԫ���±��һ
        // �̳߳�
        ForkJoinPool pool = ForkJoinPool.commonPool();
        // ��ȡ�����ֵ,�ύ����
        Future<Integer> future = pool.submit(st);
        // ������
        System.out.println("���Ľ��:"+future.get());
        // �ر��̳߳�
        pool.shutdown();
    }
}

/**
 * �з���ֵ��ʵ����
 */
class SumTask extends RecursiveTask<Integer>{
    // ���ü���ֵ
    private static final int THRESHOLD = 20;
    private int[] arr;
    // ������ʼֵ
    private int start;
    // ����β��
    private int end;
    public SumTask(int[] arr,int start,int end){
        this.start = start;
        this.end = end;
        this.arr = arr;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if (end-start<=THRESHOLD){
            for (int i=start;i<end;i++){
                sum += arr[i];
            }
            return sum;
        }else {
            // �������
            int middle = (start+end)/2;
            SumTask left = new SumTask(arr,start,middle);
            SumTask right = new SumTask(arr,middle+1,end);
            // �÷ֿ�������С���񲢷�ִ��
            left.fork();
            right.fork();
            // �ϲ����
            return left.join() + right.join();
        }
    }
}

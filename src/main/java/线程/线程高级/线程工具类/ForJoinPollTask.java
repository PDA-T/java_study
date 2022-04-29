package 线程.线程高级.线程工具类;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * 线程工具类(有返回值)
 * 线程任务量巨大的时候,可以拆分执行,提升程序执行效率
 */
public class ForJoinPollTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 要求300元素的数组里的成员之和
        int[] arr = new int[300];
        // 随机数
        Random random = new Random();
        int total = 0;
        for (int i=0;i<arr.length;i++){
            // 获取随机数
            int tmp = random.nextInt(20);
            // 把随机数放入数组
            arr[i] = tmp;
            total += arr[i];
        }
        System.out.println("构造的数据的和:"+total);
        // 用工具类实现求和
        SumTask st = new SumTask(arr,0,arr.length-1);// 最后一个元素下标减一
        // 线程池
        ForkJoinPool pool = ForkJoinPool.commonPool();
        // 获取结果的值,提交任务
        Future<Integer> future = pool.submit(st);
        // 输出结果
        System.out.println("最后的结果:"+future.get());
        // 关闭线程池
        pool.shutdown();
    }
}

/**
 * 有返回值的实现类
 */
class SumTask extends RecursiveTask<Integer>{
    // 设置极限值
    private static final int THRESHOLD = 20;
    private int[] arr;
    // 设置起始值
    private int start;
    // 设置尾数
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
            // 拆分任务
            int middle = (start+end)/2;
            SumTask left = new SumTask(arr,start,middle);
            SumTask right = new SumTask(arr,middle+1,end);
            // 让分开的两个小任务并发执行
            left.fork();
            right.fork();
            // 合并结果
            return left.join() + right.join();
        }
    }
}

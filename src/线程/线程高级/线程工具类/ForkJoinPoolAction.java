package 线程.线程高级.线程工具类;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * 线程工具类
 * 线程任务量巨大的时候,可以拆分执行,提升程序执行效率
 */
public class ForkJoinPoolAction {
    public static void main(String[] args) throws InterruptedException {
        // 创建大任务
        PrintTask pt = new PrintTask(1,300);
        // 获取工具类,构造器可以传入线程数量,默认空会自动设置计算机的CPU个数
        ForkJoinPool fjp = new ForkJoinPool();
        // 执行线程池
        fjp.submit(pt);
        // 设置关闭线程池等待时间,2秒
        fjp.awaitTermination(2, TimeUnit.SECONDS);
        // 关闭线程池
        fjp.shutdown();
    }
}

/**
 * 打印1-300之间的整数
 */
class PrintTask extends RecursiveAction{
    // 定义一个极限值(单独的一条线程,一次最多打印30个数),吧大任务拆分成小任务,一个线程最多完成30个
    private static final int THRESHOLD = 30;
    // 小任务打印的开头数
    private int start;
    // 小任务打印的尾数
    private int end;
    public PrintTask(int start,int end){
        this.start = start;
        this.end = end;
    }

    /**
     * 实现拆分任务
     * 使用递归的方式,把大任务一级一级的拆分成小任务
     */
    @Override
    protected void compute() {
        if ((end-start)<=THRESHOLD){
            for (int i=start;i<=end;i++){
                System.out.println(Thread.currentThread().getName()+"的i值:"+i);
            }
        }else {
            /*
             * 任务大,拆分,分治算法
             * 起点数加尾数除以二可以得到中间的数
             * 整数个数的一半
             * 当任务过多可以分开处理,一半一半的处理,如果一半还多可以在拆分
             */
            int middle = (end+start)/2;
            // 拆分成两次处理,对半拆分,使用了递归:如果拆分后还大,可以继续拆分
            PrintTask left = new PrintTask(start,middle);
            PrintTask right = new PrintTask(middle+1,end);
            // 合并任务
            left.fork();
            right.fork();
        }
    }
}

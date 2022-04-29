package 线程.线程高级;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 使用Callable(接口)和Future创建线程
 */
public class CallableClass {
    public static void main(String[] args) {
        // 实例化自定义的线程类
        ThirdThread thirdThread = new ThirdThread();
        // 封装线程类,由于返回值是int,所以泛型写int的包装类
        FutureTask<Integer> futureTask = new FutureTask<>(thirdThread);
        // 启动线程
        new Thread(futureTask,"1").start();
        try {
            // 获取call返回值
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
     * 类似run方法,call方法有返回值
     * @return
     * @throws Exception
     */
    @Override
    public Object call() throws Exception {
        int i=1;
        // 循环打印子线程名字
        for (;i<=10;i++){
            System.out.println("子线程名字"+Thread.currentThread().getName()+"-"+i);
        }
        return i;
    }
}

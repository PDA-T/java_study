package 线程.线程高级.线程池;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池(静态工厂创建)
 * 不适合开发,禁止使用
 */
public class ExecutorTest {
    public static void main(String[] args) {
        // 创建10条线程的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);
        // 向线程池提交线程
        service.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
        // 关闭线程池
        service.shutdown();
    }
}

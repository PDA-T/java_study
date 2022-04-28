package 线程.线程高级.多线程集合;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 集合的线程安全
 * 线程安全的集合替换线程不安全的集合
 */
public class TestCopyOnWriteArrayList {
    public static void main(String[] args) {
        CollectionsThread ct = new CollectionsThread();
        new Thread(ct,"线程1").start();
        new Thread(ct,"线程2").start();
        new Thread(ct,"线程3").start();
        new Thread(ct,"线程4").start();
    }
}
class CollectionsThread implements Runnable{
    // 传统的线程安全共享变量集合
//    private static List<String> list = Collections.synchronizedList(new ArrayList<String>());
    // 使用JUC提供的线程安全集合
    private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
    static {
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
    }

    @Override
    public void run() {
        // 创建迭代器
        Iterator<String> it = list.iterator();
        // 判断带迭起是否有值
        while (it.hasNext()){
            // 读取集合的值
            System.out.println(it.next());
            // 一边读一边写为复合操作,传统的线程安全集合会抛出异常
            list.add("111");
        }
    }
}

package 线程.隋唐演义;

/**
 * 创建关键人物类
 */
public class KeyPersonThread extends Thread{
    @Override// 注解
    public void run() {// 重写线程方法
        System.out.println(Thread.currentThread().getName()+"开始了战斗");// 打印提示
        for (int i=0;i<10;i++){// 每次发动攻击10次
            System.out.println(Thread.currentThread().getName()+"攻击了隋军");// 打印提示
        }
        System.out.println(Thread.currentThread().getName()+"结束了战斗");// 打印提示
    }
}

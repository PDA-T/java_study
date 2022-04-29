package 线程.隋唐演义;

/**
 * 军队线程,模拟双方作战的行为
 */
public class ArmyRunnable implements Runnable{
    volatile boolean keepRunning=true;// 军令,没收到停止命令线程一直运行,volatile保证了线程可以正确读取其他线程写入的值
    @Override// 注解
    public void run() {// 重写线程方法
        while (keepRunning){// 创建攻击循环
            for (int i=0;i<5;i++){// 每次发动攻击5次
                System.out.println(Thread.currentThread().getName()+":第["+i+"]次进攻");// 打印线程名,攻击信息
                Thread.yield();// 暂停当前正在执行的线程,并执行其他线程(可能没有效果)
            }
        }
        System.out.println(Thread.currentThread().getName()+":结束进攻");// 打印提示
    }
}

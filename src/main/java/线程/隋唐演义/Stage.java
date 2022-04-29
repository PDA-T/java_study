package 线程.隋唐演义;
/**
 * 隋唐演义舞台类
 */
public class Stage extends Thread{
    @Override// 注解
    public void run() {// 重写线程方法
        System.out.println("开始");// 打印提示
        try {// 捕捉异常
            Thread.sleep(5000);// 线程休眠
        } catch (InterruptedException e) {// 处理异常
            e.printStackTrace();// 打印异常
        }
        ArmyRunnable armyTaskOfSuiDynasty=new ArmyRunnable();// 实例化军队类
        ArmyRunnable armyTaskOfRevolt=new ArmyRunnable();// 实例化两个军队
        Thread armyOfSuiDynasty=new Thread(armyTaskOfSuiDynasty,"隋军");// 实例化线程
        Thread armyOfRevolt=new Thread(armyTaskOfRevolt,"起义军");// 实例化两个军队线程
        armyOfSuiDynasty.start();// 启动线程
        armyOfRevolt.start();// 启动线程
        try {// 捕捉异常
            Thread.sleep(50);// 线程休眠,以防影响军队线程
        } catch (InterruptedException e) {// 处理异常
            e.printStackTrace();// 打印异常
        }
        armyTaskOfSuiDynasty.keepRunning=false;// 停止循环
        armyTaskOfRevolt.keepRunning=false;// 停止循环
        try {// 捕捉异常
            armyOfRevolt.join();// 优先执行
        } catch (InterruptedException e) {// 处理异常
            e.printStackTrace();// 打印异常
        }
        System.out.println("关键人物程咬金入场");// 打印提示
        Thread mrCheng=new KeyPersonThread();// 创建线程
        mrCheng.setName("程咬金");// 设置线程名字
        System.out.println("程咬金要结束战争");// 打印提示
        armyTaskOfSuiDynasty.keepRunning=false;// 停止线程
        armyTaskOfRevolt.keepRunning=false;// 停止线程
        try {// 捕捉异常
            Thread.sleep(2000);// 线程休眠
        } catch (InterruptedException e) {// 处理异常
            e.printStackTrace();// 打印异常
        }
        mrCheng.start();// 运行线程
        try {// 捕捉异常
            mrCheng.join();// 优先执行
        } catch (InterruptedException e) {// 处理异常
            e.printStackTrace();// 打印异常
        }
        System.out.println("战争结束");// 打印提示
        System.out.println("程序结束");// 打印提示
    }
    public static void main(String[] args) {
        new Stage().start();// 运行程序
    }
}

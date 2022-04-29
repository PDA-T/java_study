package 线程.能量转换;

/**
 * 对能量进行转移
 */
public class EnergyTransferTask implements Runnable{
    private EnergySystem energySystem;// 共享的能量
    private int fromBox;// 能量转移的源能量盒子下标
    private double maxAmount;// 单次能量转移最大单元
    private int DELAY=10;// 最大休眠时间

    /**
     * 给类属性赋值
     * @param energySystem 共享的能量
     * @param from 能量转移的源能量盒子下标
     * @param max 单次能量转移最大单元
     */
    public EnergyTransferTask(EnergySystem energySystem,int from,double max){
        this.energySystem=energySystem;// 将类属性赋值为传入的参数
        this.fromBox=from;// 将类属性赋值为传入的参数
        this.maxAmount=max;// 将类属性赋值为传入的参数
    }
    @Override// 注解
    public void run() {// 重写线程方法
        try {// 捕捉异常
            while (true){// 创建循环
                // 盒子长度+默认产生大于等于0.0且小于1.0之间的随机double型随机数,即:0.0<=Math.random()<1.0
                int toBox=(int)(energySystem.getBoxAmount()*Math.random());
                double amount=maxAmount*Math.random();// 单次能量转移最大单元+随机数
                energySystem.transfer(fromBox,toBox,amount);// 调用能量转移到其他盒子方法
                Thread.sleep((int)(DELAY*Math.random()));// 休眠时间为最大休眠时间+随机数
            }
        }catch (InterruptedException e){// 捕获异常
            e.printStackTrace();// 打印异常
        }
    }
}

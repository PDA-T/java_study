package 线程.能量转换;

/**
 * 能量系统类
 */
public class EnergySystem {
    private final double[] energyBoxes;// 能量盒子,储存能量
    private final Object lockObj=new Object();// 创建一个私有的域,对象锁
    /**
     * @param n 能量盒子的数量
     * @param initialEnergy 每个能量盒子初始含有的能量值
     */
    public EnergySystem(int n,double initialEnergy){
        energyBoxes=new double[n];// 初始化盒子的值
        for (int i=0;i<energyBoxes.length;i++){// 循环遍历
            energyBoxes[i]=initialEnergy;// 赋值
        }
    }

    /**
     * 能量转移到其他盒子
     * @param from 能量源
     * @param to 能量终点
     * @param amount 能量值
     */
    public void transfer(int from,int to,double amount){
        synchronized (lockObj){// 创建同步锁,同步线程
//            if (energyBoxes[from]<amount){// 如果盒子里面的能量源小于转出的能量值
//                return;// 返回
//            }
            // 保证条件不满足时任务都会被条件阻挡,而不是继续竞争CPU资源
            while (energyBoxes[from]<amount){// 如果盒子里面的能量源小于转出的能量值
                try {// 捕获异常
                    // 小于转出的能量值线程无法完成如下操作,进入等待状态,让其他线程尝试操作
                    lockObj.wait();// 使线程进入等待状态
                } catch (InterruptedException e) {// 处理异常
                    e.printStackTrace();// 打印异常
                }
            }
            System.out.print(Thread.currentThread().getName());// 打印线程名字
            energyBoxes[from]-=amount;// 减去转出能量
            System.out.printf("从%d转移%10.2f单位能量到%d",from,amount,to);// 打印提示,格式输出模式
            energyBoxes[to]+=amount;// 加上转入能量
            System.out.printf("能量总和:%10.2f%n",getTotalEnergies());// 打印提示
            lockObj.notifyAll();// 唤醒所有在lockObj对象上等待的线程
        }
    }
    /**
     * 获取能量的总和
     */
    public double getTotalEnergies(){
        double sum=0;// 创建初始值
        for (double amount:energyBoxes){// 循环遍历能量盒子
            sum+=amount;// 相加盒子的值
        }
        return sum;// 返回总值
    }
    /**
     * 返回能量盒子的长度
     */
    public int getBoxAmount(){
        return energyBoxes.length;// 返回数组的长度
    }
}

package 线程.能量转换;

/**
 * 测试类
 */
public class EnergySystemTest {
    public static final int BOX_AMOUNT=100;// 将要构建的能量世界中能量盒子数量
    public static final double INITIAL_ENERGY=1000;// 每个盒子初始能量
    /**
     * 主方法
     */
    public static void main(String[] args) {
        // 实例化能量系统类,传入盒子数量和每个能量盒子初始含有的能量值
        EnergySystem eng=new EnergySystem(BOX_AMOUNT,INITIAL_ENERGY);
        for (int i=0;i<BOX_AMOUNT;i++){// 循环遍历
            // 实例化能量转移类,传入实例化能量系统类对象,能量转移的源能量盒子下标,单次能量转移最大单元
            EnergyTransferTask task=new EnergyTransferTask(eng,i,INITIAL_ENERGY);
            Thread t=new Thread(task,"TransferThread_"+i);// 创建线程传入能量转移类对象,线程名字
            t.start();// 启动线程
        }
    }
}

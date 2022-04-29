package 多态.商城秒杀模拟;
/**
 * 商城系统类
 * 运行时多态:项目需要多个同名方法并且参数相同使用运行时多态
 */
public class Shopping2 {
    public void seckill(String name,Device device){// 创建秒杀方法,传入商品名称,设备信息
        System.out.println("正在秒杀商品:"+name);// 输出提示
        device.record(name);// 调用设备类方法,相当于传入的对象.record(name);
    }
    public static void main(String[] args) {// 主方法
        Shopping2 shopping=new Shopping2();// 实例化商城类
        Device phone=new Phone();// 设备类实例化手机类
        Device pc=new PC();// 设备类实例化PC类
        shopping.seckill("笔记本",phone);// 调用秒杀方法,传入名称和设备对象调用不同的子类方法
        shopping.seckill("手机",pc);// 调用秒杀方法,传入名称和设备对象调用不同的子类方法
    }
}
/**
 * 设备类,定义抽象子类扩展
 */
abstract class Device{
    public abstract void record(String name);// 设备记录类,传入商品名称
}
/**
 * 定义手机类继承设备类
 */
class Phone extends Device{
    @Override// 重写注解
    public void record(String name) {// 重写设备类方法
        System.out.println("移动端发起秒杀:"+name);// 输出提示
    }
}
/**
 * 定义PC类继承设备类
 */
class PC extends Device{
    @Override// 重写注解
    public void record(String name) {// 重写设备类方法
        System.out.println("PC端发起秒杀:"+name);// 输出提示
    }
}
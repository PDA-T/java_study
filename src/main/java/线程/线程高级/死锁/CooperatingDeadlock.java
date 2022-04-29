package 线程.线程高级.死锁;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 协作对象之间发生死锁
 * 使用开发调用避免死锁
 * 如果在调用某个方法时不需要持有锁,这种调用被称为开放调用
 * 尽量少用同步方法,多使用同步代码块
 */
public class CooperatingDeadlock {
}

/**
 * 出租车类
 */
class Taxi{
    // 车子位置和用户目标位置
    private Point location,destination;
    // 调度器
    private Dispatcher dispatcher;

    /**
     * 使用构造器初始化客户位置
     * @param dispatcher
     */
    public Taxi(Dispatcher dispatcher){
        this.dispatcher=dispatcher;
    }

    /**
     * 获取位置方法
     * 同步方法,防止出现线程安全问题
     * @return
     */
    public synchronized Point getLocation(){
        return location;
    }

    /**
     * 设置车子位置
     * 位置信息均为共享变量,必须使用同步
     * @param location
     */
    public void setLocation(Point location){
        /*
         * 不推荐使用
         */
//        this.location=location;
//        // 判断用户的位置是否和车的位置相近(可以重写equals判断)
//        if (location.equals((destination))){
//            // 如果距离相近,通过调度器把车加入到可派遣的集合里面去
//            dispatcher.notifyAvailable(this);
//        }
        // 定义一个标记
        boolean flag;
        // this指类的实例对象(和同步方法使用的锁一样)
        synchronized (this){
            this.location = location;
            // 判断用户的位置是否和车的位置相近(可以重写equals判断)
            flag = location.equals(destination);
        }
        // 程序到这里已经结束了同步代码块(已经释放锁),之后在调用其他同步代码块的方法就不会死锁(不存在锁的交替了)

        // 判断用户的位置是否和车的位置相近
        if (flag){
            // 如果距离相近,通过调度器把车加入到可派遣的集合里面去
            dispatcher.notifyAvailable(this);
        }
    }
}
class Dispatcher {
    // 全部车辆集合
    private final Set<Taxi> taxis;
    // 可派遣车辆集合
    private final Set<Taxi> availableTaxis;

    /**
     * 通过构造器初始化两个集合
     */
    public Dispatcher() {
        taxis = new HashSet<Taxi>();
        availableTaxis = new HashSet<Taxi>();
    }

    /**
     * 将可派遣的车辆放入集合
     * 使用共享变量必须设置为同步
     *
     * @param taxi
     */
    public synchronized void notifyAvailable(Taxi taxi) {
        // 添加进可派遣集合
        availableTaxis.add(taxi);
    }

    /**
     * 在地图上画出可派遣车辆
     * 关于地图位置的操作必须使用同步
     *
     * @return
     */
    public Image getImage() {
//        Image image = new Image();
//        // 把可调度的车辆循环遍历画在地图上
//        for (Taxi t : availableTaxis){
//            // 调用把车辆画在地图上的方法
//            image.drawMarker(t.getLocation());
//        }
//        return image;
//    }
        // 创建一个副本集合
        Set<Taxi> copy;
        // this指类的实例对象(和同步方法使用的锁一样)
        synchronized (this) {
            // 复制出一个变量(副本)
            copy = new HashSet<Taxi>(availableTaxis);
        }
        // 同步代码块到这里已经结束(锁已经被释放),即便在调用其他同步代码块的方法也无所谓

        Image image = new Image();
        // 把可调度的车辆循环遍历画在地图上
        for (Taxi t : copy) {
            // 调用把车辆画在地图上的方法
            image.drawMarker(t.getLocation());
        }
        return image;
    }

    /**
     * 地图类
     */
    class Image {
        /**
         * 可以在地图上某一个位置画一辆车
         *
         * @param p
         */
        public void drawMarker(Point p) {
        }
    }
}
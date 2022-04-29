package 线程.线程高级.单例模式;

/**
 * 更新的两种单例模式
 */
public class newSingleTon {
    public static void main(String[] args) {
        // 创建20个线程
        for (int i=0;i<20;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Singleton5.getInstance());
                }
            }).start();
        }
    }
}

/**
 * 静态内部类的单例模式
 * 兼顾了饿汉模式和懒汉模式的优点
 * 但是无法传入参数
 */
class Singleton4{
    // 创建私有构造方法,不让外部类创建实例对象
    private Singleton4(){}
    // 创建静态私有化内部类
    private static class SingleTonHoler{
        // 在内部类创建静态私有化外部类属性
        private static Singleton4 singleton = new Singleton4();
    }
    // 创建返回方法,返回已经创建好的实例对象
    public static Singleton4 getInstance(){
        // 返回创建好的实例对象
        return SingleTonHoler.singleton;
    }
}

/**
 * 枚举方式实现单例模式
 * 简单,线程安全但是可读性不高
 */
enum Singleton5{
    SINGLETON;
    // 可以省略此方法直接Singleton5.SINGLETON获取
    public static Singleton5 getInstance(){
        return Singleton5.SINGLETON;
    }
}

package 线程.线程高级.单例模式;

/**
 * 两种单例模式的线程安全
 * 单例模式:程序中只能存在一个实例的对象
 */
public class Singleton {
    public static void main(String[] args) {
        // 创建20个线程
        for (int i=0;i<20;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Singleton2.getInstance());
                }
            }).start();
        }
    }
}

/**
 * 饿汉模式
 * 无线程安全问题
 * 但是程序启动就会创建实例对象,占用内存,加载速度慢
 */
class Singleton1{
    // 创建私有实例对象
    private static Singleton1 singleton1 = new Singleton1();
    // 创建私有构造方法,不让外部类创建实例对象
    private Singleton1(){}
    // 创建返回方法,返回已经创建好的实例对象
    public static Singleton1 getInstance(){
        return singleton1;
    }
}

/**
 * 懒汉模式
 * 有线程安全问题
 */
class Singleton2{
    // 创建空实例对象
    private static Singleton2 singleton2 = null;
    // 创建私有构造方法,不让外部类创建实例对象
    private Singleton2(){}
    // 创建返回方法,返回已经创建的实例对象
    public static Singleton2 getInstance(){
        // 判断实例对象是否已经被创建
        if (singleton2 == null){
            // 创建实例对象
            singleton2 = new Singleton2();
        }
        // 返回创建的实例对象
        return singleton2;
    }
}

/**
 * 解决懒汉模式的线程安全问题
 */
class Singleton3{
    // 创建空实例对象
    private volatile static Singleton3 singleton3 = null;
    // 创建私有构造方法,不让外部类创建实例对象
    private Singleton3(){}
    // 创建返回方法,返回已经创建的实例对象
    public static Singleton3 getInstance(){
        // 判断实例对象是否已经被创建
        if (singleton3 == null){
            // 使用同步代码块保护,锁为类信息类,第二层保护
            synchronized (Singleton3.class){
                // 再次判断是否为空,第三层保护,配合volatile关键字
                if (singleton3 == null){
                    // 创建实例对象
                    singleton3 = new Singleton3();
                }
            }
        }
        // 返回创建的实例对象
        return singleton3;
    }
}
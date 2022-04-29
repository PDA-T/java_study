package 泛型;

/**
 * 泛型的上下边界,有副作用,泛型上下边界可以应用于泛型方法
 * 如果需要频繁读取内容,使用上边界
 * 如果需要频繁插入内容,使用下边界
 */
public class Generic_method2 {
    public static void main(String[] args) {
        /*
         * 使用Fruit的泛型无法实例化Apple2,因为即使有继承关系,java依然无法兼容
         * 使用通配符?(表示全部类型)范围太广
         * 则可以使用上边界<? extends Fruit>即可
         * <? extends 类>表示被?号继承的类以下全部衍生的子类都合法(指定上边界)
         * <? super 类>表示被?号super的类以上的全部父类都可以赋给泛型类(指定下边界)
         */
        Plate<? extends Fruit> p = new Plate<Apple2>(new Apple2());
        /*
         * 使用通配符上边界set方法失效,无法修改
         * 因为编译器只知道容器内是Fruit或者是他的子类,具体什么类型不知道
         * 当使用set插入对象的时候编译器不知道能不能和?匹配,所以无法set
         */
//        p.set(new Fruit());// 报错
//        p.set(new Apple2());// 报错
        Fruit f = p.get();
        Object o = p.get();
        /*
         * 如上编译器只知道容器内是Fruit的子类,不知道具体类型,所以不知道具体的子类是不是Apple
         * 所以无法赋值给Apple对象
         */
//        Apple2 a = p.get();// 报错
        // 但可以转换另一种写法,先取出Fruit,在强转转换
        Apple2 a = (Apple2) f;
        /*
         * 使用下边界,set方法正常,get方法只能存放于Object
         */
        Plate<? super Fruit> p2 = new Plate<Fruit>(new Fruit());
        p2.set(new Fruit());
        p2.set(new Apple2());
//        Fruit f2 = p2.get();// 报错
        Object o2 = p2.get();
    }
}

/**
 * 创建水果类
 */
class Fruit{
}

/**
 * 创建苹果类继承水果类
 */
class Apple2 extends Fruit{}
/**
 * 创建盘子类
 */
class Plate<T>{
    private T item;
    public Plate(T t){
        item=t;
    }

    public T get() {
        return item;
    }

    public void set(T t) {
        item = t;
    }
}

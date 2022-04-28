package 反射;

public class ClassDemo {
    public static void main(String[] args) {
        Foo foo=new Foo();// 创建Foo类对象
        Class c1=Foo.class;// 类的第一种反射方法
        Class c2=foo.getClass();// 第二种反射方法
        Class c3=null;// 赋值空
        try {// 捕捉异常
            // 动态加载类
            c3=Class.forName("反射.Foo");// 第三种反射方式,需要写类的全称
        } catch (ClassNotFoundException e) {// 处理异常
            e.printStackTrace();// 打印异常
        }
        try {// 捕捉异常
            Foo foo2=(Foo) c1.newInstance();// 通过类的反射创建对象,需要强转
            foo2.print();// 需要有无参构造方法
        } catch (InstantiationException e) {// 处理异常
            e.printStackTrace();// 打印异常
        } catch (IllegalAccessException e) {// 处理异常
            e.printStackTrace();// 打印异常
        }
        System.out.println(c1==c2);// 相等
    }
}
/**
 * 类的反射为类类型
 */
class Foo{// 类本身也是对象
    public void print(){// 创建输出方法
        System.out.println("null");// 打印输出
    }
}

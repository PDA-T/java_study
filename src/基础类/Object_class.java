package 基础类;

import java.util.Objects;

public class Object_class {
    public static void main(String[] args) {
        Object_class demo=new Object_class();// 创建对象
        System.out.println(demo.hashCode());// 通过内存地址运算出一组整数
        Object_class demo2=new Object_class();// 创建对象
        System.out.println(demo2.hashCode());// 通过内存地址运算出一组整数
    }
}

/**
 * Object工具类
 */
class Object_tool {
    public void main(){
        Integer a1[]=new Integer[]{1,2,3};
        Integer a2[]=new Integer[]{1,2,3};
        // 下标值全部一样返回true
        System.out.println(Objects.deepEquals(a1,a2));
        String string="a";
        // 避免空指针异常
        System.out.println(Objects.hashCode(string));
        System.out.println("a".hashCode());
        System.out.println(Objects.hashCode("a"));
        // 加工后的求和(+31)
        System.out.println(Objects.hash("a"));
        String s="aaa";
        // 防止空值报错
        System.out.println(Objects.toString(s));
        String n=null;
        Objects.requireNonNull(n,"空");
    }
}

/**
 * clone方法必须实现可克隆Cloneable接口
 */
class Object_clone implements Cloneable{
    public void Object_clone(){
        // 因为clone是用protected修饰的方法所以调用clone方法必须是子类的对象,不能是父类的引用
        Object_clone demo=new Object_clone();
        try {
            /*
             * 浅克隆不克隆对象只克隆引用(不开辟新的内存空间),可以大量节省内存空间
             */
            java.lang.Object obj=demo.clone();// 克隆,使用子类接收必须强转
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println(demo.toString());// 默认可以不写
    }
    // 覆盖toString方法
    @Override
    public String toString(){
        return "Demo []";
    }
}
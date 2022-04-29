package 反射;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodDemo {
    public static void main(String[] args) {
        A a=new A();// 实例化方法
        Class c=A.class;// 获取类的类类型
        try {// 捕捉异常
            Method m=c.getMethod("print",new Class[]{int.class,int.class});// 获取public方法,方法名称,参数列表
            Method m2=c.getDeclaredMethod("print",new Class[]{int.class,int.class});// 获取自己声明的方法
            Method m3=c.getDeclaredMethod("print",int.class,int.class);// 此方法传入参数也可以
            Object o=m.invoke(a,new Object[]{10,20});// 通过反射调用方法,方法如果没用返回值返回null
            Object o2=m.invoke(a,10,20);// 此方法传入参数也可以
            System.out.println("----------------------------------------------");// 分割线
            Method m4=c.getMethod("print",String.class,String.class);// 获取public方法,方法名称,参数列表
            o=m4.invoke(a,"hello","HELLO");// 通过反射调用方法
//            Method m5=c.getMethod("print",new Class[]{});// 宿主空
            Method m5=c.getMethod("print");// 没有参数可以不传
//            m5.invoke(a,new Object[]{});// 宿主空
            m5.invoke(a);// 没有参数可以不写
        } catch (NoSuchMethodException e) {// 处理异常
            e.printStackTrace();// 打印异常
        } catch (InvocationTargetException e) {// 处理异常
            e.printStackTrace();// 打印异常
        } catch (IllegalAccessException e) {// 处理异常
            e.printStackTrace();// 打印异常
        }
    }
}
class A{// 创建类
    public void print(){// 创建无参方法
    }
    public void print(int a,int b){// 创建输出方法
        System.out.println(a+b);;// 打印输出
    }
    public void print(String a,String b){// 创建输出方法
        System.out.println(a.toUpperCase()+","+b.toLowerCase());// 打印输出转换大写,转换小写
    }
}

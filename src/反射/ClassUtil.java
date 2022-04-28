package 反射;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassUtil {
    /**
     * 获取成员函数
     */
    public static void printClassMethodMessage(Object obj){// 打印类的信息,包括类的成员函数,成员变量
        Class c=obj.getClass();// 获取传入的类类型
        System.out.println("类的名称时:"+c.getName());// 获取类的名称
        Method[] ms=c.getMethods();// Method是方法对象getMethods()是获取所有的public函数
//        c.getDeclaredMethods();// 获取的所有该类自己声明的方法,无访问权限限制
        for (int i=0;i<ms.length;i++){// 循环遍历数组
            Class returnType=ms[i].getReturnType();// 获取方法的返回值类类型
            System.out.print(returnType.getName()+"");// 获取方法类型的名称
            System.out.print(ms[i].getName()+"(");// 获取方法的名称
            Class[] paramTypes=ms[i].getParameterTypes();// 获取参数列表的类类型
            for (Class class1:paramTypes){// 循环遍历数组
                System.out.print(class1.getName()+";");// 获取类名称
            }
            System.out.println(")");// 打印输出另一个括号
            /**
             * 成员变量也是对象Field类
             */
        }
    }
    /**
     * 获取成员变量
     */
    public static void printFieldMessage(Object obj){// 成员变量也是对象Field类
        Class c=obj.getClass();// 获取传入的类类型
        Field[] fs=c.getFields();// 获取所有public的成员变量信息
        Field[] fs2=c.getDeclaredFields();// 获取所有自己声明的成员变量信息
        for (Field field:fs2){// 循环遍历
            Class fieldType=field.getType();// 得到成员变量的类型的类类型
            String typeName=fieldType.getName();// 得到成员变量类型的名称
            String fieldName=field.getName();// 得到成员变量的名称
            System.out.println(typeName+" "+fieldName);// 打印输出
        }
    }
    /**
     * 获取对象的构造函数信息
     */
    public static void printConMessage(Object obj){// 构造函数也是对象
        Class c=obj.getClass();// 获取传入的类类型
        Constructor[] cs=c.getConstructors();// 获取所有的public构造方法
        Constructor[] cs2=c.getDeclaredConstructors();// // 获取所有自己声明的构造方法
        for (Constructor constructor:cs){// 循环遍历
            System.out.print(constructor.getName()+"(");// 打印构造方法名称
            Class[] paramTypes=constructor.getParameterTypes();// 获取构造方法的参数列表
            for (Class class1:paramTypes){// 循环遍历
                System.out.print(class1.getName()+",");// 打印类的名称
            }
            System.out.println(")");// 打印输出
        }
    }
}
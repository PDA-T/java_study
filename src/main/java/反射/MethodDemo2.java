package 反射;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
/**
 * 反射的操作都是编译之后的操作
 */
public class MethodDemo2 {
    public static void main(String[] args) {
        ArrayList list=new ArrayList();// 创建无泛型列表
        ArrayList<String> list2=new ArrayList<String>();// 创建有泛型列表
        list2.add("hello");// 传入String参数可以
//        list2.add(20);// 传入int参数报错,防止输入错误
        Class c=list.getClass();// 获取类的类类型
        Class c2=list2.getClass();// 获取类的类类型
        System.out.println(c==c2);// 打印是否相等
        // 泛型是为了防止输入错误,只在编译阶段有效,绕过编译就无效
        try {// 捕捉异常
            Method m=c2.getMethod("add",Object.class);// 通过方法反射来验证
            m.invoke(list2,20);// 通过反射调用方法
            System.out.println(list2.size());// 输出列表长度
            System.out.println(list2);// 绕过编译泛型无效,String泛型加入了int类型值,不能使用for循环遍历
        } catch (NoSuchMethodException e) {// 处理异常
            e.printStackTrace();// 打印异常
        } catch (InvocationTargetException e) {// 处理异常
            e.printStackTrace();// 打印异常
        } catch (IllegalAccessException e) {// 处理异常
            e.printStackTrace();// 打印异常
        }
    }
}

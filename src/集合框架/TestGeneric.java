package 集合框架;

import java.util.ArrayList;
import java.util.List;

public class TestGeneric {// 创建泛型测试类
    public List<Course> courses;// 带有泛型的列表
    public TestGeneric(){// 创建构造方法
        this.courses=new ArrayList<Course>();// 实例化接口
    }
    /**
     * 测试添加
     */
    public void testAdd(){// 创建添加方法
        Course cr1=new Course("1","大学语文");// 实例化类
        courses.add(cr1);// 添加元素到列表
//        courses.add("字符串");// 泛型集合中不能添加规定类型及子类以外的对象
        Course cr2=new Course("2","Java");// 实例化类
        courses.add(cr2);// 添加元素到列表
    }
    /**
     * 测试循环遍历
     */
    public void testForEach(){// 创建遍历方法
        System.out.println("for each循环");
        for (Course cr:courses){// 创建for each循环,不需要obj强转
            System.out.println(cr.getId()+","+cr.getName());// 输出元素
        }
    }
    /**
     * 测试泛型集合可以添加泛型子类的对象实例
     */
    public void testChild(){// 创建测试方法
        ChildCourse ccr=new ChildCourse();// 实例化类
        ccr.setId("3");// 赋值
        ccr.setName("子类对象");// 赋值
        courses.add(ccr);// 添加子类实例
    }
    /**
     * 泛型不能使用基本类型
     */
    public void testBasicType(){// 创建类
        List<Integer> list=new ArrayList<Integer>();// 实例化接口
        list.add(1);// 添加元素
        System.out.println("基本类型的包装:"+list.get(0));// 打印输出
    }

    /**
     * 测试主类
     */
    public static void main(String[] args) {
        TestGeneric tg=new TestGeneric();
        tg.testAdd();
        tg.testForEach();
        tg.testChild();
        tg.testForEach();
        tg.testBasicType();
    }
}

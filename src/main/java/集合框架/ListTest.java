package 集合框架;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ListTest {// 创建备选课程类
    public List coursesToSelect;// 创建用于存放备选课程的容器
    public ListTest(){// 创建构造方法
        this.coursesToSelect=new ArrayList();// 实例化List接口
    }
    public void testAdd(){// 用于往容器中添加备选课程
        /**
         * 单个元素添加
         */
        Course cr1=new Course("1","数据结构");// 创建一个课程对象，并通过调用Add方法，添加到备选课程容器中
        coursesToSelect.add(cr1);// 添加到列表
        Course temp=(Course) coursesToSelect.get(0);// 获取列表第0位的值
        System.out.println("添加了课程:"+temp.getId()+":"+temp.getName());// 打印输出

        coursesToSelect.add(cr1);// 添加到列表
        Course temp0=(Course) coursesToSelect.get(1);// 获取列表第1位的值
        System.out.println("添加了课程:"+temp0.getId()+":"+temp0.getName());// 打印输出

        Course cr2=new Course("2","C语言");// 创建课程对象
        coursesToSelect.add(0,cr2);// 添加到列表第0位
        Course temp2=(Course) coursesToSelect.get(0);// 获取列表第0位的值
        System.out.println("添加了课程:"+temp2.getId()+":"+temp2.getName());// 打印输出

        /**
         * 数组下标越界，长度大于列表值和小于0
         */
//        Course cr3=new Course("3","test");// 创建课程对象
//        coursesToSelect.add(4,cr3);// 添加到容器
//        Course temp3=(Course) coursesToSelect.get(0);// 获取对象赋值,强制转换类型
//        System.out.println("添加了课程:"+temp3.getId()+":"+temp3.getName());// 打印输出
        /**
         * 多个元素添加
         */
        Course[] course={new Course("3","离散数学"),new Course("4","汇编语言")};// 数组创建课程
        coursesToSelect.addAll(Arrays.asList(course));// 添加所有元素到列表第二位中
        Course temp3=(Course) coursesToSelect.get(3);// 获取列表第二位的值
        Course temp4=(Course) coursesToSelect.get(4);// 获取列表第二位的值
        System.out.println("添加了两门课程:"+temp3.getId()+":"+temp3.getName());// 打印输出
        System.out.println("添加了两门课程:"+temp4.getId()+":"+temp4.getName());// 打印输出

        Course[] course2={new Course("5","高等数学"),new Course("6","大学英语")};// 数组创建课程
        coursesToSelect.addAll(2,Arrays.asList(course2));// 添加所有元素到列表第二位中
        Course temp5=(Course) coursesToSelect.get(2);// 获取列表第二位的值
        Course temp6=(Course) coursesToSelect.get(3);// 获取列表第二位的值
        System.out.println("添加了两门课程:"+temp5.getId()+":"+temp5.getName());// 打印输出
        System.out.println("添加了两门课程:"+temp6.getId()+":"+temp6.getName());// 打印输出
    }
    /**
     * 获取列表值
     */
    public void getGet(){// 创建获取列表值方法
        int size=coursesToSelect.size();// 获取列表长度
        System.out.println("以下课程待选:");// 打印输出提示
        for (int i=0;i<size;i++){// 循环获取列表全部元素
            Course cr=(Course) coursesToSelect.get(i);// 获取列表元素
            System.out.println(cr.getId()+":"+cr.getName());// 输出列表元素
        }
    }

    /**
     * 通过迭代器来遍历列表
     */
    public void testIterator(){// 创建迭代器方法
        Iterator it=coursesToSelect.iterator();// 赋值接口
        System.out.println("以下课程待选:(通过迭代器)");// 打印输出提示
        while (it.hasNext()){// 如果含有元素就返回true
            Course cr=(Course) it.next();// 方法返回下一个值
            System.out.println(cr.getId()+":"+cr.getName());// 输出列表元素
        }
    }

    /**
     * 通过for each方法访问集合元素
     */
    public void testForEach(){// 创建for each方法
        System.out.println("以下课程待选:(for each方法)");// 打印输出提示
        for (Object obj:coursesToSelect){// 创建for each循环
            Course cr=(Course) obj;// 强制转换类型
            System.out.println(cr.getId()+":"+cr.getName());// 输出列表元素
        }
    }

    /**
     * 修改列表元素
     */
    public void testModify(){// 创建修改方法
        coursesToSelect.set(4,new Course("7","毛概"));// 修改第4位元素
    }

    /**
     * 删除列表中元素
     */
    public void testRemove(){// 创建删除方法
//        Course cr=(Course) coursesToSelect.get(4);// 获取列表4位置的元素
//        coursesToSelect.remove(4);// 删除第4位课程
        Course[] courses={(Course) coursesToSelect.get(4),(Course) coursesToSelect.get(5)};// 传入多个删除元素位置
        coursesToSelect.removeAll(Arrays.asList(courses));// 删除多位元素
        testForEach();// 调用遍历方法
    }
    /**
     * 往列表里中添加一些其他东西
     */
    public void testType(){// 创建方法
        System.out.println("添加其他数据");
        coursesToSelect.add("字符");
    }
    /**
     * 测试主类
     */
    public static void main(String[] args) {
        ListTest t=new ListTest();
        t.testAdd();
//        t.getGet();
//        t.testIterator();
//        t.testForEach();
//        t.testModify();
//        t.testForEach();
//        t.testRemove();
        t.testType();
        t.testForEach();
    }
}

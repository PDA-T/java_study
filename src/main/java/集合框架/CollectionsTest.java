package 集合框架;

import java.util.*;

public class CollectionsTest {
    /**
     * 通过Collections.sort()方法,对Integer泛型的List进行排序
     */
    public void testSort1(){
        List<Integer> integerList=new ArrayList<Integer>();// 创建列表接口实例化实现类
        Random random=new Random();// 创建随机类
        Integer k;// 创建放整数的容器
        for (int i=0;i<10;i++){// 循环在List中插入随机数
            do {// 先进行运算
                k=random.nextInt(100);// 随机获取整数
            }while (integerList.contains(k));// 判断是否重复
            integerList.add(k);// 列表中加入随机数
            System.out.println("成功添加整数:"+k);// 打印输出
        }
        System.out.println("----------------排序前----------------");// 分割线
        for (Integer integer:integerList){// 循环遍历
            System.out.println("元素:"+integer);// 打印输出遍历值
        }
        Collections.sort(integerList);// 排序方法
        System.out.println("----------------排序后----------------");// 分割线
        for (Integer integer:integerList){// 循环遍历
            System.out.println("元素:"+integer);// 打印输出遍历值
        }
    }
    /**
     * 对String泛型的List进行排序
     */
    public void testSort2(){
        List<String> stringList=new ArrayList<String>();// 创建列表接口实例化实现类
        stringList.add("Google");// 在列表中添加字符串
        stringList.add("Microsoft");// 在列表中添加字符串
        stringList.add("lenovo");// 在列表中添加字符串
        System.out.println("----------------排序前----------------");// 分割线
        for (String string:stringList){// 循环遍历
            System.out.println("元素:"+string);// 打印输出遍历值
        }
        Collections.sort(stringList);// 排序方法
        System.out.println("----------------排序后----------------");// 分割线
        for (String string:stringList){// 循环遍历
            System.out.println("元素:"+string);// 打印输出遍历值
        }
    }
    public void testSort3(){
        List<Student> studentList=new ArrayList<Student>();// 创建列表接口实例化实现类
        Random random=new Random();// 创建随机数方法
        studentList.add(new Student(random.nextInt(1000)+"","Mike"));// 列表内添加对象
        studentList.add(new Student(random.nextInt(1000)+"","Angela"));// 列表内添加对象
        studentList.add(new Student(random.nextInt(1000)+"","Lucy"));// 列表内添加对象
        studentList.add(new Student(10000+"","Beyonce"));// 列表内添加对象
        System.out.println("----------------排序前----------------");// 分割线
        for (Student student:studentList){// 循环遍历
            System.out.println("学生:"+student.getId()+":"+student.getName());// 打印输出遍历值
        }
        Collections.sort(studentList);// 排序方法
        System.out.println("----------------排序后----------------");// 分割线
        for (Student student:studentList){// 循环遍历
            System.out.println("学生:"+student.getId()+":"+student.getName());// 打印输出遍历值
        }
        Collections.sort(studentList,new StudentComparator());// 排序方法
        System.out.println("----------------按照姓名排序后----------------");// 分割线
        for (Student student:studentList){// 循环遍历
            System.out.println("学生:"+student.getId()+":"+student.getName());// 打印输出遍历值
        }
    }
    public static void main(String[] args) {
        CollectionsTest ct=new CollectionsTest();// 实例化本类
//        ct.testSort1();// 调用排序1方法
//        ct.testSort2();// 调用排序2方法
        ct.testSort3();// 调用排序3方法
    }
}

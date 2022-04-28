package 集合框架;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * HashSet是无序,不可重复的集合
 */
public class SteTest {
    public List<Course> coursesToSelect;// 创建列表对象
    private Scanner console;// 创建扫描器
    public Student student;// 创建学生类
    public SteTest(){// 创建构造方法
        coursesToSelect=new ArrayList<Course>();// 实例化接口
        console=new Scanner(System.in);// 实例化扫描器
    }

    /**
     * 添加方法
     */
    public void testAdd(){// 用于往容器中添加备选课程
        Course cr1=new Course("1","数据结构");// 创建一个课程对象，并通过调用Add方法，添加到备选课程容器中
        coursesToSelect.add(cr1);// 添加到列表
        Course temp=(Course) coursesToSelect.get(0);// 获取列表第0位的值

        Course cr2=new Course("2","C语言");// 创建课程对象
        coursesToSelect.add(0,cr2);// 添加到列表第0位

        Course[] course={new Course("3","离散数学"),new Course("4","汇编语言")};// 数组创建课程
        coursesToSelect.addAll(Arrays.asList(course));// 添加所有元素到列表第二位中

        Course[] course2={new Course("5","高等数学"),new Course("6","大学英语")};// 数组创建课程
        coursesToSelect.addAll(2,Arrays.asList(course2));// 添加所有元素到列表第二位中
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
     * 测试List的contains方法
     * contains()方法底层为逐个调用equals()对比,重写equals()即可修改contains()的判断值
     */
    public void testListContains(){
        Course course=coursesToSelect.get(0);// 获取第0个的元素
        System.out.println("取得课程:"+course.getName());// 打印输出coursesToSelect是否包含course对象
        //打印输出是否存在
        System.out.println("备选课程是否包含课程:"+course.getName()+","+coursesToSelect.contains(course));
        System.out.println("请输入课程名称:");// 提示输入课程名称
        String name=console.next();// 获取传入的值
        Course course2=new Course();// 实例化类
        course2.setName(name);// 添加name值
        System.out.println("新创建课程:"+course2.getName());// 打印输出
        //打印输出是否存在
        System.out.println("备选课程是否包含课程:"+course2.getName()+","+coursesToSelect.contains(course2));
        if (coursesToSelect.contains(course2)){// 判断是否为空
            // 通过indexOF()方法来取得某元素的索引位置
            System.out.println("课程:"+course2.getName()+"索引位置为:"+coursesToSelect.indexOf(course2));
        }
    }

    /**
     * 创建学生对象并且选课
     */
    public void createStudentAndSelectCoure(){
        student=new Student("1","小明");// 创建学生对象
        System.out.println("添加:"+student.getId()+student.getName());// 打印输出
        Scanner console=new Scanner(System.in);// 创建扫描器
        for (int i=0;i<3;i++){// 创建循环
            System.out.println("请输入课程ID:");// 打印输出
            String courseId=console.next();// 指针指向下一条记录，有值就返回true并把记录内容存入到对应的对象中
            for (Course cr:coursesToSelect) {// 创建for each循环
                if (cr.getId().equals(courseId)){// 创建if比较输入的值
                    student.courese.add(cr);// 添加元素
                }
            }
        }
    }
    /**
     * 测试Set的contains方法
     */
    public void testSetContains(){
        System.out.println("请输入学生已选的课程名称:");// 打印提示
        String name=console.next();// 获取输入的值
        Course course=new Course();// 创建课程对象
        course.setName(name);// 设置类属性
        System.out.println("新创建课程:"+course.getName());// 打印输出
        /**
         * Set查询底层为调用hashCode()判断值后调用equals()方法再次判断,都成立返回true.
         * 重写hashCode()
         */
        System.out.println("备选课程中是否包含课程:"+course.getName()+","+student.courese.contains(course));
    }
    public static void main(String[] args) {
        SteTest st=new SteTest();// 实例化类
        st.testAdd();// 调用添加方法
        st.testListContains();// 调用添加方法
        st.testForEach();// 调用遍历方法
//        st.testForEachForSet(student);// 调用遍历方法
//        st.createStudentAndSelectCoure();// 创建学生对象并且选课
//        st.testSetContains();// 测试contains方法
    }
    public void testForEachForSet(Student student){// 创建遍历方法
        System.out.println("共选择了:"+student.courese.size());// 打印输出全部选择的课程
        for (Course cr:student.courese){// 打印输出学生选的课程
            System.out.println("选择了:"+cr.getId()+cr.getName());// 打印输出
        }
    }
}

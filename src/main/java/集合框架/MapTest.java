package 集合框架;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class MapTest {
    /**
     * 用来承装学生类型对象
     */
    public Map<String,Student> students;
    /**
     * 在构造器中初始化students属性
     */
    public MapTest(){
        this.students=new HashMap<String,Student>();
    }
    /**
     * 测试添加:输入ID,判断是否被占用
     */
    public void testPut(){
        Scanner console=new Scanner(System.in);// 创建扫描类
        int i=0;// 创建循环变量
        while (i<3){// 创建循环输入
            System.out.println("请输入学生ID:");// 打印输出
            String ID=console.next();// 获取输入的值
            Student st=students.get(ID);// 判断该ID是否被占用
            if (st==null){// 判断值是否存在
                System.out.println("请输入学生姓名:");// 打印输出
                String name=console.next();// 获取输入的值
                Student newStudent=new Student(ID,name);// 创建新的学生类对象
                students.put(ID,newStudent);// 调用方法添加ID-学生映射,将Student类传入的元素变为值添加
                System.out.println("成功添加学生:"+students.get(ID).getName());// 打印输出结果
                i++;// 循环变量加一
            }else {
                System.out.println("该学生ID已被占用!");// 打印输出
                continue;// 重新开始循环
            }
        }
    }
    /**
     * 测试Map的KeySet方法
     */
    public void testKeySet(){
        Set<String> KeySet=students.keySet();// 获取Map中所有的键
        for(String stuId:KeySet){// 遍历KeySet,取得每一个键,再调用get方法取得每个键对应的value
            Student st=students.get(stuId);// 获取值
            if (st!=null){// 判断该ID是否为空
                System.out.println("学生:"+st.getName());// 打印输出
            }
        }
    }

    /**
     * 测试删除Map中的映射
     */
    public void testRemove(){
        System.out.println("请输入要删除的学生ID:");// 提示要删除的学生ID
        Scanner console=new Scanner(System.in);// 创建扫描类
        String ID=console.next();// 获取输入的ID
        Student st=students.get(ID);// 获取此ID的值
        while (true){// 死循环
            if (st==null){// 判断该值是否为空
                System.out.println("空");// 打印输出
                continue;// 重新循环
            }
            System.out.println("已删除:"+st.getName());// 删除的值
            students.remove(ID);// 删除该ID的值
            break;// 跳出循环
        }
    }

    /**
     * 通过entrySet方法来遍历Map
     */
    public void testEntrySet(){
        Set<Map.Entry<String,Student>> entrySet=students.entrySet();// 获取Map的键和值
        for (Map.Entry<String,Student> entry:entrySet){// 循环遍历
            System.out.println(entry.getKey());// 获取键
            System.out.println(entry.getValue().getName());// 获取值
        }
    }

    /**
     * 利用put方法修改Map中已又的映射
     */
    public void testModify(){
        System.out.println("请输入要修改的学生ID");// 打印输出提示
        Scanner sc=new Scanner(System.in);// 创建扫描器
        String ID=sc.next();// 获取输入的值
        Student st=students.get(ID);// 获取ID对应的值
        while (true){// 死循环
            if (st==null){// 判断输入ID是否为空
                System.out.println("空");// 打印输入提示
                continue;// 重新循环
            }
            System.out.println("请输入要修改的值:");// 打印输出
            String name=sc.next();// 获取输入的值
            Student vale=new Student(ID,name);// 传入输入的值
            students.put(ID,vale);// 修改内容
            break;// 跳出循环
        }
    }
    /**
     * 测试Map中是否包含某个Key或者某个Value值
     */
    public void testContainsKeyOrValue(){
        System.out.println("请输入要查询的学生ID:");// 打印提示
        Scanner console=new Scanner(System.in);// 创建扫描器
        String id=console.next();// 获取输入的id
        // 在Map中用containsKey()方法来判断是否包含某个Key
        System.out.println("您输入的学生ID为:"+id+"在学生映射表中是否存在:"+students.containsKey(id));
        if (students.containsKey(id)){// 判断是否存在
            // 在Map中用containsValue()方法来判断是否包含某个Value值
            System.out.println("对应的学生为:"+students.get(id).getName());
        }
        System.out.println("请输入要查询的学生姓名:");// 打印提示
        String name=console.next();// 获取输入值
        if (students.containsValue(new Student(null,name))){// 判断输入学生是否存在
            System.out.println("在学生映射表中包含学生:"+name);// 打印结果
        }else {// 判断不成立
            System.out.println("输入不存在");// 打印输出
        }
    }
    public static void main(String[] args) {
        MapTest mt=new MapTest();// 实例化本类
        mt.testPut();
        mt.testKeySet();
//        mt.testRemove();
//        mt.testEntrySet();
//        mt.testModify();
//        mt.testEntrySet();
        mt.testContainsKeyOrValue();
    }
}

package 集合框架;

import java.util.HashSet;
import java.util.Set;

public class Student implements Comparable<Student>{// 创建学生类
    private String id;// 创建私有化属性
    private String name;// 创建私有化属性
    public Set<Course> courese;// 创建Set类型属性
    public Student(String id,String name){// 创建构造方法
        this.id=id;// 赋值id属性
        this.name=name;// 赋值name属性
        this.courese=new HashSet<Course>();// 接口实例化
    }
    public String getId(){// 其他类获取私有属性
        return id;
    }
    public String getName(){// 其他类获取私有属性
        return name;
    }
    /**
     * 重写hashCode()方法
     */
    @Override
    public int hashCode(){
        final int prime=31;
        int result=1;
        result=prime+result+((name==null)?0:name.hashCode());
        return result;
    }
    /**
     * 重写equals方法模板
     */
    @Override// 重写注解
    public boolean equals(Object obj){// 重写equals方法
        if (this==obj){// 判断值是否等于传入的参数
            return true;
        }
        if (obj==null) {// 判断传入参数是否为空
            return false;
        }
        if (!(obj instanceof Student)){// 测试它左边的对象是否是它右边的类的实例，返回boolean类型的数据。
            return false;
        }
        Student student=(Student) obj;// 强制转换
        /**
         * 如果为多个判断,新加入多个判断即可
         */
        if (this.name==null){// 判断名字是否为空
            if (student.name==null){// 判断强转完后名字是否为空
                return true;
            }else {
                return false;
            }
        }else {
            if (this.name.equals(student.name)){// 判断名字是否相等
                return true;
            }else{
                return false;
            }
        }
    }

    /**
     * 默认比较规则
     */
    @Override
    public int compareTo(Student o) {
        return this.id.compareTo(o.id);
    }
}

package 数据流.对象序列化;

import java.io.*;

/**
 * 对象流使用领域非常单一
 * 主要用于对象的序列化和反序列化
 * 把对象转换成二进制数据
 */
public class Student implements Serializable {
    private String stuno;// 学生编号
    private String stuname;// 学生姓名
    private transient int stuage;// 学生年龄,transient修饰后不会进行jvm默认的序列化
    /**
     * 创建有参构造方法
     * @param stuno 学生编号
     * @param stuname 学生姓名
     * @param stuage 学生年龄
     */
    public Student(String stuno, String stuname, int stuage) {
        this.stuno = stuno;// 赋值学生编号
        this.stuname = stuname;// 赋值学生姓名
        this.stuage = stuage;// 赋值学生年龄
    }
    /**
     * 创建无参构造方法
     */
    public Student(){
    }
    public String getStuno() {
        return stuno;// 返回学生编号
    }
    public void setStuno(String stuno) {
        this.stuno = stuno;// 赋值学生编号
    }
    public String getStuname() {
        return stuname;// 返回学生姓名
    }
    public void setStuname(String stuname) {
        this.stuname = stuname;// 赋值学生姓名
    }
    public int getStuage() {
        return stuage;// 返回学生年龄
    }
    public void setStuage(int stuage) {
        this.stuage = stuage;// 赋值学生年龄
    }
    @Override
    public String toString() {
        return "Student{" +
                "stuno='" + stuno + '\'' +
                ", stuname='" + stuname + '\'' +
                ", stuage=" + stuage +
                '}';
    }
    private void writeObject(java.io.ObjectOutputStream s)throws java.io.IOException{
        s.defaultWriteObject();// 把jvm能默认序列化的元素进行序列化操作
        s.writeInt(stuage);// 字节完成stuage的序列化
    }
    private void readObject(java.io.ObjectInputStream s)throws java.io.IOException,ClassNotFoundException{
        s.defaultReadObject();// 把jvm能默认反序列化的元素进行反序列化操作
        this.stuage=s.readInt();// 自己完成stuage的反序列化操作
    }
}
/**
 * 创建测试类
 */
class ObjectSeriaDemo1{
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String file="C:/Users/15811/Desktop/abc.txt";// 创建路径
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(file));// 实例化序列化流
        Student stu=new Student("10010","张三",20);// 调用构造方法
        oos.writeObject(stu);// 写入
        oos.flush();// 刷新缓冲区
        oos.close();// 关闭序列化流
        // 读取数据
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream(file));// 实例化反序列化流
        Student stu2=(Student) ois.readObject();// Obj强转Student类型
        System.out.println(stu);// 打印结果
        ois.close();// 关闭反序列化流
    }
}
/**
 * 子类和父类构造函数的调用
 */
class ObjectSeriaDemo2{
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 实例化序列化流
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("C:/Users/15811/Desktop/abc.txt"));
        Foo2 foo2=new Foo2();// 实例化Foo2类
        oos.writeObject(foo2);// 写入
        oos.flush();// 刷新缓冲区
        oos.close();// 关闭序列化流
        // 反序列化是否递归调用父类的构造方法
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream("C:/Users/15811/Desktop/abc.txt"));
        Foo2 foo3=(Foo2) ois.readObject();// 调用读取方法
        oos.writeObject(foo3);// 写入
        System.out.println(foo3);// 打印结果
        ois.close();// 关闭反序列化流
    }
}
/**
 * 一个类实现了序列化接口,那么其子类都可以进行序列化
 */
class Foo implements Serializable{
    public Foo(){// 创建构造方法
        System.out.println("foo...");// 打印
    }
}
class Foo1 extends Foo{
    public Foo1(){// 创建构造方法
        System.out.println("foo1...");// 打印
    }
}
class Foo2 extends Foo1{
    public Foo2(){// 创建构造方法
        System.out.println("foo2...");// 打印
    }
}
class Bar{
    public Bar(){// 创建构造方法
        System.out.println("Bar");// 打印
    }
}
class Bar1 extends Bar implements Serializable{
    public Bar1(){// 创建构造方法
        System.out.println("Bar1...");// 打印
    }
}
class Bar2 extends Bar1{
    public Bar2(){// 创建构造方法
        System.out.println("Bar3...");// 打印
    }
}

package 数据流.File类;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 随机访问文件类
 * 可以从文件的任何位置开始读取
 */
public class RandomAccessFileTest {
    public static void main(String[] args) throws IOException {
        // 创建雇员对象
        Employee employee = new Employee("zhangzheng",12);
        Employee employee2 = new Employee("chengqing",15);
        Employee employee3 = new Employee("yanghaoyu",20);
        // 创建随机访问类,rw表示对文件读写,r表示只读,rwd表示写入后马上保存
        RandomAccessFile rf = new RandomAccessFile("D:/abc.txt","rw");
        // 创建随机访问类,用来读取
        RandomAccessFile rfr = new RandomAccessFile("D:/abc.txt","r");
        // 写入员工1
        rf.write(employee.getName().getBytes());
        rf.writeInt(employee.getAge());
        // 写入员工2
        rf.write(employee2.getName().getBytes());
        rf.writeInt(employee2.getAge());
        // 写入员工3
        rf.write(employee3.getName().getBytes());
        rf.writeInt(employee3.getAge());
        // 读取第二个员工信息,一个员工名字8字节+年龄4字节,一共12字节,跳12字节
        rfr.skipBytes(12);
        // 创建字符串
        String str = "";
        // 循环8次读取名字8字节
        for (int i=0;i<8;i++){
            // 读取一字节
            str += (char) rfr.readByte();
        }
        // 输出名字
        System.out.println("员工2的名字:"+str);
        // 输出年龄
        System.out.println("员工2的年龄:"+rfr.readInt());
        // 还原指针到0的位置
        rfr.seek(0);
        // 创建字符串
        String str2 = "";
        // 循环8次读取名字8字节
        for (int i=0;i<8;i++){
            // 读取一字节
            str2 += (char) rfr.readByte();
        }
        // 输出名字
        System.out.println("员工1的名字:"+str2);
        // 输出年龄
        System.out.println("员工1的年龄:"+rfr.readInt());
        // 跳过12字节读取员工3
        rfr.skipBytes(12);
        // 创建字符串
        String str3 = "";
        // 循环8次读取名字8字节
        for (int i=0;i<8;i++){
            // 读取一字节
            str3 += (char) rfr.readByte();
        }
        // 输出名字
        System.out.println("员工1的名字:"+str3);
        // 输出年龄
        System.out.println("员工1的年龄:"+rfr.readInt());
        // 关闭流
        rf.close();
        rfr.close();
    }
}

/**
 * 雇员类
 */
class Employee{
    // 名字
    private String name;
    // 年龄
    private int age;
    // 初始化
    public Employee(String name,int age){
        // 包装名字,要求8字节,如果超过8字节
        if (name.length()>8){
            // 截取名字前8个字节
            name = name.substring(0,8);
        }else {
            // 如果名字小于8字节
            while (name.length()<8){
                // 名字后面加空格
                name = name + "/u0000";
            }
            // 名字8字节,初始化属性
            this.name = name;
            // 初始化年龄
            this.age = age;
        }
    }

    public String getName() {
        return name;
    }

    public Employee setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Employee setAge(int age) {
        this.age = age;
        return this;
    }
}
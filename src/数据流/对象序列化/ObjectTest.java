package 数据流.对象序列化;

import java.io.*;

/**
 * 对象流使用领域非常单一
 * 主要用于对象的序列化和反序列化
 * 把对象转换成二进制数据,可以保存在磁盘中,或传输
 */
public class ObjectTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 创建一个对象
        Person p = new Person();
        // 给属性赋值
        p.name = "张征";
        p.age = 18;
        p.sex = "男";
        // 通过接口创建文件输出流,temp为临时文件,二进制文件,无法打开
        OutputStream out = new FileOutputStream("D:/abc.temp");
        // 创建缓冲输出流,封装文件输出流
        BufferedOutputStream bout = new BufferedOutputStream(out);
        // 使用对象输出流,封装缓冲输出流
        ObjectOutputStream oout = new ObjectOutputStream(bout);
        // 将对象放入对象输出流
        oout.writeObject(p);
        /*
         * 把对象二进制文件读取出来
         * 创建文件输入流
         */
        FileInputStream fis = new FileInputStream("D:/abc.temp");
        // 创建缓冲输入流
        BufferedInputStream bis = new BufferedInputStream(fis);
        // 创建对象输入流
        ObjectInputStream ois = new ObjectInputStream(bis);
        // 拿到对象
        Person p2 = (Person) ois.readObject();
        // 输出保存的对象属性
        System.out.println(p2.name+p2.age+p2.sex);
    }
}

/**
 * 要实现对象序列化必须实现序列化接口
 */
class Person implements Serializable{
    // 名字
    public String name;
    // 年龄
    public int age;
    // 性别,transient关键字表示此属性不能被序列化
    public transient String sex;
}

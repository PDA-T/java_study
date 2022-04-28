package 数据流.对象序列化;

import java.io.*;

/**
 * 单例模式的序列化
 * 用的很少
 */
public class SerializationTest {
    public static void main(String[] args) {
        // 创建对象输出流
        ObjectOutputStream objout = null;
        // 创建对象输入流
        ObjectInputStream objin = null;
        try {
            // 创建文件输出流
            OutputStream out = new FileOutputStream("D:/abc.save");
            // 封装对象输出流
            objout = new ObjectOutputStream(out);
            // 创建单例模式类
            Person3 person3 = Person3.getInstance("张征",55,"男");
            // 对象序列化
            objout.writeObject(person3);

            // 创建文件输入流
            InputStream in = new FileInputStream("D:/abc.save");
            // 封装为对象输入流
            objin = new ObjectInputStream(in);
            // 对象反序列化
            Person3 person4 = (Person3) objin.readObject();
            // 输出是不是一个对象(false),不是
            System.out.println(person3 == person4);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
/**
 * 对象序列化要实现接口
 * 单例模式
 */
class Person3 implements Serializable {
    private String name;
    private int age;
    private String sex;
    private static Person3 person3 = null;
    // 私有化构造器,外部无法调用
    private Person3(){}

    private Person3(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    /**
     * 单例模式,先判断是否有对象,如果没有则创建
     * 保证只能有一个对象
     * @param name
     * @param age
     * @param sex
     * @return
     */
    public static Person3 getInstance(String name, int age, String sex){
        if (person3==null){
            person3 = new Person3(name,age,sex);
        }
        return person3;
    }

    public String getName() {
        return name;
    }

    public Person3 setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Person3 setAge(int age) {
        this.age = age;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public Person3 setSex(String sex) {
        this.sex = sex;
        return this;
    }

    @Override
    public String toString() {
        return "Person3{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }

    /**
     * 加上此方法则32行判断为同一对象
     * 可以替换掉反序列化时返回的不一样的对象
     * @return
     */
    public Object readResolve(){
        return person3;
    }
}
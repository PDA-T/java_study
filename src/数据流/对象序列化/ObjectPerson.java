package 数据流.对象序列化;

import java.io.*;

/**
 * 对象序列化
 * 可以把对象转换成二进制,保存或传输
 * 主要应用于分布式(远程调用)
 */
public class ObjectPerson {
    public static void main(String[] args) {
        // 创建对象输出流
        ObjectOutputStream objout = null;
        // 创建对象输入流
        ObjectInputStream objin = null;
        try {
            // 创建文件输出流
            OutputStream out = new FileOutputStream("D:/abc.obj");
            // 封装成对象输出流
            objout = new ObjectOutputStream(out);
            // 实例化对象
            Person2 person2 = new Person2("zhangzheng",12,"男");
            // 序列化对象
            objout.writeObject(person2);

            // 创建文件输入流
            InputStream is = new FileInputStream("D:/abc.obj");
            // 封装文件输入流
            objin = new ObjectInputStream(is);
            // 反序列化读取
            Person2 person3 = (Person2) objin.readObject();
            // 输出
            System.out.println(person3);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objout!=null){
                try {
                    // 关闭流
                    objout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (objin!=null){
                try {
                    // 关闭流
                    objin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

/**
 * 对象序列化要实现接口
 */
class Person2 implements Serializable {
    private String name;
    private int age;
    private String sex;
    public Person2(){}

    public Person2(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public Person2 setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Person2 setAge(int age) {
        this.age = age;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public Person2 setSex(String sex) {
        this.sex = sex;
        return this;
    }

    @Override
    public String toString() {
        return "Person2{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}

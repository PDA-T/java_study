package ������.�������л�;

import java.io.*;

/**
 * �������л�
 * ���԰Ѷ���ת���ɶ�����,�������
 * ��ҪӦ���ڷֲ�ʽ(Զ�̵���)
 */
public class ObjectPerson {
    public static void main(String[] args) {
        // �������������
        ObjectOutputStream objout = null;
        // ��������������
        ObjectInputStream objin = null;
        try {
            // �����ļ������
            OutputStream out = new FileOutputStream("D:/abc.obj");
            // ��װ�ɶ��������
            objout = new ObjectOutputStream(out);
            // ʵ��������
            Person2 person2 = new Person2("zhangzheng",12,"��");
            // ���л�����
            objout.writeObject(person2);

            // �����ļ�������
            InputStream is = new FileInputStream("D:/abc.obj");
            // ��װ�ļ�������
            objin = new ObjectInputStream(is);
            // �����л���ȡ
            Person2 person3 = (Person2) objin.readObject();
            // ���
            System.out.println(person3);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objout!=null){
                try {
                    // �ر���
                    objout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (objin!=null){
                try {
                    // �ر���
                    objin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

/**
 * �������л�Ҫʵ�ֽӿ�
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

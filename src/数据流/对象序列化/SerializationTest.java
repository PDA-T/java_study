package ������.�������л�;

import java.io.*;

/**
 * ����ģʽ�����л�
 * �õĺ���
 */
public class SerializationTest {
    public static void main(String[] args) {
        // �������������
        ObjectOutputStream objout = null;
        // ��������������
        ObjectInputStream objin = null;
        try {
            // �����ļ������
            OutputStream out = new FileOutputStream("D:/abc.save");
            // ��װ���������
            objout = new ObjectOutputStream(out);
            // ��������ģʽ��
            Person3 person3 = Person3.getInstance("����",55,"��");
            // �������л�
            objout.writeObject(person3);

            // �����ļ�������
            InputStream in = new FileInputStream("D:/abc.save");
            // ��װΪ����������
            objin = new ObjectInputStream(in);
            // �������л�
            Person3 person4 = (Person3) objin.readObject();
            // ����ǲ���һ������(false),����
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
 * �������л�Ҫʵ�ֽӿ�
 * ����ģʽ
 */
class Person3 implements Serializable {
    private String name;
    private int age;
    private String sex;
    private static Person3 person3 = null;
    // ˽�л�������,�ⲿ�޷�����
    private Person3(){}

    private Person3(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    /**
     * ����ģʽ,���ж��Ƿ��ж���,���û���򴴽�
     * ��ֻ֤����һ������
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
     * ���ϴ˷�����32���ж�Ϊͬһ����
     * �����滻�������л�ʱ���صĲ�һ���Ķ���
     * @return
     */
    public Object readResolve(){
        return person3;
    }
}
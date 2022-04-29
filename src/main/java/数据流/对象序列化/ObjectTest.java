package ������.�������л�;

import java.io.*;

/**
 * ������ʹ������ǳ���һ
 * ��Ҫ���ڶ�������л��ͷ����л�
 * �Ѷ���ת���ɶ���������,���Ա����ڴ�����,����
 */
public class ObjectTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // ����һ������
        Person p = new Person();
        // �����Ը�ֵ
        p.name = "����";
        p.age = 18;
        p.sex = "��";
        // ͨ���ӿڴ����ļ������,tempΪ��ʱ�ļ�,�������ļ�,�޷���
        OutputStream out = new FileOutputStream("D:/abc.temp");
        // �������������,��װ�ļ������
        BufferedOutputStream bout = new BufferedOutputStream(out);
        // ʹ�ö��������,��װ���������
        ObjectOutputStream oout = new ObjectOutputStream(bout);
        // �����������������
        oout.writeObject(p);
        /*
         * �Ѷ���������ļ���ȡ����
         * �����ļ�������
         */
        FileInputStream fis = new FileInputStream("D:/abc.temp");
        // ��������������
        BufferedInputStream bis = new BufferedInputStream(fis);
        // ��������������
        ObjectInputStream ois = new ObjectInputStream(bis);
        // �õ�����
        Person p2 = (Person) ois.readObject();
        // �������Ķ�������
        System.out.println(p2.name+p2.age+p2.sex);
    }
}

/**
 * Ҫʵ�ֶ������л�����ʵ�����л��ӿ�
 */
class Person implements Serializable{
    // ����
    public String name;
    // ����
    public int age;
    // �Ա�,transient�ؼ��ֱ�ʾ�����Բ��ܱ����л�
    public transient String sex;
}

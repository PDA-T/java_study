package ������.�������л�;

import java.io.*;

/**
 * ������ʹ������ǳ���һ
 * ��Ҫ���ڶ�������л��ͷ����л�
 * �Ѷ���ת���ɶ���������
 */
public class Student implements Serializable {
    private String stuno;// ѧ�����
    private String stuname;// ѧ������
    private transient int stuage;// ѧ������,transient���κ󲻻����jvmĬ�ϵ����л�
    /**
     * �����вι��췽��
     * @param stuno ѧ�����
     * @param stuname ѧ������
     * @param stuage ѧ������
     */
    public Student(String stuno, String stuname, int stuage) {
        this.stuno = stuno;// ��ֵѧ�����
        this.stuname = stuname;// ��ֵѧ������
        this.stuage = stuage;// ��ֵѧ������
    }
    /**
     * �����޲ι��췽��
     */
    public Student(){
    }
    public String getStuno() {
        return stuno;// ����ѧ�����
    }
    public void setStuno(String stuno) {
        this.stuno = stuno;// ��ֵѧ�����
    }
    public String getStuname() {
        return stuname;// ����ѧ������
    }
    public void setStuname(String stuname) {
        this.stuname = stuname;// ��ֵѧ������
    }
    public int getStuage() {
        return stuage;// ����ѧ������
    }
    public void setStuage(int stuage) {
        this.stuage = stuage;// ��ֵѧ������
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
        s.defaultWriteObject();// ��jvm��Ĭ�����л���Ԫ�ؽ������л�����
        s.writeInt(stuage);// �ֽ����stuage�����л�
    }
    private void readObject(java.io.ObjectInputStream s)throws java.io.IOException,ClassNotFoundException{
        s.defaultReadObject();// ��jvm��Ĭ�Ϸ����л���Ԫ�ؽ��з����л�����
        this.stuage=s.readInt();// �Լ����stuage�ķ����л�����
    }
}
/**
 * ����������
 */
class ObjectSeriaDemo1{
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String file="C:/Users/15811/Desktop/abc.txt";// ����·��
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(file));// ʵ�������л���
        Student stu=new Student("10010","����",20);// ���ù��췽��
        oos.writeObject(stu);// д��
        oos.flush();// ˢ�»�����
        oos.close();// �ر����л���
        // ��ȡ����
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream(file));// ʵ���������л���
        Student stu2=(Student) ois.readObject();// ObjǿתStudent����
        System.out.println(stu);// ��ӡ���
        ois.close();// �رշ����л���
    }
}
/**
 * ����͸��๹�캯���ĵ���
 */
class ObjectSeriaDemo2{
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // ʵ�������л���
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("C:/Users/15811/Desktop/abc.txt"));
        Foo2 foo2=new Foo2();// ʵ����Foo2��
        oos.writeObject(foo2);// д��
        oos.flush();// ˢ�»�����
        oos.close();// �ر����л���
        // �����л��Ƿ�ݹ���ø���Ĺ��췽��
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream("C:/Users/15811/Desktop/abc.txt"));
        Foo2 foo3=(Foo2) ois.readObject();// ���ö�ȡ����
        oos.writeObject(foo3);// д��
        System.out.println(foo3);// ��ӡ���
        ois.close();// �رշ����л���
    }
}
/**
 * һ����ʵ�������л��ӿ�,��ô�����඼���Խ������л�
 */
class Foo implements Serializable{
    public Foo(){// �������췽��
        System.out.println("foo...");// ��ӡ
    }
}
class Foo1 extends Foo{
    public Foo1(){// �������췽��
        System.out.println("foo1...");// ��ӡ
    }
}
class Foo2 extends Foo1{
    public Foo2(){// �������췽��
        System.out.println("foo2...");// ��ӡ
    }
}
class Bar{
    public Bar(){// �������췽��
        System.out.println("Bar");// ��ӡ
    }
}
class Bar1 extends Bar implements Serializable{
    public Bar1(){// �������췽��
        System.out.println("Bar1...");// ��ӡ
    }
}
class Bar2 extends Bar1{
    public Bar2(){// �������췽��
        System.out.println("Bar3...");// ��ӡ
    }
}

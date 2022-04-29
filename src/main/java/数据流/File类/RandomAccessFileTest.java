package ������.File��;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * ��������ļ���
 * ���Դ��ļ����κ�λ�ÿ�ʼ��ȡ
 */
public class RandomAccessFileTest {
    public static void main(String[] args) throws IOException {
        // ������Ա����
        Employee employee = new Employee("zhangzheng",12);
        Employee employee2 = new Employee("chengqing",15);
        Employee employee3 = new Employee("yanghaoyu",20);
        // �������������,rw��ʾ���ļ���д,r��ʾֻ��,rwd��ʾд������ϱ���
        RandomAccessFile rf = new RandomAccessFile("D:/abc.txt","rw");
        // �������������,������ȡ
        RandomAccessFile rfr = new RandomAccessFile("D:/abc.txt","r");
        // д��Ա��1
        rf.write(employee.getName().getBytes());
        rf.writeInt(employee.getAge());
        // д��Ա��2
        rf.write(employee2.getName().getBytes());
        rf.writeInt(employee2.getAge());
        // д��Ա��3
        rf.write(employee3.getName().getBytes());
        rf.writeInt(employee3.getAge());
        // ��ȡ�ڶ���Ա����Ϣ,һ��Ա������8�ֽ�+����4�ֽ�,һ��12�ֽ�,��12�ֽ�
        rfr.skipBytes(12);
        // �����ַ���
        String str = "";
        // ѭ��8�ζ�ȡ����8�ֽ�
        for (int i=0;i<8;i++){
            // ��ȡһ�ֽ�
            str += (char) rfr.readByte();
        }
        // �������
        System.out.println("Ա��2������:"+str);
        // �������
        System.out.println("Ա��2������:"+rfr.readInt());
        // ��ԭָ�뵽0��λ��
        rfr.seek(0);
        // �����ַ���
        String str2 = "";
        // ѭ��8�ζ�ȡ����8�ֽ�
        for (int i=0;i<8;i++){
            // ��ȡһ�ֽ�
            str2 += (char) rfr.readByte();
        }
        // �������
        System.out.println("Ա��1������:"+str2);
        // �������
        System.out.println("Ա��1������:"+rfr.readInt());
        // ����12�ֽڶ�ȡԱ��3
        rfr.skipBytes(12);
        // �����ַ���
        String str3 = "";
        // ѭ��8�ζ�ȡ����8�ֽ�
        for (int i=0;i<8;i++){
            // ��ȡһ�ֽ�
            str3 += (char) rfr.readByte();
        }
        // �������
        System.out.println("Ա��1������:"+str3);
        // �������
        System.out.println("Ա��1������:"+rfr.readInt());
        // �ر���
        rf.close();
        rfr.close();
    }
}

/**
 * ��Ա��
 */
class Employee{
    // ����
    private String name;
    // ����
    private int age;
    // ��ʼ��
    public Employee(String name,int age){
        // ��װ����,Ҫ��8�ֽ�,�������8�ֽ�
        if (name.length()>8){
            // ��ȡ����ǰ8���ֽ�
            name = name.substring(0,8);
        }else {
            // �������С��8�ֽ�
            while (name.length()<8){
                // ���ֺ���ӿո�
                name = name + "/u0000";
            }
            // ����8�ֽ�,��ʼ������
            this.name = name;
            // ��ʼ������
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
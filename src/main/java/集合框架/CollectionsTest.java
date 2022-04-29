package ���Ͽ��;

import java.util.*;

public class CollectionsTest {
    /**
     * ͨ��Collections.sort()����,��Integer���͵�List��������
     */
    public void testSort1(){
        List<Integer> integerList=new ArrayList<Integer>();// �����б�ӿ�ʵ����ʵ����
        Random random=new Random();// ���������
        Integer k;// ����������������
        for (int i=0;i<10;i++){// ѭ����List�в��������
            do {// �Ƚ�������
                k=random.nextInt(100);// �����ȡ����
            }while (integerList.contains(k));// �ж��Ƿ��ظ�
            integerList.add(k);// �б��м��������
            System.out.println("�ɹ��������:"+k);// ��ӡ���
        }
        System.out.println("----------------����ǰ----------------");// �ָ���
        for (Integer integer:integerList){// ѭ������
            System.out.println("Ԫ��:"+integer);// ��ӡ�������ֵ
        }
        Collections.sort(integerList);// ���򷽷�
        System.out.println("----------------�����----------------");// �ָ���
        for (Integer integer:integerList){// ѭ������
            System.out.println("Ԫ��:"+integer);// ��ӡ�������ֵ
        }
    }
    /**
     * ��String���͵�List��������
     */
    public void testSort2(){
        List<String> stringList=new ArrayList<String>();// �����б�ӿ�ʵ����ʵ����
        stringList.add("Google");// ���б�������ַ���
        stringList.add("Microsoft");// ���б�������ַ���
        stringList.add("lenovo");// ���б�������ַ���
        System.out.println("----------------����ǰ----------------");// �ָ���
        for (String string:stringList){// ѭ������
            System.out.println("Ԫ��:"+string);// ��ӡ�������ֵ
        }
        Collections.sort(stringList);// ���򷽷�
        System.out.println("----------------�����----------------");// �ָ���
        for (String string:stringList){// ѭ������
            System.out.println("Ԫ��:"+string);// ��ӡ�������ֵ
        }
    }
    public void testSort3(){
        List<Student> studentList=new ArrayList<Student>();// �����б�ӿ�ʵ����ʵ����
        Random random=new Random();// �������������
        studentList.add(new Student(random.nextInt(1000)+"","Mike"));// �б�����Ӷ���
        studentList.add(new Student(random.nextInt(1000)+"","Angela"));// �б�����Ӷ���
        studentList.add(new Student(random.nextInt(1000)+"","Lucy"));// �б�����Ӷ���
        studentList.add(new Student(10000+"","Beyonce"));// �б�����Ӷ���
        System.out.println("----------------����ǰ----------------");// �ָ���
        for (Student student:studentList){// ѭ������
            System.out.println("ѧ��:"+student.getId()+":"+student.getName());// ��ӡ�������ֵ
        }
        Collections.sort(studentList);// ���򷽷�
        System.out.println("----------------�����----------------");// �ָ���
        for (Student student:studentList){// ѭ������
            System.out.println("ѧ��:"+student.getId()+":"+student.getName());// ��ӡ�������ֵ
        }
        Collections.sort(studentList,new StudentComparator());// ���򷽷�
        System.out.println("----------------�������������----------------");// �ָ���
        for (Student student:studentList){// ѭ������
            System.out.println("ѧ��:"+student.getId()+":"+student.getName());// ��ӡ�������ֵ
        }
    }
    public static void main(String[] args) {
        CollectionsTest ct=new CollectionsTest();// ʵ��������
//        ct.testSort1();// ��������1����
//        ct.testSort2();// ��������2����
        ct.testSort3();// ��������3����
    }
}

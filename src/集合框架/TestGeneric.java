package ���Ͽ��;

import java.util.ArrayList;
import java.util.List;

public class TestGeneric {// �������Ͳ�����
    public List<Course> courses;// ���з��͵��б�
    public TestGeneric(){// �������췽��
        this.courses=new ArrayList<Course>();// ʵ�����ӿ�
    }
    /**
     * �������
     */
    public void testAdd(){// ������ӷ���
        Course cr1=new Course("1","��ѧ����");// ʵ������
        courses.add(cr1);// ���Ԫ�ص��б�
//        courses.add("�ַ���");// ���ͼ����в�����ӹ涨���ͼ���������Ķ���
        Course cr2=new Course("2","Java");// ʵ������
        courses.add(cr2);// ���Ԫ�ص��б�
    }
    /**
     * ����ѭ������
     */
    public void testForEach(){// ������������
        System.out.println("for eachѭ��");
        for (Course cr:courses){// ����for eachѭ��,����Ҫobjǿת
            System.out.println(cr.getId()+","+cr.getName());// ���Ԫ��
        }
    }
    /**
     * ���Է��ͼ��Ͽ�����ӷ�������Ķ���ʵ��
     */
    public void testChild(){// �������Է���
        ChildCourse ccr=new ChildCourse();// ʵ������
        ccr.setId("3");// ��ֵ
        ccr.setName("�������");// ��ֵ
        courses.add(ccr);// �������ʵ��
    }
    /**
     * ���Ͳ���ʹ�û�������
     */
    public void testBasicType(){// ������
        List<Integer> list=new ArrayList<Integer>();// ʵ�����ӿ�
        list.add(1);// ���Ԫ��
        System.out.println("�������͵İ�װ:"+list.get(0));// ��ӡ���
    }

    /**
     * ��������
     */
    public static void main(String[] args) {
        TestGeneric tg=new TestGeneric();
        tg.testAdd();
        tg.testForEach();
        tg.testChild();
        tg.testForEach();
        tg.testBasicType();
    }
}

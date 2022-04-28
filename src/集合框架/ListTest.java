package ���Ͽ��;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ListTest {// ������ѡ�γ���
    public List coursesToSelect;// �������ڴ�ű�ѡ�γ̵�����
    public ListTest(){// �������췽��
        this.coursesToSelect=new ArrayList();// ʵ����List�ӿ�
    }
    public void testAdd(){// ��������������ӱ�ѡ�γ�
        /**
         * ����Ԫ�����
         */
        Course cr1=new Course("1","���ݽṹ");// ����һ���γ̶��󣬲�ͨ������Add��������ӵ���ѡ�γ�������
        coursesToSelect.add(cr1);// ��ӵ��б�
        Course temp=(Course) coursesToSelect.get(0);// ��ȡ�б��0λ��ֵ
        System.out.println("����˿γ�:"+temp.getId()+":"+temp.getName());// ��ӡ���

        coursesToSelect.add(cr1);// ��ӵ��б�
        Course temp0=(Course) coursesToSelect.get(1);// ��ȡ�б��1λ��ֵ
        System.out.println("����˿γ�:"+temp0.getId()+":"+temp0.getName());// ��ӡ���

        Course cr2=new Course("2","C����");// �����γ̶���
        coursesToSelect.add(0,cr2);// ��ӵ��б��0λ
        Course temp2=(Course) coursesToSelect.get(0);// ��ȡ�б��0λ��ֵ
        System.out.println("����˿γ�:"+temp2.getId()+":"+temp2.getName());// ��ӡ���

        /**
         * �����±�Խ�磬���ȴ����б�ֵ��С��0
         */
//        Course cr3=new Course("3","test");// �����γ̶���
//        coursesToSelect.add(4,cr3);// ��ӵ�����
//        Course temp3=(Course) coursesToSelect.get(0);// ��ȡ����ֵ,ǿ��ת������
//        System.out.println("����˿γ�:"+temp3.getId()+":"+temp3.getName());// ��ӡ���
        /**
         * ���Ԫ�����
         */
        Course[] course={new Course("3","��ɢ��ѧ"),new Course("4","�������")};// ���鴴���γ�
        coursesToSelect.addAll(Arrays.asList(course));// �������Ԫ�ص��б�ڶ�λ��
        Course temp3=(Course) coursesToSelect.get(3);// ��ȡ�б�ڶ�λ��ֵ
        Course temp4=(Course) coursesToSelect.get(4);// ��ȡ�б�ڶ�λ��ֵ
        System.out.println("��������ſγ�:"+temp3.getId()+":"+temp3.getName());// ��ӡ���
        System.out.println("��������ſγ�:"+temp4.getId()+":"+temp4.getName());// ��ӡ���

        Course[] course2={new Course("5","�ߵ���ѧ"),new Course("6","��ѧӢ��")};// ���鴴���γ�
        coursesToSelect.addAll(2,Arrays.asList(course2));// �������Ԫ�ص��б�ڶ�λ��
        Course temp5=(Course) coursesToSelect.get(2);// ��ȡ�б�ڶ�λ��ֵ
        Course temp6=(Course) coursesToSelect.get(3);// ��ȡ�б�ڶ�λ��ֵ
        System.out.println("��������ſγ�:"+temp5.getId()+":"+temp5.getName());// ��ӡ���
        System.out.println("��������ſγ�:"+temp6.getId()+":"+temp6.getName());// ��ӡ���
    }
    /**
     * ��ȡ�б�ֵ
     */
    public void getGet(){// ������ȡ�б�ֵ����
        int size=coursesToSelect.size();// ��ȡ�б���
        System.out.println("���¿γ̴�ѡ:");// ��ӡ�����ʾ
        for (int i=0;i<size;i++){// ѭ����ȡ�б�ȫ��Ԫ��
            Course cr=(Course) coursesToSelect.get(i);// ��ȡ�б�Ԫ��
            System.out.println(cr.getId()+":"+cr.getName());// ����б�Ԫ��
        }
    }

    /**
     * ͨ���������������б�
     */
    public void testIterator(){// ��������������
        Iterator it=coursesToSelect.iterator();// ��ֵ�ӿ�
        System.out.println("���¿γ̴�ѡ:(ͨ��������)");// ��ӡ�����ʾ
        while (it.hasNext()){// �������Ԫ�ؾͷ���true
            Course cr=(Course) it.next();// ����������һ��ֵ
            System.out.println(cr.getId()+":"+cr.getName());// ����б�Ԫ��
        }
    }

    /**
     * ͨ��for each�������ʼ���Ԫ��
     */
    public void testForEach(){// ����for each����
        System.out.println("���¿γ̴�ѡ:(for each����)");// ��ӡ�����ʾ
        for (Object obj:coursesToSelect){// ����for eachѭ��
            Course cr=(Course) obj;// ǿ��ת������
            System.out.println(cr.getId()+":"+cr.getName());// ����б�Ԫ��
        }
    }

    /**
     * �޸��б�Ԫ��
     */
    public void testModify(){// �����޸ķ���
        coursesToSelect.set(4,new Course("7","ë��"));// �޸ĵ�4λԪ��
    }

    /**
     * ɾ���б���Ԫ��
     */
    public void testRemove(){// ����ɾ������
//        Course cr=(Course) coursesToSelect.get(4);// ��ȡ�б�4λ�õ�Ԫ��
//        coursesToSelect.remove(4);// ɾ����4λ�γ�
        Course[] courses={(Course) coursesToSelect.get(4),(Course) coursesToSelect.get(5)};// ������ɾ��Ԫ��λ��
        coursesToSelect.removeAll(Arrays.asList(courses));// ɾ����λԪ��
        testForEach();// ���ñ�������
    }
    /**
     * ���б��������һЩ��������
     */
    public void testType(){// ��������
        System.out.println("�����������");
        coursesToSelect.add("�ַ�");
    }
    /**
     * ��������
     */
    public static void main(String[] args) {
        ListTest t=new ListTest();
        t.testAdd();
//        t.getGet();
//        t.testIterator();
//        t.testForEach();
//        t.testModify();
//        t.testForEach();
//        t.testRemove();
        t.testType();
        t.testForEach();
    }
}

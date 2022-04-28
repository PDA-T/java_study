package ���Ͽ��;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * HashSet������,�����ظ��ļ���
 */
public class SteTest {
    public List<Course> coursesToSelect;// �����б����
    private Scanner console;// ����ɨ����
    public Student student;// ����ѧ����
    public SteTest(){// �������췽��
        coursesToSelect=new ArrayList<Course>();// ʵ�����ӿ�
        console=new Scanner(System.in);// ʵ����ɨ����
    }

    /**
     * ��ӷ���
     */
    public void testAdd(){// ��������������ӱ�ѡ�γ�
        Course cr1=new Course("1","���ݽṹ");// ����һ���γ̶��󣬲�ͨ������Add��������ӵ���ѡ�γ�������
        coursesToSelect.add(cr1);// ��ӵ��б�
        Course temp=(Course) coursesToSelect.get(0);// ��ȡ�б��0λ��ֵ

        Course cr2=new Course("2","C����");// �����γ̶���
        coursesToSelect.add(0,cr2);// ��ӵ��б��0λ

        Course[] course={new Course("3","��ɢ��ѧ"),new Course("4","�������")};// ���鴴���γ�
        coursesToSelect.addAll(Arrays.asList(course));// �������Ԫ�ص��б�ڶ�λ��

        Course[] course2={new Course("5","�ߵ���ѧ"),new Course("6","��ѧӢ��")};// ���鴴���γ�
        coursesToSelect.addAll(2,Arrays.asList(course2));// �������Ԫ�ص��б�ڶ�λ��
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
     * ����List��contains����
     * contains()�����ײ�Ϊ�������equals()�Ա�,��дequals()�����޸�contains()���ж�ֵ
     */
    public void testListContains(){
        Course course=coursesToSelect.get(0);// ��ȡ��0����Ԫ��
        System.out.println("ȡ�ÿγ�:"+course.getName());// ��ӡ���coursesToSelect�Ƿ����course����
        //��ӡ����Ƿ����
        System.out.println("��ѡ�γ��Ƿ�����γ�:"+course.getName()+","+coursesToSelect.contains(course));
        System.out.println("������γ�����:");// ��ʾ����γ�����
        String name=console.next();// ��ȡ�����ֵ
        Course course2=new Course();// ʵ������
        course2.setName(name);// ���nameֵ
        System.out.println("�´����γ�:"+course2.getName());// ��ӡ���
        //��ӡ����Ƿ����
        System.out.println("��ѡ�γ��Ƿ�����γ�:"+course2.getName()+","+coursesToSelect.contains(course2));
        if (coursesToSelect.contains(course2)){// �ж��Ƿ�Ϊ��
            // ͨ��indexOF()������ȡ��ĳԪ�ص�����λ��
            System.out.println("�γ�:"+course2.getName()+"����λ��Ϊ:"+coursesToSelect.indexOf(course2));
        }
    }

    /**
     * ����ѧ��������ѡ��
     */
    public void createStudentAndSelectCoure(){
        student=new Student("1","С��");// ����ѧ������
        System.out.println("���:"+student.getId()+student.getName());// ��ӡ���
        Scanner console=new Scanner(System.in);// ����ɨ����
        for (int i=0;i<3;i++){// ����ѭ��
            System.out.println("������γ�ID:");// ��ӡ���
            String courseId=console.next();// ָ��ָ����һ����¼����ֵ�ͷ���true���Ѽ�¼���ݴ��뵽��Ӧ�Ķ�����
            for (Course cr:coursesToSelect) {// ����for eachѭ��
                if (cr.getId().equals(courseId)){// ����if�Ƚ������ֵ
                    student.courese.add(cr);// ���Ԫ��
                }
            }
        }
    }
    /**
     * ����Set��contains����
     */
    public void testSetContains(){
        System.out.println("������ѧ����ѡ�Ŀγ�����:");// ��ӡ��ʾ
        String name=console.next();// ��ȡ�����ֵ
        Course course=new Course();// �����γ̶���
        course.setName(name);// ����������
        System.out.println("�´����γ�:"+course.getName());// ��ӡ���
        /**
         * Set��ѯ�ײ�Ϊ����hashCode()�ж�ֵ�����equals()�����ٴ��ж�,����������true.
         * ��дhashCode()
         */
        System.out.println("��ѡ�γ����Ƿ�����γ�:"+course.getName()+","+student.courese.contains(course));
    }
    public static void main(String[] args) {
        SteTest st=new SteTest();// ʵ������
        st.testAdd();// ������ӷ���
        st.testListContains();// ������ӷ���
        st.testForEach();// ���ñ�������
//        st.testForEachForSet(student);// ���ñ�������
//        st.createStudentAndSelectCoure();// ����ѧ��������ѡ��
//        st.testSetContains();// ����contains����
    }
    public void testForEachForSet(Student student){// ������������
        System.out.println("��ѡ����:"+student.courese.size());// ��ӡ���ȫ��ѡ��Ŀγ�
        for (Course cr:student.courese){// ��ӡ���ѧ��ѡ�Ŀγ�
            System.out.println("ѡ����:"+cr.getId()+cr.getName());// ��ӡ���
        }
    }
}

package ����;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
/**
 * ����Ĳ������Ǳ���֮��Ĳ���
 */
public class MethodDemo2 {
    public static void main(String[] args) {
        ArrayList list=new ArrayList();// �����޷����б�
        ArrayList<String> list2=new ArrayList<String>();// �����з����б�
        list2.add("hello");// ����String��������
//        list2.add(20);// ����int��������,��ֹ�������
        Class c=list.getClass();// ��ȡ���������
        Class c2=list2.getClass();// ��ȡ���������
        System.out.println(c==c2);// ��ӡ�Ƿ����
        // ������Ϊ�˷�ֹ�������,ֻ�ڱ���׶���Ч,�ƹ��������Ч
        try {// ��׽�쳣
            Method m=c2.getMethod("add",Object.class);// ͨ��������������֤
            m.invoke(list2,20);// ͨ��������÷���
            System.out.println(list2.size());// ����б���
            System.out.println(list2);// �ƹ����뷺����Ч,String���ͼ�����int����ֵ,����ʹ��forѭ������
        } catch (NoSuchMethodException e) {// �����쳣
            e.printStackTrace();// ��ӡ�쳣
        } catch (InvocationTargetException e) {// �����쳣
            e.printStackTrace();// ��ӡ�쳣
        } catch (IllegalAccessException e) {// �����쳣
            e.printStackTrace();// ��ӡ�쳣
        }
    }
}

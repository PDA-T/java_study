package ����;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodDemo {
    public static void main(String[] args) {
        A a=new A();// ʵ��������
        Class c=A.class;// ��ȡ���������
        try {// ��׽�쳣
            Method m=c.getMethod("print",new Class[]{int.class,int.class});// ��ȡpublic����,��������,�����б�
            Method m2=c.getDeclaredMethod("print",new Class[]{int.class,int.class});// ��ȡ�Լ������ķ���
            Method m3=c.getDeclaredMethod("print",int.class,int.class);// �˷����������Ҳ����
            Object o=m.invoke(a,new Object[]{10,20});// ͨ��������÷���,�������û�÷���ֵ����null
            Object o2=m.invoke(a,10,20);// �˷����������Ҳ����
            System.out.println("----------------------------------------------");// �ָ���
            Method m4=c.getMethod("print",String.class,String.class);// ��ȡpublic����,��������,�����б�
            o=m4.invoke(a,"hello","HELLO");// ͨ��������÷���
//            Method m5=c.getMethod("print",new Class[]{});// ������
            Method m5=c.getMethod("print");// û�в������Բ���
//            m5.invoke(a,new Object[]{});// ������
            m5.invoke(a);// û�в������Բ�д
        } catch (NoSuchMethodException e) {// �����쳣
            e.printStackTrace();// ��ӡ�쳣
        } catch (InvocationTargetException e) {// �����쳣
            e.printStackTrace();// ��ӡ�쳣
        } catch (IllegalAccessException e) {// �����쳣
            e.printStackTrace();// ��ӡ�쳣
        }
    }
}
class A{// ������
    public void print(){// �����޲η���
    }
    public void print(int a,int b){// �����������
        System.out.println(a+b);;// ��ӡ���
    }
    public void print(String a,String b){// �����������
        System.out.println(a.toUpperCase()+","+b.toLowerCase());// ��ӡ���ת����д,ת��Сд
    }
}

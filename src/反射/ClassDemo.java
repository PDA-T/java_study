package ����;

public class ClassDemo {
    public static void main(String[] args) {
        Foo foo=new Foo();// ����Foo�����
        Class c1=Foo.class;// ��ĵ�һ�ַ��䷽��
        Class c2=foo.getClass();// �ڶ��ַ��䷽��
        Class c3=null;// ��ֵ��
        try {// ��׽�쳣
            // ��̬������
            c3=Class.forName("����.Foo");// �����ַ��䷽ʽ,��Ҫд���ȫ��
        } catch (ClassNotFoundException e) {// �����쳣
            e.printStackTrace();// ��ӡ�쳣
        }
        try {// ��׽�쳣
            Foo foo2=(Foo) c1.newInstance();// ͨ����ķ��䴴������,��Ҫǿת
            foo2.print();// ��Ҫ���޲ι��췽��
        } catch (InstantiationException e) {// �����쳣
            e.printStackTrace();// ��ӡ�쳣
        } catch (IllegalAccessException e) {// �����쳣
            e.printStackTrace();// ��ӡ�쳣
        }
        System.out.println(c1==c2);// ���
    }
}
/**
 * ��ķ���Ϊ������
 */
class Foo{// �౾��Ҳ�Ƕ���
    public void print(){// �����������
        System.out.println("null");// ��ӡ���
    }
}

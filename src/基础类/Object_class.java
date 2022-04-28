package ������;

import java.util.Objects;

public class Object_class {
    public static void main(String[] args) {
        Object_class demo=new Object_class();// ��������
        System.out.println(demo.hashCode());// ͨ���ڴ��ַ�����һ������
        Object_class demo2=new Object_class();// ��������
        System.out.println(demo2.hashCode());// ͨ���ڴ��ַ�����һ������
    }
}

/**
 * Object������
 */
class Object_tool {
    public void main(){
        Integer a1[]=new Integer[]{1,2,3};
        Integer a2[]=new Integer[]{1,2,3};
        // �±�ֵȫ��һ������true
        System.out.println(Objects.deepEquals(a1,a2));
        String string="a";
        // �����ָ���쳣
        System.out.println(Objects.hashCode(string));
        System.out.println("a".hashCode());
        System.out.println(Objects.hashCode("a"));
        // �ӹ�������(+31)
        System.out.println(Objects.hash("a"));
        String s="aaa";
        // ��ֹ��ֵ����
        System.out.println(Objects.toString(s));
        String n=null;
        Objects.requireNonNull(n,"��");
    }
}

/**
 * clone��������ʵ�ֿɿ�¡Cloneable�ӿ�
 */
class Object_clone implements Cloneable{
    public void Object_clone(){
        // ��Ϊclone����protected���εķ������Ե���clone��������������Ķ���,�����Ǹ��������
        Object_clone demo=new Object_clone();
        try {
            /*
             * ǳ��¡����¡����ֻ��¡����(�������µ��ڴ�ռ�),���Դ�����ʡ�ڴ�ռ�
             */
            java.lang.Object obj=demo.clone();// ��¡,ʹ��������ձ���ǿת
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println(demo.toString());// Ĭ�Ͽ��Բ�д
    }
    // ����toString����
    @Override
    public String toString(){
        return "Demo []";
    }
}
package ����;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassUtil {
    /**
     * ��ȡ��Ա����
     */
    public static void printClassMethodMessage(Object obj){// ��ӡ�����Ϣ,������ĳ�Ա����,��Ա����
        Class c=obj.getClass();// ��ȡ�����������
        System.out.println("�������ʱ:"+c.getName());// ��ȡ�������
        Method[] ms=c.getMethods();// Method�Ƿ�������getMethods()�ǻ�ȡ���е�public����
//        c.getDeclaredMethods();// ��ȡ�����и����Լ������ķ���,�޷���Ȩ������
        for (int i=0;i<ms.length;i++){// ѭ����������
            Class returnType=ms[i].getReturnType();// ��ȡ�����ķ���ֵ������
            System.out.print(returnType.getName()+"");// ��ȡ�������͵�����
            System.out.print(ms[i].getName()+"(");// ��ȡ����������
            Class[] paramTypes=ms[i].getParameterTypes();// ��ȡ�����б��������
            for (Class class1:paramTypes){// ѭ����������
                System.out.print(class1.getName()+";");// ��ȡ������
            }
            System.out.println(")");// ��ӡ�����һ������
            /**
             * ��Ա����Ҳ�Ƕ���Field��
             */
        }
    }
    /**
     * ��ȡ��Ա����
     */
    public static void printFieldMessage(Object obj){// ��Ա����Ҳ�Ƕ���Field��
        Class c=obj.getClass();// ��ȡ�����������
        Field[] fs=c.getFields();// ��ȡ����public�ĳ�Ա������Ϣ
        Field[] fs2=c.getDeclaredFields();// ��ȡ�����Լ������ĳ�Ա������Ϣ
        for (Field field:fs2){// ѭ������
            Class fieldType=field.getType();// �õ���Ա���������͵�������
            String typeName=fieldType.getName();// �õ���Ա�������͵�����
            String fieldName=field.getName();// �õ���Ա����������
            System.out.println(typeName+" "+fieldName);// ��ӡ���
        }
    }
    /**
     * ��ȡ����Ĺ��캯����Ϣ
     */
    public static void printConMessage(Object obj){// ���캯��Ҳ�Ƕ���
        Class c=obj.getClass();// ��ȡ�����������
        Constructor[] cs=c.getConstructors();// ��ȡ���е�public���췽��
        Constructor[] cs2=c.getDeclaredConstructors();// // ��ȡ�����Լ������Ĺ��췽��
        for (Constructor constructor:cs){// ѭ������
            System.out.print(constructor.getName()+"(");// ��ӡ���췽������
            Class[] paramTypes=constructor.getParameterTypes();// ��ȡ���췽���Ĳ����б�
            for (Class class1:paramTypes){// ѭ������
                System.out.print(class1.getName()+",");// ��ӡ�������
            }
            System.out.println(")");// ��ӡ���
        }
    }
}
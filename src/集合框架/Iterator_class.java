package ���Ͽ��;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Iterator_class {
    public static void main(String[] args) {
        // ʹ�ýӿ�ʵ�������϶���
        Collection collection = new ArrayList();
        // �ڼ��������ֵ
        collection.add("1");
        collection.add("2");
        collection.add("3");
        // ɾ��ֵ
        collection.remove("1");
        // ������������������
        Iterator iterator = collection.iterator();
        // �жϵ������Ƿ���ֵ,�����ֵ����ѭ��
        while (iterator.hasNext()){
            // ����������ֵ��ֵ��String
            String str = (String) iterator.next();
            // ���
            System.out.println(str);
        }
    }
}

package ���Ͽ��;

import java.util.ArrayList;
import java.util.List;

/**
 * List���������򼯺�,�ײ�Ϊ����ʵ��
 */
public class List_class {
    public static void main(String[] args) {
        // ��������
        List li = new ArrayList();
        // ���ֵ
        li.add(1);
        li.add(2);
        li.add(3);
        li.add(4);
        li.add(5);
        // ��ͨforѭ������
        for (int i=0;i<li.size();i++){
            System.out.println(li.get(i));
        }
        // ��ָ��λ�����Ԫ��
        li.add(3,44);
        // ��ָ����λ���޸�Ԫ��
        li.set(3,33);
        // ��ȡָ��ֵ������
        System.out.println(li.indexOf(33));
    }
}

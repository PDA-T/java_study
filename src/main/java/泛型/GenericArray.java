package ����;

import java.util.ArrayList;
import java.util.List;

/**
 * ��������
 */
public class GenericArray {
    public static void main(String[] args) {
        // ���鲻��ָ������ķ�������
//        List<String>[] lia = new ArrayList<>[10];// ����
        // �������ʹ��ͨ���
        List<?>[] lia = new ArrayList<?>[10];
        List<?>[] lia2 = new ArrayList[10];
    }
}

package �����ӿ�;

import java.util.Arrays;

/**
 * �����Ŀ��һ������Ҫ����ȴû��ʵ��Comparable�ӿ�
 * �����ʹ��Comparator�ӿ�(��Ƚ���)
 */
public class Comparator {
    public static void main(String[] args) {
        // �������
        Comparable_Dog dog1=new Comparable_Dog();
        dog1.setId(1);
        dog1.setAge(2);
        Comparable_Dog dog2=new Comparable_Dog();
        dog2.setId(2);
        dog2.setAge(5);
        Comparable_Dog dog3=new Comparable_Dog();
        dog3.setId(3);
        dog3.setAge(4);
        Comparable_Dog dog4=new Comparable_Dog();
        dog4.setId(4);
        dog4.setAge(1);
        // ��������
        Comparable_Dog[] arr=new Comparable_Dog[]{dog3,dog4,dog1,dog2};
        // �������
        System.out.println(Arrays.toString(arr));
        // ��������(ͨ��ID����)
        Arrays.sort(arr,new Comparator_Dog());
        // ��������(ͨ��age����)
        Arrays.sort(arr,new Comparator_Dog2());
        // �������
        System.out.println(Arrays.toString(arr));
    }
}

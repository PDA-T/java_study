package �����ӿ�;

import java.util.Arrays;

/**
 * �����Ŀ����ʱ�Ѿ�֪����Ҫ�����ʹ��Comparable�ӿ�
 * Comparable�ӿڵ�compareTo����
 * Ҫ��ʹ��compareTo�����ȴ�С(ʵ��Comparable�ӿ�),֮��ſ�ͨ��sort��������
 */
public class Comparable {
    public static void main(String[] args) {
        String s1="a";
        String s2="c";
        // ͨ��ASCII������ó����(�����ȾͶԱȳ��ȵĲ�ֵ)
        System.out.println(s1.compareTo(s2));
        // �������
        ComParable_Person p1=new ComParable_Person();
        p1.setId(1);
        ComParable_Person p2=new ComParable_Person();
        p2.setId(2);
        ComParable_Person p3=new ComParable_Person();
        p3.setId(3);
        ComParable_Person p4=new ComParable_Person();
        p4.setId(4);
        // ��������
        ComParable_Person[] arr=new ComParable_Person[]{p3,p4,p1,p2};
        // �������
        System.out.println(Arrays.toString(arr));
        // ��������
        Arrays.sort(arr);
        // �������
        System.out.println(Arrays.toString(arr));
    }
}

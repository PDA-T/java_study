package ö��;

public class TestMain {
    public static void main(String[] args) {
        Day days[]=Day.values();// ����values��������ö����
        // ��������
        for (Day day:days){
            System.out.println(day);// ���ö������
        }
        Day monday=Day.valueOf("MONDAY");// ���ص�ֵΪmonday����
//        System.out.println(monday);// ����̨�����MONDAYΪ��������
        System.out.println(monday.ordinal());// ��ȡö�����͵�����ֵ
        Day tuesday=Day.valueOf("TUESDAY");// Ч��������һ��
        Day tuesday1=Day.valueOf(Day.class,"TUESDAY");// Ч��������һ��
        System.out.println(tuesday.ordinal());// ��ȡö�����͵�����ֵ
        System.out.println(tuesday.compareTo(monday));// �ȴ�С,����-1��1
        System.out.println(monday.toString());// ȡ����������
        System.out.println(monday.name());// ȡ����������
    }
}

package ��̬.�б���;

import java.util.ArrayList;
import java.util.List;

public class ArrayListSample {
    public static void main(String[] args) {
        List<String> booklist=new ArrayList<String>();// ʵ�����б������ָ������Ϊ�ַ�������
        booklist.add("��¥��");// ���б����ֵ
        booklist.add("���μ�");// ���б����ֵ
        booklist.add("ˮ䰴�");// ���б����ֵ
        booklist.add("����־");// ���б����ֵ
        booklist.add(0,"����Ե");// ���б�ָ��λ�����ֵ
        System.out.println(booklist);// ����б�
        String bookName=booklist.get(2);// ��ȡ�б�ڶ�����ֵ
        System.out.println(bookName);// ����б�ڶ�����ֵ
        int size=booklist.size();// ��ȡ�б�һ���м���ֵ
        System.out.println(size);// ����б�һ���м���ֵ
        booklist.remove(2);// ɾ���б�ڶ�����ֵ
        System.out.println(booklist);// ���ɾ������б�
        booklist.remove(booklist.size()-1);// �߼�����ɾ���б����һ��ֵ
        System.out.println(booklist);// ���ɾ������б�
        for (String book:booklist){// ѭ������
            System.out.println(book);// ��������б�ֵ
        }
    }
}

package ������.��ӡ�ƻ���;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * ��ӡ������Ϊ��������ӷ���
 * �����ֽڴ�ӡ�����ַ���ӡ��
 */
public class PrintStreamTest {
    public static void main(String[] args) throws FileNotFoundException {
        // �����ļ������
        FileOutputStream fos = new FileOutputStream("D:/abc.txt");
        // ��װΪ��ӡ��
        PrintStream ps = new PrintStream(fos);
        // ����д���κ���������
        ps.print("Google");
        ps.print(123);
        ps.print(12.3);
        ps.print(new Object());
        ps.println();
        ps.print("1+1="+2);
        // �ر�������
        ps.close();
    }

    /**
     * ��ӡ�����Ը�ʽ�����
     */
    public void PrintStreamTest2() throws FileNotFoundException {
        // �����ļ������
        FileOutputStream fos = new FileOutputStream("D:/abc.txt");
        // ��װΪ��ӡ��
        PrintStream ps = new PrintStream(fos);
        // ��������
        int i = 10;
        // �����ַ���
        String s = "��ӡ��";
        // ����С��
        float f = 15.5f;
        // ��ӡ��ʽ��,����%d,�ַ���%s,С��%f,ǰ���ȶ���,���油ȫ
        ps.printf("����%d,Google%s,GitHub%f",i,s,f);
    }
}

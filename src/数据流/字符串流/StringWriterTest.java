package ������.�ַ�����;

import java.io.StringWriter;

/**
 * �ַ��������
 */
public class StringWriterTest {
    public static void main(String[] args) {
        // �����ַ���
        String s = "Goole";
        // �����ַ��������
        StringWriter sw = new StringWriter();
        // д���ַ���
        sw.write(s);
        // ׷���ַ���
        sw.append("Java");
        // ׷���ַ���
        sw.append("GitHub");
        // ˢ�»�����,������������ݴ��ݳ�ȥ
        sw.flush();
        // ��ȡ��������ַ���,StringBuffer��Stringһ��
        StringBuffer sb = sw.getBuffer();
        // �����淽ʽһ��(ת��ΪSting)
        String s1 = sw.toString();
        // ����ַ���,Ч��һ��
        System.out.println(s1);
        System.out.println(sb.toString());
    }
}

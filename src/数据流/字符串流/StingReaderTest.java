package ������.�ַ�����;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * �ַ���������
 */
public class StingReaderTest {
    public static void main(String[] args) throws IOException {
        // �����ַ���
        String s = "Google 1";
        // �����ַ���������
        StringReader sr = new StringReader(s);
        // �����ַ��������
        StringWriter sw = new StringWriter();
        // �����ַ�����
        char[] c = new char[1024];
        // ��¼�Ѿ���ȡ����������ֽڸ���
        int hasRead = 0;
        // ѭ����ȡ
        while ((hasRead = sr.read(c))!=-1){
            // �������������ݷ��������
            sw.write(c,0,hasRead);
        }
        // ����ַ���
        System.out.println(sw.toString());
    }
}

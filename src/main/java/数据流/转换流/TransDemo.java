package ������.ת����;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * ����ַ���
 * �ɶԶ�ȡ�����ַ����ݾ���ָ������ת�����ֽ�
 */
public class TransDemo {
    public static void main(String[] args) {
        // �����ַ����������ַ������
        try(InputStreamReader isr = new InputStreamReader(
                new FileInputStream("D:/abc.txt"),"GBK");
            OutputStreamWriter osw = new OutputStreamWriter(
                new FileOutputStream("D:/abc2.txt"),"UTF-8")) {
            // �����ַ�����
            char[] c = new char[1024];
            // �Ѿ���ȡ�������ݵĳ���
            int hasRead = 0;
            // ѭ��
            while ((hasRead=isr.read(c))!=-1){
                // д��
                osw.write(c,0,hasRead);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

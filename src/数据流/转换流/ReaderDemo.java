package ������.ת����;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * �����ַ���
 * �ɶԶ�ȡ�����ֽ����ݾ���ָ������ת�����ַ�
 */
public class ReaderDemo {
    public static void main(String[] args) throws IOException {
        // �����ļ�������
        FileInputStream in=new FileInputStream("C:/Users/15811/Desktop/abc.txt");
        // �����ַ�������,Ĭ����Ŀ����
        InputStreamReader isr=new InputStreamReader(in,"GBK");
//        int c;// ������ȡ�ֽڱ���
//        while ((c=isr.read())!=-1){// ��ȡ�ֽڱ�������-1��ʼѭ��
//            System.out.print((char) c);// ��ӡ�ַ�
//        }
        // �����ַ�����
        char[] buffer=new char[8*1204];
        // ������ȡ�ֽڱ���
        int c;
        // ��ȡ�ֽڱ�������-1��ʼѭ��
        while ((c=isr.read(buffer,0,buffer.length))!=-1){
            // �����ַ���
            String s=new String(buffer,0,c);
            // ��ӡ�ַ���
            System.out.print(s);
        }
    }
}
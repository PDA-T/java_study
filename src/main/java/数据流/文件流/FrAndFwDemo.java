package ������.�ļ���;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FrAndFwDemo {
    public static void main(String[] args) throws IOException {
        FileReader fr=new FileReader("C:/Users/15811/Desktop/abc.txt");// �����ļ��ַ���
        FileWriter fw=new FileWriter("C:/Users/15811/Desktop/abc.txt");// �����ļ������
        char[] buffer=new char[2056];// �����ַ�����
        int c;// ������ȡ�ֽڱ���
        while ((c=fr.read(buffer,0,buffer.length))!=-1){// ��ȡ�ֽڱ�������-1��ʼѭ��
            fw.write(buffer,0,c);// д���ַ�
            fw.flush();// ˢ�»�����
        }
        fr.close();// �ر��ļ��ַ���
        fw.close();// �ر��ļ������
    }
}

package ������.�ļ���;

import java.io.*;

/**
 * �ļ��ַ���
 * �����ı��Ĵ���ʹ���ַ���
 * ʹ�÷�ʽ���ֽ�������һ��
 */
public class �ļ��ַ��� {
    public static void main(String[] args) {
        File f=new File("C:/Users/15811/Desktop/abc.txt");// �����ļ�����
        FileWriter fr=null;// �����ַ������
        try {
            fr=new FileWriter(f,true);// ʵ�����ַ������,�ڶ�������Ϊtrue��᲻�Ḳ��
            String s="Google";// �����ַ���
            fr.write(s);// ���ַ���д���ı��ĵ�
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fr!=null){// �ж����Ƿ�Ϊ�գ���Ϊ����ر�
                try {
                    fr.close();// �ر��ַ������
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // �����ַ�������
        FileReader re=null;
        try {
            re=new FileReader(f);// ʵ�����ַ�������
            char c[]=new char[1024];// ������
            int i;// �Ѷ����ַ���
            while ((i=re.read(c))!=-1){// ѭ����ȡ�ļ��е����ݣ�ֱ�������ַ������꣬����ȡ������д������(������)
                System.out.println(i);
                System.out.println("�ļ��е�����Ϊ:"+new String(c,0,i));// String�ɽ��ַ�����ת���ɶ�Ӧ�ַ���
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (re!=null){//�ж��Ƿ�Ϊ��
                try {
                    re.close();// �ر��ַ�������
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

package ������.�ļ���;

import java.io.*;

/**
 * �ļ��ֽ���
 * ����ͼƬ����Ƶ�Ĵ���ʹ���ֽ���
 * ʹ�÷�ʽ���ַ�������һ��
 */
public class �ļ��ֽ��� {
    public static void main(String[] args) {
        File f=new File("C:/Users/15811/Desktop/abc.txt");// �����ļ�����
        FileOutputStream out=null;// �����ļ������
        try {
            // ʵ�����ļ����������,����������Ϊtrue�����к��׷�����ݣ�Ϊfalse�����к��滻����
            out=new FileOutputStream(f,true);
            String s="Google";// �����ַ���
            byte b[]=s.getBytes();// ���ַ���ת��Ϊ�ֽ�����
            out.write(b);// ���ֽ������е�����д�뵽�ļ���
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out!=null){// �ж��ļ�����������Ƿ�Ϊ�գ����ǿ���ر�
                try {
                    out.close();// �ر��ļ������
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // �ļ�������
        FileInputStream in=null;
        // java7�Ժ��������д,���д��try��,����Բ���Ҫ�ر�,�Զ��ر�(����Ҫfinally)
        try(FileInputStream in2 = new FileInputStream(f)) {
            in=new FileInputStream(f);// ʵ�����ļ�����������
            byte b2[]=new byte[200];// ������
            /*
             * ����ȡ������д������(������),lenΪ���뻺���������ֽ���
             * read�᷵�ض�ȡ�����ֽڳ���,�����ȡ��ϻ᷵��0
             */
            int len=in.read(b2);
            // String�ɽ��ֽ�����ת���ɶ�Ӧ�ַ���,��0��ʼȡֵ��len����
            System.out.println("�ļ��е�������:"+new String(b2,0,len));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in!=null){// �ж��ļ��������Ƿ�Ϊ�գ����ǿ���ر�
                try {
                    // IO��Դ�������ڴ����Դ,�������ջ����޷�����,���Ա���ر�
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

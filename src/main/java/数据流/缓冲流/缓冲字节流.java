package ������.������;

import java.io.*;

public class �����ֽ��� {
    public static void main(String[] args) {
        File f=new File("C:/Users/15811/Desktop/abc.txt");// �����ļ�����
        BufferedInputStream bi=null;// ��������������
        FileOutputStream out=null;// �����ļ������
        FileInputStream in=null;// �����ļ�������
        BufferedOutputStream si=null;// �������������
        long start=System.currentTimeMillis();// ��õ�ǰ����
        try {
            in=new FileInputStream(f);// ʵ�����ļ�������
            out=new FileOutputStream(f);// ʵ�����ļ������
            bi=new BufferedInputStream(in);// ʵ��������������,���ļ��ֽ�����װ�ɻ�����
            si=new BufferedOutputStream(out);// ʵ�������������
            String s="Google";// �����ַ���
            byte t[]=s.getBytes();// ���ַ���ת��Ϊ�ֽ�����
            si.write(t);// д���ֽ�����
            si.flush();// ˢ��,ǿ�ƽ�����������д���ļ�,��ʹ������û��д��
            byte b[]=new byte[1024];// �����������ֽ�����
            while (bi.read(b)!=-1){// ʹ�û�������ȡ����
            }
            long end=System.currentTimeMillis();// ��ȡ��ǰ����
            System.out.println("���о����ĺ�����:"+(end-start));// ��ȡ����һ�����ж೤ʱ��
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in!=null){// �ж��ļ��������Ƿ�Ϊ��
                try {
                    in.close();// �ر��ļ�������
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bi!=null){// �жϻ����������Ƿ�Ϊ��
                try {
                    bi.close();// �رջ���������
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out!=null){// �ж��ļ�������Ƿ�Ϊ��
                try {
                    out.close();// �ر��ļ������
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

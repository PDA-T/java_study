package ������.������;

import java.io.*;

public class �����ַ��� {
    public static void main(String[] args) {
        File f=new File("C:/Users/15811/Desktop/abc.txt");// �����ļ�����
        FileWriter fw=null;// �����ַ������
        BufferedWriter bw=null;// ���������ַ������
        FileReader fr=null;// �����ַ�������
        BufferedReader br=null;// ���������ַ�������
        try {
            fw=new FileWriter(f);// ʵ�����ַ������
            bw=new BufferedWriter(fw);// ʵ���������ַ������
            fr=new FileReader(f);// ʵ�����ַ�������
            br=new BufferedReader(fr);// ʵ���������ַ�������
            String s="Google";// �ַ���1
            String s2="IDEA";// �ַ���2
            bw.write(s);// д���ַ���1
            bw.newLine();// ������һ��
            bw.write(s2);// д���ַ���2
            String tmp=null;// �������ַ���
            int i=1;// ������
            while ((tmp=br.readLine())!=null){// ��ȡһ��,ѭ����ȡ�ļ�����
                System.out.println("��"+i+"��:"+tmp);// ������
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {// �ȴ���������ر�
            if (bw!=null){// �ж����Ƿ�Ϊ��
                try {
                    bw.close();// �رջ����ַ������
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fw!=null){// �ж����Ƿ�Ϊ��
                try {
                    fw.close();// �ر��ַ������
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br!=null){// �ж����Ƿ�Ϊ��
                try {
                    br.close();// �رջ����ַ�������
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fr!=null){// �ж����Ƿ�Ϊ��
                try {
                    fr.close();// �ر��ַ�������
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

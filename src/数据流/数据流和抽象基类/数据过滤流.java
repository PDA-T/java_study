package ������.�������ͳ������;

import java.io.*;

/**
 * ������
 * ����Ӧ�ó����������Java��������д�����������,���ߴӵײ��ȡ������Java����
 * ������д��,�ļ���Ϊ����
 */
public class ���ݹ����� {
    public static void main(String[] args) {
        File f=new File("C:/Users/15811/Desktop/abc.txt");// �����ļ�����
        FileOutputStream out=null;// �����ļ������
        DataOutputStream dos=null;// �������������
        FileInputStream in=null;// �����ļ�������
        DataInputStream di=null;// ��������������
        try {
            out=new FileOutputStream(f);// ʵ�����ļ������
            dos=new DataOutputStream(out);// ʵ�������������,���ļ�����װ��������
            in=new FileInputStream(f);// ʵ�����ļ�������
            di=new DataInputStream(in);// ʵ��������������
            dos.writeUTF("д���ַ�������");// д���ַ�������
            dos.writeInt(123);// д����������
            dos.writeDouble(3.14);// д�븡��������
            dos.writeBoolean(true);// д�벼����������
            System.out.println("UTF:"+di.readUTF());// ��ȡ�ַ���
            System.out.println("Int:"+di.readInt());// ��ȡ����
            System.out.println("Double:"+di.readDouble());// ��ȡ������
            System.out.println("Boolean:"+di.readBoolean());// ��ȡ������
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dos!=null){// �ж��Ƿ�Ϊ��
                try {
                    dos.close();// �ر����������
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out!=null){// �ж��Ƿ�Ϊ��
                try {
                    out.close();// �ر��ļ������
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(di!=null){// �ж��Ƿ�Ϊ��
                try {
                    di.close();//�ر�����������
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in!=null){// �ж��Ƿ�Ϊ��
                try {
                    in.close();//�ر��ļ�������
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

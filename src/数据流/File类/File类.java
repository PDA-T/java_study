package ������.File��;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class File�� {
    public static void main(String[] args) {
        // ��Ŀ��Ĭ��·��ֱ��д�ļ�������
        // �����ļ�·��:src/·������
        File f1=new File("C:/Users/15811/Desktop/abc.txt");// �����ļ�����
        /*File f2=new File("C:/Users/15811/Desktop","abc.txt");// �ڶ��ַ���
        File wjj=new File("C:/Users/15811/Desktop");// �ļ���
        File f3=new File(wjj,"abc.txt");// �����ַ���
        System.out.println(f1.getAbsolutePath());// ���·��
        System.out.println(f2.getAbsolutePath());// ���·��
        System.out.println(f3.getAbsolutePath());// ���·��
        System.out.println(f1==f2);// ��
        System.out.println(f1.equals(f2));// ��*/
        System.out.println("�ļ��Ƿ����:"+f1.exists());// �ж��ļ��Ƿ����
        System.out.println("�ļ���:"+f1.getName());// ����ļ���
        System.out.println("�ļ�����·��:"+f1.getAbsolutePath());// �������·��
        System.out.println("�Ƿ������ļ�:"+f1.isHidden());// �Ƿ������ļ�
        System.out.println("�ļ���С:"+f1.length());// ����ļ��ֽ���
        //System.out.println("�ļ������޸�ʱ��:"+f1.lastModified());// ����ļ�����ʱ��(����)
        Date d=new Date(f1.lastModified());// ͨ������ֵ����������
        SimpleDateFormat riqi=new SimpleDateFormat("yyyy/MM/dd HH/mm/ss");// ���ڸ�ʽ����
        System.out.println("�ļ�����޸�����:"+riqi.format(d));// �ļ������޸�����
        //System.out.println("ɾ���ɹ�:"+f1.delete());// ɾ���ļ�
        if (f1.delete()==false){// �ж�����ļ������ھʹ���
            try {
                System.out.println("�����ļ��Ƿ�ɹ�:"+f1.createNewFile());// �����ļ�,����ļ����ڲ��ܸ���
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // �ļ��в���
        File dir=new File("C:/Users/15811/Desktop/dir");// �����ļ��ж���
        System.out.println("�ļ��д����ɹ�:"+dir.mkdir());// �����ļ���
        File dir2=new File("C:/Users/15811/Desktop/dir/dir2/dir3/dir4");// �����ļ��ж���
        //System.out.println("����ļ��д����ɹ�:"+dir2.mkdirs());// �ɴ�������ļ���
        System.out.println("�ļ����Ƿ�ɾ��:"+dir.delete());// ɾ���ļ���(ɾ���ļ�·�����һ���ļ���)
        File win=new File("C:/Windows");// �����ļ��ж���
        File file[]=win.listFiles();// �����ļ�����ȫ�����ļ�(�����ļ�����)
        for (File f:file){// �������ص��ļ�����
            if (f.isFile()){// �ж��Ƿ�Ϊ�ļ�
                System.out.println("�ļ���:"+f.getName());// ��ȡ�ļ���
            }else if (f.isDirectory()){// �ж��Ƿ�Ϊ�ļ���
                System.out.println("�ļ�����:"+f.getName());// ��ȡ�ļ�����
            }
        }
    }
}
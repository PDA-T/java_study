package ������;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class RafDemo {
    public static void main(String[] args) throws IOException {
        File demo=new File("C:/Users/15811/Desktop/abc.txt");// �����ļ���
        if (demo.exists()){// ����ļ�������
            demo.mkdir();// �����ļ�
        }
        File file=new File(demo,"C:/Users/15811/Desktop/abc2.txt");// �����ļ���
        if (!file.exists()){// ����ļ�������
            file.createNewFile();// ������ļ��Ѿ�����,�򲻴���,����һ��false,���û��,�򷵻�true
        }
        RandomAccessFile raf=new RandomAccessFile(file,"rw");// ���������д��
        System.out.println(raf.getFilePointer());// ��ӡָ��λ��
        raf.write('A');// ֻд��һ���ֽ�
        System.out.println(raf.getFilePointer());// ��ӡָ��λ��
        raf.write('B');// ֻд��һ���ֽ�
        int i=0x7fffffff;// ����������������int
        // ÿ��ֻ��дһ���ֽ�,���Ҫ��iд��ȥ��Ҫд4��
//        raf.writeInt(i);// �ײ������ԭ��һ��
        raf.write(i>>>24);// ���8λ
        raf.write(i>>>16);// д��
        raf.write(i>>>8);// д��
        raf.write(i);// д��
        System.out.println(raf.getFilePointer());// ��ӡָ��λ��
        String s="��";// �����ַ���
        byte[] gbk=s.getBytes("GBK");// ʹ��GBK�������
        raf.write(gbk);// д���Ѿ��������ֽ���
        System.out.println(raf.length());// �������
        raf.seek(0);// ��ȡ�ļ���Ҫ��ָ���0
        byte[] buf=new byte[(int) raf.length()];// һ���Զ�ȡ,���ļ��е����ݶ������ֽ�������
        raf.read(buf);// ��ȡһ���ֽ�
        System.out.println(Arrays.toString(buf));// ������
        for (byte b:buf){// ѭ������
            System.out.println(Integer.toHexString(b&0xff)+" ");// ��ӡ���16����
        }
    }
}

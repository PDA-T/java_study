package ������.File��;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * �ļ������
 */
public class FileOutDemo1 {
    public static void main(String[] args) throws IOException {
        FileOutputStream out=new FileOutputStream("C:/Users/15811/Desktop/abc.txt");// �����ļ������
        out.write('A');// д��A�ĵڰ�λ
        out.write('B');// д��B�ĵڰ�λ
        int a=10;// ��������,writeֻ��д��λ,дһ��int��Ҫд�Ĵ�ÿ�ΰ�λ
        out.write(a>>>24);//д��int
        out.write(a>>>16);//д��int
        out.write(a>>>8);//д��int
        out.write(a);//д�����8λ
    }
}

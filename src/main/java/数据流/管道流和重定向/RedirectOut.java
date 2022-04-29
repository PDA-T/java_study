package ������.�ܵ������ض���;

import java.io.*;
import java.util.Scanner;

/**
 * �ض����׼��������
 * ��Ŀ�к�����
 */
public class RedirectOut {
    public static void main(String[] args) throws FileNotFoundException {
        // �����ļ������
        FileOutputStream fos = new FileOutputStream("D:/abc.txt");
        // ������ӡ�����
        PrintStream ps = new PrintStream(fos);
        // �ض����׼���,�����Ǵ�ӡ������̨,�ض����ļ�
        System.setOut(ps);
        // ���д�ӡ����,�����ӡ������̨,���Ǵ�ӡ���ļ�
        System.out.println();
        // �رմ�ӡ��
        ps.close();
    }

    /**
     * �ض����׼����
     */
    public void RedirectIn() throws FileNotFoundException {
        // �����ļ�������
        InputStream in = new FileInputStream("D:/abc.txt");
        // �ض����׼���,�����Ǽ�������,�����ض����ļ�
        System.setIn(in);
        // ����ɨ����
        Scanner sc = new Scanner(System.in);
        // ���ü������
        sc.useDelimiter("\n");
        // ���������ɨ�赽������,ѭ����ȡ�ļ�
        while (sc.hasNext()){
            // ��ӡ����
            System.out.println(sc.next());
        }
    }
}
package ������.��ӡ�ƻ���;

import java.io.*;

public class BrAndBwOrPwDemo {
    /**
     * �ַ���ӡ��
     */
    public static void main(String[] args) throws IOException {
        // ���������ַ���
        BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/15811/Desktop/abc.txt")));
        // �������������
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:/Users/15811/Desktop/abc.txt")));
        // ��������ַ���
        PrintWriter pw=new PrintWriter("C:/Users/15811/Desktop/abc.txt");
        String line;// �����ַ���
        while((line=br.readLine())!=null){// �����ȡ���ַ����ǿտ�ʼѭ��,һ�ζ�ȡһ���޷�ʶ����
            System.out.println(line);// ��ӡ���
            bw.write(line);// д���ַ�
            bw.newLine();// ����
            bw.flush();// ˢ�»�����
            pw.print(line);// ���һ���ַ���
            pw.flush();// ˢ�»�����
        }
        br.close();// �رչ����ַ���
        bw.close();// �رչ��������
        pw.close();// �ر�����ַ���
    }
}

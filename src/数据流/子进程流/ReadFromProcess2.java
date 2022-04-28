package ������.�ӽ�����;

import java.io.*;
import java.util.Scanner;

/**
 * ���ӽ��̽��ж�д
 * ���ӽ���д����Ϣ
 * ������
 */
public class ReadFromProcess2 {
    public static void main(String[] args) {
        PrintStream ps = null;
        try {
            // �����ӽ���
            Process p = Runtime.getRuntime().exec("java ReceiveInfo");
            // �õ������
            OutputStream out = p.getOutputStream();
            // ���������װ����ӡ��
            ps = new PrintStream(out);
            // ������Ϣ,�κ���Ϣ�����Է���
            ps.print("�ַ���");
            ps.print(123);
            ps.print(new ReadFromProcess2());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            // �رմ�ӡ��
            if (ps!=null){
                ps.close();
            }
        }
    }
}

/**
 * �������ŵ��ӽ�����ȥִ��
 * ���Խ��ո�����(ReadFromProcess2)����������
 * ���Ұѽ��յ������ݱ��浽�ļ�
 */
class ReceiveInfo{
    public static void main(String[] args) throws FileNotFoundException {
        // �����ļ������
        OutputStream out = new FileOutputStream("D:/abc.txt");
        // ��װ�ɴ�ӡ��(Ҳ�����ȷ�װ�ɻ������ڷ�װ�ɴ�ӡ��)
        PrintStream ps = new PrintStream(out);
        // ����ɨ����,��׼������in�����ﲻ�Ǽ���,�Ǹ�����
        Scanner scanner = new Scanner(System.in);
        // �ûس����ָ�
        scanner.useDelimiter("\n");
        // �ж�ɨ�����Ƿ�������,�������߳�
        while (scanner.hasNext()){
            // д�뵽�ļ�
            ps.print(scanner.next());
        }
        // �ر���
        ps.close();
    }
}

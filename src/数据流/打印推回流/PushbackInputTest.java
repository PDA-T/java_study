package ������.��ӡ�ƻ���;

import java.io.*;

/**
 * �ƻ�������
 * ���Ѿ���ȡ�������ֽڻ��ַ����������,�����ƻص�������
 * �Ӷ������ظ���ȡ�ոն�ȡ�Ĳ���Ҫ�Ķ���֮ǰ������(��Ҫ������)
 */
public class PushbackInputTest {
    public static void main(String[] args) throws IOException {
        // �����ַ�������
        Reader reader = new FileReader("D:/abc.txt");
        // ʹ���ƻ���,��װ�ַ�������,Ҫ���û�������С,��ȻĬ��1
        PushbackReader pr = new PushbackReader(reader,1024);
        // �����ַ�����
        char[] c = new char[5];
        // �����Ѿ���ȡ�����ĸ����ļ�����
        int hasReadCount = 0;
        // �����Ѿ���ȡ���������ַ���
        String sumString = "";
        // ѭ����ȡ
        while ((hasReadCount=pr.read(c))!=-1){
            // ����ѭ����ȡ������,ת�����ַ���
            String curString = new String(c,0,hasReadCount);
            // ��ȡ���ַ���
            sumString = sumString + curString;
            // ����û�в���Ҫ���ַ���(aaa)
            int aaaIndex = sumString.indexOf("aaa");
            // �ж�����в���Ҫ���ַ���
            if (aaaIndex>-1){
                // �ƻ�����(��������),�ƻص����ݲ��ܱȻ�������
                pr.unread(sumString.toCharArray());
                // ���¶�ȡ��Ҫ������,��������黺������
                if (aaaIndex > 5){
                    // ���¶�������,��СΪ����ֵ�Ĵ�С
                    c = new char[aaaIndex];
                }
                // ��ȡ��Ҫ������
                pr.read(c,0,c.length);
                // �����Ҫ������
                System.out.println(new String(c));
                // �Ѿ��õ���Ҫ������,����ѭ��
                break;
                // û�в���Ҫ������
            }else {
                // ֱ�����
                System.out.println(new String(c));
            }
        }
    }
}

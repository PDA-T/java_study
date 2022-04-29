package ������.�ַ�����;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;

/**
 * һ��ʹ�ù������ȡ����
 * �����������
 * ����������
 */
public class ToolStringReader {
    public static void main(String[] args) throws IOException {
        // �����ַ���
        String s = "Google 1";
        // �����ַ���������
        StringReader sr = new StringReader(s);
        // �����������
        StreamTokenizer st = new StreamTokenizer(sr);
        // ��¼�ַ������ֵĸ���
        int numCount = 0;
        // ��¼�ַ������ʵĸ���
        int wordCount = 0;
        // nextToken�������ú�,st.ttype(����ǽ����������һ������)�ͻ��иոմ������ȡ�ı������
        while (st.nextToken()!=StreamTokenizer.TT_EOF){// ��ȡ���ı�ǲ�������ĩβ���
            // ��ȡ�������
            int flagType = st.ttype;
            // ��������ǵ�������
            if (flagType == StreamTokenizer.TT_NUMBER){
                // �ó�����
                System.out.println(st.nval);
                // ���ָ�����1
                numCount++;
                // �������ǵ���
            }else if (flagType == StreamTokenizer.TT_WORD){
                // �ó�����
                System.out.println(st.sval);
                // ���ʸ�����1
                wordCount++;
            }
        }
        System.out.println("�����ַ���������ָ�����:"+numCount);
        System.out.println("�����ַ�����ĵ��ʸ�����:"+wordCount);
    }
}

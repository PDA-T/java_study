package ������.�ӽ�����;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * ���ӽ��̽��ж�д
 * ��ȡ�ӽ�����Ϣ
 */
public class ReadFromProcess {
    public static void main(String[] args) throws IOException {
        // ��Runtime���exec����,����ƽ̨�ϵ�javac����,Process�ʹ����ӽ���
        Process p = Runtime.getRuntime().exec("javac");
        // ���ֽ���ת�����ַ���,��p�����ȡ��Ϣ(������Ϣ)
        InputStreamReader reader = new InputStreamReader(p.getErrorStream(),"GBK");
        // ��װ�ɻ�����
        BufferedReader br = new BufferedReader(reader);
        // �����ַ���
        String buff = null;
        // ����ѭ��,��ȡһ��
        while ((buff = br.readLine())!=null){
            // ������
            System.out.println(buff);
        }
        // �ر���
        br.close();
    }
}

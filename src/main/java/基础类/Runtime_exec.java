package ������;

import java.io.IOException;

/**
 * ��Ŀ��ͨ����Runtime��exec����������������
 */
public class Runtime_exec {
    public static void main(String[] args) {
        // ��������
        java.lang.Runtime rt= java.lang.Runtime.getRuntime();
        Process p=null;// �����ն���
        // �����п��ܲ��ɹ�,��Ҫtry
        try {
            p=rt.exec("notepad");// ��������
            p.waitFor();// �ȴ��򿪵��µĳ���,ִ����֮��,�ż��������Java����
            p.destroy();// �������̽���
        } catch (IOException e) {
            System.out.println("����ʧ��");// �����ʾ
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // ���û��waitFor��������ֱ��ִ��
        System.out.println("����");
    }
}

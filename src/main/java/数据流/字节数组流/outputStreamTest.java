package ������.�ֽ�������;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * �ֽ����������
 */
public class outputStreamTest {
    public static void main(String[] args) throws IOException {
        // ����һ������,һ����ռ��һ���ֽ�
        byte[] b1 = new byte[]{1,2,3,4};
        // �������������
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // ������������ݵ������
        baos.write(b1);
        // ����һ���·�����ֽ�����,�����С�͵�ǰ�����һ��,����Ϊ��ǰ������Ŀ���
        byte[] b2 = baos.toByteArray();
        // ���b2ֵ
        System.out.println(Arrays.toString(b2));
        // ����,��Ϊ��byte����,�޷�����Ϊ�ַ���
        System.out.println(baos.toString());
        // �����ļ������
        FileOutputStream fos = new FileOutputStream("D:/aaa.txt");
        // ������������������������
        baos.writeTo(fos);
    }
}

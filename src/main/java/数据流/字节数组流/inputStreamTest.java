package ������.�ֽ�������;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * �ֽ�����������
 */
public class inputStreamTest {
    public static void main(String[] args) throws IOException {
        // ����һ������,һ����ռ��һ���ֽ�
        byte[] b1 = new byte[]{1,2,3,4};
        // ������ת��Ϊ����������
        ByteArrayInputStream bais = new ByteArrayInputStream(b1);
        // ��ʾ�������е�ʣ����������
        System.out.println(bais.available());
        // ����һ������
        byte[] b2 = new byte[2];
        // �����������ó������ֽڵ����ݷ���b2
        bais.read(b2);
        // ���ֽ�ת��Ϊ�ַ�,���b2��ֵ
        System.out.println(Arrays.toString(b2));
        System.out.println("��ȡһ��֮��,������ʣ�µ�������:"+bais.available());
        // �����������ó������ֽڵ����ݷ���b2
        bais.read(b2);
        // ���ֽ�ת��Ϊ�ַ�,���b2��ֵ
        System.out.println(Arrays.toString(b2));
        System.out.println("��ȡ����֮��,������ʣ�µ�������:"+bais.available());
        // �����������ó������ֽڵ����ݷ���b2
        bais.read(b2);
        // ���ֽ�ת��Ϊ�ַ�,���b2��ֵ
        System.out.println(Arrays.toString(b2));
        // һ��ֻ�����ĸ��ֽڵ�����,ÿ���ó�����֮��,�������Ѿ�û������
        System.out.println("��ȡ����֮��,������ʣ�µ�������:"+bais.available());
    }
}

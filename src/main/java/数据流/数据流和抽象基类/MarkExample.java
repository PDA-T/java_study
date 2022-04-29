package ������.�������ͳ������;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * �������
 * ���ڷ�װ������,�ṩ����Ĺ���
 */
public class MarkExample {
    public static void main(String[] args) throws IOException {
        // ����һ���ֽ�����
        byte[] b = new byte[]{1,2,3,4,5};
        // ������ת����������
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        // ����������װ��Ϊ������,��װ��������Ҫ�ƶ���������С(2���ֽ�)
        BufferedInputStream bis = new BufferedInputStream(bais,2);
        // ��ȡ��һ���ֽ�
        System.out.println(bis.read());
        // �ж�һ�����Ƿ�֧�ֱ��
        System.out.println(bis.markSupported());
        // ָ�����һλ
//        bis.skip(1);
        // �ڵڶ���λ���ϴ�һ�����(��ȡһ����ָ���ڵڶ���λ��)
        bis.mark(1);
        // �����ĵ�,д��mark�����Ч�Բ�����һ���ֽ�,��ȡ�����ֽں���ʧЧ
        System.out.println(bis.read());
        System.out.println(bis.read());
        // ��ȡ������������������Ƿ����Ż�ʧЧ(����)
//        System.out.println(bis.read());
        // ���ر�Ƿ���,��ȡ����д�Ĳ���1���ֽڲ��ᱨ��(�ĵ��Ǵ��)
        bis.reset();
        // ִ���˱�Ƿ���,ָ�����»ص���ǵ�λ��(��ȡ�ڶ���)
        System.out.println(bis.read());
    }
}

package ������.�ֽ�������;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * ��֤������������
 * Ĭ�ϳ�ʼ��32���ֽڴ�С,�������ԼΪ2G,ֻҪ���ݲ�����2G,����������д
 * ÿ��д����֮ǰ���ȼ����С,�����Ҫ����,����ԭ����������С
 */
public class MyByteArrayOutputStream {
    public static void main(String[] args) throws IOException {
        // �������������
        MyByteArrayOutputStream2 out = new MyByteArrayOutputStream2();
        // ���Ĭ�����,�����������buf�ĳ���
        System.out.println(out.getBuf().length);
        // ��������ж�������
        System.out.println(out.size());
        // �ָ�
        System.out.println("----------------------");
        // д��32���ֽڵ�����
        for (int i=0;i<32;i++){
            out.write(1);
        }
        // �������,�����������buf�ĳ���
        System.out.println(out.getBuf().length);
        // ��������ж�������
        System.out.println(out.size());
        // �ָ�
        System.out.println("----------------------");
        // �ٴ�д��һ������
        out.write(1);
        // �������,�����������buf�ĳ���
        System.out.println(out.getBuf().length);
        // ��������ж�������
        System.out.println(out.size());
        // �ָ�
        System.out.println("----------------------");
        // ����98������
        out.write(new byte[98]);
        // �������,�����������buf�ĳ���
        System.out.println(out.getBuf().length);
        // ��������ж�������
        System.out.println(out.size());
    }
}
class MyByteArrayOutputStream2 extends ByteArrayOutputStream {
    // �����buf������Ϊ����������Ϣ
    public byte[] getBuf(){
        // �õ������buf����
        return super.buf;
    }
}

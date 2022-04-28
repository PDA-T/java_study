package ������.�߼�������.������;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * �������ĸ�������
 */
public class BufferTest {
    public static void main(String[] args) {
        // �����ֽڻ�����,����Ϊ9
        ByteBuffer buffer = ByteBuffer.allocate(9);
        // ����������
        System.out.println("����������:"+buffer.capacity());
        // �������Ͻ�
        System.out.println("�������Ͻ�:"+buffer.limit());
        // ������ָ��λ��
        System.out.println("������ָ��λ��:"+buffer.position());
        // �����ַ���
        String str = "123456";
        // ���ַ���ת��Ϊ�ֽ�����
        byte[] strbyte = str.getBytes();
        // ���뻺����
        buffer.put(strbyte);
        // �������Ͻ�
        System.out.println("�������Ͻ�:"+buffer.limit());
        // ������ָ��λ��
        System.out.println("������ָ��λ��:"+buffer.position());
        // �ָ�
        System.out.println("----------------------------------");
        /*
         * ��ת������(��Ϊ��ȡģʽ)
         * ���Ͻ��ƶ���ָ���λ��(����������Ͻ絽ָ��λ��,��ȡʱ��һֱ��ȡ�����������������λ��)
         * ��ָ���ƶ���0(��0��ʼ��ȡ)
         * �ѱ���ƶ���-1(��ձ��)
         */
        buffer.flip();
        // �������Ͻ�
        System.out.println("�������Ͻ�:"+buffer.limit());
        // ������ָ��λ��
        System.out.println("������ָ��λ��:"+buffer.position());
        // �ж��ǲ���ֻ��������
        System.out.println(buffer.isReadOnly());
        // ����һ���ַ�����,��СΪ�Ͻ�(Ԫ�صĸ���)
        char[] dataarr = new char[buffer.limit()];
        // ѭ���жϻ������Ƿ�������
        for (int i=0;buffer.hasRemaining();i++){
            // ��ȡ����
            dataarr[i] = (char) buffer.get();
        }
        // �������
        System.out.println(Arrays.toString(dataarr));





        // �ָ�,��Ϊ�Ѿ���Ϊֻ��������,�Ͻ����⵼�����´���ᱨ��
        System.out.println("=================================");
        // ����ָ��λ��
        buffer.position(5);
        // ������ָ��λ��
        System.out.println("������ָ��λ��:"+buffer.position());
        // ��5��λ���ϴ���
        buffer.mark();
        // �ָ�
        System.out.println("---------------------------------");
        // �ı�ָ��λ��
        buffer.position(8);
        // ������ָ��λ��
        System.out.println("������ָ��λ��:"+buffer.position());
        // ��ָ��λ�ø��ĵ����λ��
        buffer.reset();
        // ������ָ��λ��
        System.out.println("������ָ��λ��:"+buffer.position());
    }
}

package ������.�߼�������.������;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * ��ͼ������
 */
public class BufferTest3 {
    public static void main(String[] args) {
        // ����ԭʼ������
        ByteBuffer buffery = ByteBuffer.allocate(9);
        // ����ԭʼ������,������ͼ������
        ByteBuffer buffers = buffery.duplicate();
        // ��ԭʼ�����������ֵ
        buffery.put("123456".getBytes());
        // ��������
        char[] c = new char[6];
        // ѭ����ȡ����
        for (int i=0;i<6;i++){
            /*
             * ��ͼ��������ԭʼ���������ݹ���,����ָ�벻����
             * ����������ͼ������λ��ָ��Ϊ0,�����Ѿ�������
             * ���÷�ת����������ȡ��
             */
            c[i] = (char)buffers.get();
        }
        // �������
        System.out.println(Arrays.toString(c));

        // ����ԭʼ������
        ByteBuffer buffery2 = ByteBuffer.allocate(9);
        // ����ԭʼ������,������ͼ������(ֻ��),���˲���д����������ͼ������һ��
        ByteBuffer buffers2 = buffery2.asReadOnlyBuffer();
        // �ж��Ƿ���ֻ��������
        System.out.println(buffers2.isReadOnly());

        // ����ԭʼ������
        ByteBuffer buffery3 = ByteBuffer.allocate(8);
        // ����8���ֽ�
        buffery3.put("12345678".getBytes());
        // ��ת������
        buffery3.flip();
        // �ƶ�λ��ָ�뵽4
        buffery3.position(4);
        // ������ͼ������(�ָ�)
        ByteBuffer buffers3 = buffery3.slice();
        // �������������,�ָ��������Ϊԭʼ��������ǰ���Ͻ�ָ��-λ��ָ���ֵ
        System.out.println(buffers3.capacity());
        // ��������
        char[] c2 = new char[9];
        // ѭ����ȡ����
        for (int i=0;buffers3.hasRemaining();i++){
            // ��ȡ����
            c2[i] = (char)buffers3.get();
        }
        // �������,ֻ�����һ��,��Ϊ������ȫ����,ֻ�����Ͻ�ָ��-λ��ָ�������
        System.out.println(Arrays.toString(c2));

        // ������һ����
        new BufferTest3_1();
    }
}

/**
 * �ֽڶ����ƶ�ȡ����
 * ��Ϊ������С����,�����Ӷ���������߿�ʼ��ȡ,С������ұ߿�ʼ��ȡ
 */
class BufferTest3_1{
    BufferTest3_1(){
        // �����ֽڻ�����
        ByteBuffer buffer = ByteBuffer.allocate(9);
        // ���Ĭ�ϵĶ�ȡ˳��
        System.out.println(buffer.order());
        // �Ѷ�ȡ˳���ΪС��
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        // �����ȡ˳��
        System.out.println(buffer.order());
        // �����ǰϵͳCPU�Ķ�ȡ˳��
        System.out.println(ByteOrder.nativeOrder());

        // �������������ֽ�
        buffer.put("12345678".getBytes());
        // ��ת������
        buffer.flip();
        // �ô����ģʽȡ��
        int v1 = buffer.order(ByteOrder.BIG_ENDIAN).getInt();
        // ��С����ģʽȡ��
        int v2 = buffer.order(ByteOrder.LITTLE_ENDIAN).getInt();
        // ������
        System.out.println(v1+","+v2);
    }
}

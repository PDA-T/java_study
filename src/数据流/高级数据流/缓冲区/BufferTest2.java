package ������.�߼�������.������;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Arrays;

/**
 * �������ȴ�С���ж����
 * get�����Ĳ����ù�����(���ױ��쳣,�Ƽ�ʹ��ѭ������)
 */
public class BufferTest2 {
    public static void main(String[] args) {
        // ��������������
        ByteBuffer buffer = ByteBuffer.allocate(9);
        ByteBuffer buffer2 = ByteBuffer.allocate(9);
        // ���������ֽ�
        buffer.put("123".getBytes());
        buffer2.put("456".getBytes());
        /*
         * �ж��������
         * 1.������ָ��λ�ú��Ͻ�֮���Ԫ�ظ���һ��
         * 2.������ָ��λ�ú��Ͻ�֮���Ԫ��ֵһ��
         * ���
         */
        System.out.println(buffer.equals(buffer2));
        // ��ת������,�ı���ָ��λ�ú��Ͻ�λ�����Բ����
        buffer.flip();
        buffer2.flip();
        // �����
        System.out.println(buffer.equals(buffer2));
        /*
         * �жϴ�С,���˷���������,С�˷��ظ�����,0Ϊ���
         * һ��Ԫ�ش󷵻ص�ֵ+1,С��-1(���һ��10��Ԫ�ض����򷵻�10)
         * 1.������ָ��λ�ú��Ͻ�֮��Ԫ�ض��һ����
         * 2.�ӵ����߿�ʼ�Ա�,һ����һ��Ԫ�ش�Сȷ����,�򷵻�ֵҲȷ��
         * ������ߵ�һ��С,��ô����ֵ��СΪ-1
         */
        System.out.println(buffer.compareTo(buffer2));


        // ����һ���ֽ�����
        byte[] arr = new byte[10];
        // ʹ��get����,�ӻ�����������ȡ����(��Ϊ������ֻ������Ԫ��,������Ҫ��10��,����)
        buffer.get(arr);
        // ָ���ͷ��ֽڸ������ᱨ��
        buffer.get(arr,0,3);
        // ������ָ��λ��
        System.out.println("������ָ��λ��:"+buffer.position());
        // �������Ͻ�
        System.out.println("�������Ͻ�:"+buffer.limit());
        // �������(������)
        System.out.println(Arrays.toString(arr));
    }
}

/**
 * �������Ĵ���
 * ��ӵĻ�����,ʹ�õ��Ǳ��ݵ�����
 */
class BufferTest2_1 {
    public static void main(String[] args) {
        // ����һ������Ϊ100��char�����Ļ�����
        CharBuffer buffer = CharBuffer.allocate(100);
        // ����һ������(��һ�ִ�������)
        char[] chararr = new char[100];
        // ͨ�����鳤�ȴ���������(��������������ֱ��Ӱ�컺����)
        CharBuffer buffer2 = CharBuffer.wrap(chararr);
    }
}

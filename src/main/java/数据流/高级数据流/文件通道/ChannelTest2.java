package ������.�߼�������.�ļ�ͨ��;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIO����д�����
 */
public class ChannelTest2 {
    public static void main(String[] args) {
        // ����һ���ַ���
        String str = "Google";
        // �����ļ�ͨ��
        FileChannel fc = null;
        try {
            // �����ļ������
            FileOutputStream fout = new FileOutputStream("D:/abc.txt");
            // ͨ���ļ��������ȡ�ļ�ͨ��(�ļ�ͨ������ͨ�������������ȡ)
            fc = fout.getChannel();
            // ����������
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // �����ֽ�����,���ַ�����ֽ�
            byte[] data = str.getBytes();
            // ѭ����������
            for (int i=0;i<data.length;i++){
                // �����ݷ��뻺����
                buffer.put(data[i]);
            }
            // �ѻ�������д��ģʽ,��Ϊ��ȡģʽ(��ΪҪ�ѻ���������д����ͨ��,����Ҫ��ȡ������)
            buffer.flip();
            // ��ȡ�ļ��ֽ�(��ȡ�ļ�ǰ30�ֽ�)
//            FileChannel fc2 = fc.truncate(30);
            // �����������������
            while (buffer.hasRemaining()){
                // ���ָ��λ��
                System.out.println(fc.position());
                // д���ļ�ͨ��
                fc.write(buffer);
                // ���ָ��λ��
                System.out.println(fc.position());
                // ���ͨ���������ļ���С
                System.out.println(fc.size());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            while (fc!=null){
                try {
                    // �ر�ͨ��
                    fc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

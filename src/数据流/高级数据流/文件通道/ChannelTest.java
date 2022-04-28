package ������.�߼�������.�ļ�ͨ��;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIO��,����ͨ������������ȡд��
 * NIO����IO(BIO)���ĸ��°�,֧��ͬ��,�������̶߳�ȡд���ļ�
 * ���Ǳ���һ��ʹ��IO(BIO)��,NIO����ҪΪ�����̿���ʹ��
 */
public class ChannelTest {
    public static void main(String[] args) {
        // ����NIO��ͨ��
        FileChannel fc = null;
        try {
            // ������������ļ���
            RandomAccessFile raf = new RandomAccessFile("D:/abc.txt","rw");
            // ��ȡNIO��ͨ��ʵ��
            fc = raf.getChannel();
            // ��ȡ������,���뻺������С
            ByteBuffer buffer = ByteBuffer.allocate(48);
            // ��ʾ���ж����ֽڱ�������Buffer��,����-1��ʾ�������ļ�ĩβ
            int hasRead = fc.read(buffer);
            // ѭ���ж��Ƿ�����ļ�ĩβ
            while (hasRead!=-1){
                // �ѻ�������д��ģʽ,��Ϊ��ȡģʽ
                buffer.flip();
                // �������������δ��ȡ�����ݴ���
                while (buffer.hasRemaining()){
                    // �������
                    System.out.println((char) buffer.get());
                }
                // �����������Ѿ�������,��Ҫ�ٴδ�ͨ�����ȡ,��ջ�����
                buffer.clear();
                // �ڴ�ͨ�������ȡ����
                hasRead = fc.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fc!=null){
                try {
                    // �ر���
                    fc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

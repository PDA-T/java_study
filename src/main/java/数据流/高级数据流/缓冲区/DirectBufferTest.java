package ������.�߼�������.������;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * ֱ�ӻ�����
 * �ƹ���������ڴ�,�ڴ����ݺʹ��ļ�����������Ч�ʸ�
 * С�ļ������������������Ч�ʵ�
 */
public class DirectBufferTest {
    public static void main(String[] args) throws IOException {
        /*
         * ����ֱ�ӻ�����(100M)
         * �����Ĵ�СĬ��ΪJVM�ѵ����ֵ,��������ᱨ��(OutOfMemoryError)
         * ���ǲ��ܶѵ�����,����JVM����MaxDirectMemorySize��������
         * ����취,���Ը��Ļ�������С,���߸���JVM������С
         */
        ByteBuffer.allocateDirect(100*1024*1024);
        // ��ӻ�����
        new DirectBufferTest_0().DirectBufferTest_1();
        // ֱ�ӻ�����
        new DirectBufferTest_0().DirectBufferTest_2();
    }
}

/**
 * �Ա�һ�����ļ���һ��С�ļ���ȡ�ٶ�
 */
class DirectBufferTest_0{
    void DirectBufferTest_1() throws IOException {
        // �����ļ���,GTA5��2.6G���ļ�
        File file = new File("D:\\Epic Games\\GTAV\\x64q.rpf");
        // �����ļ�������
        FileInputStream fin = new FileInputStream(file);
        // ��ȡ�ļ�ͨ��
        FileChannel channel = fin.getChannel();
        // ������ӻ�����(������Խ��Խ��)
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // ��ȡ����ʼʱ��
        long start = System.currentTimeMillis();
        // ѭ�����ļ����뻺����,�ж��Ƿ���ļ���ȡ���
        while (channel.read(buffer)!=-1){
            // ��ת������
            buffer.flip();
            // ��ջ�����
            buffer.clear();
        }
        // ��ȡ�������ʱ��
        long end = System.currentTimeMillis();
        // �������ִ��ʱ��
        System.out.println("��ӻ�������Ҫʱ��:" + (end-start));
    }

    /**
     * ֱ�ӻ�����
     * @throws IOException
     */
    void DirectBufferTest_2() throws IOException {
        // �����ļ���,GTA5��2.6G���ļ�
        File file = new File("D:\\Epic Games\\GTAV\\x64q.rpf");
        // �����ļ�������
        FileInputStream fin = new FileInputStream(file);
        // ��ȡ�ļ�ͨ��
        FileChannel channel = fin.getChannel();
        // ����ֱ�ӻ�����(������Խ��Խ��)
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        // ��ȡ����ʼʱ��
        long start = System.currentTimeMillis();
        // ѭ�����ļ����뻺����,�ж��Ƿ���ļ���ȡ���
        while (channel.read(buffer)!=-1){
            // ��ת������
            buffer.flip();
            // ��ջ�����
            buffer.clear();
        }
        // ��ȡ�������ʱ��
        long end = System.currentTimeMillis();
        // �������ִ��ʱ��
        System.out.println("ֱ�ӻ�������Ҫʱ��:" + (end-start));
    }
}

package ������.�߼�������.������;

import jdk.internal.ref.Cleaner;
import sun.nio.ch.DirectBuffer;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * ӳ���ֽڻ�����
 * ��Ŀ���Ƽ�ʹ��
 */
public class MappedByteBufferTest {
    public static void main(String[] args) throws IOException {
        // �����ļ���,GTA5��1.8G���ļ�(map����2G)
        File file = new File("D:\\Epic Games\\GTAV\\x64v.rpf");
        // ��������ļ���ȡ��
        RandomAccessFile raf = new RandomAccessFile(file,"rw");
        // �õ��ļ�ͨ��
        FileChannel channel = raf.getChannel();
        // ��ȡ����ʼʱ��
        long start = System.currentTimeMillis();
        // ӳ���ֽڻ�����,���ļ���0��ʼ���ļ���β�ĳ������ڴ��Ӳ��֮�佨��ӳ���ϵ(READ_ONLYֻ��),����2G
        MappedByteBuffer mb = channel.map(FileChannel.MapMode.READ_ONLY,0,file.length());
        // ѭ���жϻ������Ƿ�������
        while (mb.hasRemaining()){
            // ��ȡ����,����Ҫ��ת������
            mb.get();
        }
        // ��ȡ�������ʱ��
        long end = System.currentTimeMillis();
        // �������ִ��ʱ��
        System.out.println("��Ҫʱ��:" + (end-start));
        // �ر�ͨ��
        channel.close();
        // �ر���
        raf.close();
        /*
         * ���ļ�����,��Ϊ�ļ�ӳ�䲻����Ϊ�ر���������ͨ�����ر�
         * �ļ�ӳ�������޷��޸��ļ���,ȫ��GC���ղſ��������ӳ���ϵ
         */
        file.renameTo(new File("D:\\Epic Games\\GTAV\\x64v.rpf"));

        /*
         * ����޷��Ͽ�ӳ�������
         */
        Cleaner cleaner = ((DirectBuffer) mb).cleaner();
        // �ж��Ƿ�Ϊ��
        if (channel!=null){
            // �Ͽ�����
            cleaner.clean();
        }

        /*
         * �������һ���Դ�����2G���ļ�
         * ����������
         */
        MappedByteBuffer mb2 = null;
        // �����ļ�����
        long fileLen = file.length();
        // �����ļ����ݵ�����ָ��λ��
        long cur = 0;
        // ʹ��λ������,ÿ��ӳ��512M
        long mapSize = 1L << 29;
        // ����ļ�ָ��λ��С���ļ����ȿ�ʼѭ��,ѭ�����֮���ļ�ָ��λ�ü���ÿ��ӳ����ļ���С(512M)
        for (;cur < fileLen;cur += mapSize){
            // ����ļ����ȼ�ȥָ��λ��С��512M,˵��ʣ�µ����ݲ�����512M,ͨ���жϵ�������
            if (fileLen - cur < mapSize){
                // ˵���˴��ó�����������512M,��Ҫʹ���ļ����ȼ�ȥ��ǰָ��λ��
                mb2 = channel.map(FileChannel.MapMode.READ_ONLY,cur,fileLen-cur);
            }else {
                // ˵���˴��ó��������㹻512M,ֱ���ƶ�mapSize(512M)����
                mb2 = channel.map(FileChannel.MapMode.READ_ONLY,cur,mapSize);
            }
            // ѭ���жϻ������Ƿ�������
            while (mb2.hasRemaining()){
                // ��ȡ����
                mb2.get();
            }
        }

        /**
         * д�ļ�
         * �����ļ���
         */
        File file2 = new File("D:\\Epic Games\\GTAV\\x64v.rpf");
        // ��������ļ���ȡ��
        RandomAccessFile raf2 = new RandomAccessFile(file2,"rw");
        // �õ��ļ�ͨ��
        FileChannel channel2 = raf2.getChannel();
        // ӳ���ֽڻ�����,���ļ���0��ʼ���ļ���β�ĳ������ڴ��Ӳ��֮�佨��ӳ���ϵ(READ_WRITE��д),����2G
        MappedByteBuffer mb3 = channel2.map(FileChannel.MapMode.READ_WRITE,0,file.length());
        // ѭ���жϻ������Ƿ�������
        while (mb.hasRemaining()){
            // ��ȡ��һ���ļ�����д��ڶ���������
            mb3.put(mb.get());
        }
    }
}

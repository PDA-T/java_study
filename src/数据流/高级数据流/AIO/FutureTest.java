package ������.�߼�������.AIO;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

/**
 * AIOΪNIO�Ľ��װ�(NIO2.0)
 * �첽������
 */
public class FutureTest {
    public static void main(String[] args) {
        // ����·��
        Path path = Paths.get("D:\\Epic Games\\GTAV\\x64q.rpf");
        // ������������
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        try {
            // ������첽�ļ�ͨ��,READ��ʾδ��
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
            // ��������,0��ʾ���ļ����ʼλ�ö�
            Future<Integer> future = channel.read(readBuffer, 0);
            /*
             * ��Ϊ���첽�Ĳ�����ʽ,���Բ���ֱ�Ӷ�ȡ
             * �ڶ�ȡ֮ǰ,���뱣֤��ͨ����ȡ������,����д�뵽������
             * ѭ���ж�ͨ�������Ƿ�ȫ������д�뵽��������(ѭ��û�з�����)
             */
            while (!future.isDone());
            // ��ת������
            readBuffer.flip();
            // ���������������
            System.out.println("�������������:" + new String(readBuffer.array()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
         * �ڶ�������
         * ʹ���첽,�������߳���ִ�ж�ȡ����
         * ���̲߳���ȴ�
         */
        try {
            // ������첽�ļ�ͨ��,READ��ʾδ��
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
            /*
             * �����Ĳ���read����,����������Ϊ���ĸ������ĸ��Ӷ���
             * �ᱻ���뵽���ĸ������������ڲ��෽���Ĳ�����
             * ���뵽completed������ByteBuffer attachment��
             */
            channel.read(readBuffer, 0, readBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                /**
                 * ��ͨ���������д���������,д�����ʱ����
                 * @param result
                 * @param attachment
                 */
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    // �����ȡ�˶����ֽ�
                    System.out.println(result);
                    // ��ת������
                    attachment.flip();
                    // �������������
                    System.out.println(new String(attachment.array()));
                }

                /**
                 * ��ͨ���������д���������,д��ʧ��ʱ����
                 * @param exc
                 * @param attachment
                 */
                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // �ȴ����̶߳�ȡ����ڽ������߳�
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * д����
     */
    FutureTest(){
        // ����·��
        Path path = Paths.get("D:/abc.txt");
        // �ж��ļ��Ƿ����
        if (!Files.exists(path)){
            try {
                // �����ļ�
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // ����д������
        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
        // ��������
        writeBuffer.put("����".getBytes());
        // ��ת������
        writeBuffer.flip();
        try {
            // ��ȡ�ļ����첽ͨ��,WRITE��ʾд
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(path,StandardOpenOption.WRITE);
            // д��ͨ��,�Ͷ�����һ��
            Future<Integer> fu = channel.write(writeBuffer, 0);
            // �ȴ�д�����֮����ִ��,�Ͷ�һ��
            while (!fu.isDone());
            System.out.println("д�����");
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
         * �ڶ�������
         * ʹ���첽,�������߳���ִ��д�뷽��
         * ���̲߳���ȴ�
         */
        try {
            // ��ȡ�ļ����첽ͨ��,WRITE��ʾд
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(path,StandardOpenOption.WRITE);
            // д��ͨ��,�Ͷ�����һ��
            channel.write(writeBuffer, 0, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                /**
                 * д����ɵ���
                 * @param result
                 * @param attachment
                 */
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    // ���д���˶����ֽ�
                    System.out.println(result);
                }

                /**
                 * д��ʧ�ܵ���
                 * @param exc
                 * @param attachment
                 */
                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // �ȴ����̶߳�ȡ����ڽ������߳�
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

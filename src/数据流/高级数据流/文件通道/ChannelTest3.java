package ������.�߼�������.�ļ�ͨ��;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * ͨ��һ��������һ������ͨ�����뵽��һ������ͨ��
 */
public class ChannelTest3 {
    public static void main(String[] args) throws IOException {
        // ��������ļ���
        RandomAccessFile fileFrom = new RandomAccessFile("D:/abc.txt","rw");
        // �õ��ļ�ͨ��
        FileChannel channelFrom = fileFrom.getChannel();
        // ��������ļ���(Ҫ���Ƶĵط�)
        RandomAccessFile fileTo = new RandomAccessFile("E:/abc.txt","rw");
        // �õ��ļ�ͨ��
        FileChannel channelTo = fileTo.getChannel();
        // ����,��һ������Ϊԭ���ݵ��ļ�ͨ��,�ڶ�Ϊ��ʼλ��,������Ϊ��ഫ������ֽ�
        channelTo.transferFrom(channelFrom,0,channelFrom.size());
        // ������ķ���Ч��һ��,ֻ�ǲ���λ�ú�ͨ����һ��
        channelFrom.transferTo(0,channelFrom.size(),channelTo);
    }
}

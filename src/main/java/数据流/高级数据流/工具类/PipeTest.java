package ������.�߼�������.������;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * 2���߳�֮�䵥����������
 */
public class PipeTest {
    public static void main(String[] args) throws IOException {
        // ��ȡpipeʵ��
        Pipe pipe = Pipe.open();
        // �����߳�
        new Thread(new ThreadA(pipe)).start();
        new Thread(new ThreadB(pipe)).start();
    }
}

/**
 * ����һ���߳���
 */
class ThreadA implements Runnable{
    // ��������
    private Pipe pipe;
    // ����������
    public ThreadA(Pipe pipe){
        this.pipe = pipe;
    }

    /**
     * д��
     */
    @Override
    public void run() {
        // ��ȡͨ��
        Pipe.SinkChannel sink = pipe.sink();
        // �����ַ���
        String data = "aaaaaaaaaaaaaaaaaaaaa";
        // ����������
        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
        // �����ݷ��뻺����
        writeBuffer.put(data.getBytes());
        // ��ת������
        writeBuffer.flip();
        try {
            // ѭ���жϻ������Ƿ�������
            while (writeBuffer.hasRemaining()){
                // ������д��ͨ��
                sink.write(writeBuffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class ThreadB implements Runnable{
    // ��������
    private Pipe pipe;
    // ����������
    public ThreadB(Pipe pipe){
        this.pipe = pipe;
    }

    /**
     * ��ȡ
     */
    @Override
    public void run() {
        // ��ȡͨ��
        Pipe.SourceChannel source = pipe.source();
        // ����������
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        try {
            // ��ȡ�߳�A�Ļ�����
            source.read(readBuffer);
            // ��ת������
            readBuffer.flip();
            // �������
            System.out.println(new String(readBuffer.array()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

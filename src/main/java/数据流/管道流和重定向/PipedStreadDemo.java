package ������.�ܵ������ض���;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * �ܵ����������
 * ���������߳�֮����໥ͨ��
 */
public class PipedStreadDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        // ʹ���̳߳ش������߳�(�����߳�,����߳�,ʱ��,ʱ������,5����������)
        ThreadPoolExecutor executor = new ThreadPoolExecutor(6,12,
                200,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(5));
        // �ȴ����ܵ������
        PipedOutputStream pout = new PipedOutputStream();
        // �����ܵ�������,��Ҫ���������(����ͨ��)
        PipedInputStream pin = new PipedInputStream(pout);
        // ʵ�����߳���
        Sender sender = new Sender(pout);
        // ʵ�����߳���
        Receiver receiver = new Receiver(pin);
        // �����̳߳���ȥ�����߳�ִ��
        executor.execute(sender);
        // �����̳߳���ȥ�����߳�ִ��
        executor.execute(receiver);
        // �ر��̳߳�(��ǿ�ƹر�)
        executor.shutdown();
        // ���ó�ʱδ�رյ�ʱ��(����һСʱδ�ر�ǿ�ƹر�)
        executor.awaitTermination(1,TimeUnit.HOURS);
    }
}

/**
 * �������߳���
 */
class Sender implements Runnable{
    // �����ܵ������
    private PipedOutputStream pout;
    // ������ʼ�����췽��
    public Sender(PipedOutputStream pout){
        this.pout = pout;
    }
    @Override
    public void run() {
        // ����һ���ַ���
        String s = "��������";
        // ���ַ���ת��������
        byte[] b = s.getBytes();
        try {
            // д�뻺������,������Ϣ
            pout.write(b);
            System.out.println("���ͳ�ȥ����Ϣ��:"+s);
            // �ر�֮��,����������ʹ��read�������ܷ���-1
            pout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/**
 * ������������
 */
class Receiver implements Runnable{
    // �����ܵ�������
    private PipedInputStream pin;
    // ������ʼ�����췽��
    public Receiver(PipedInputStream pin){
        this.pin = pin;
    }
    @Override
    public void run() {
        // �����ֽ����������
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // �����ֽ�����
        byte[] b = new byte[1024];
        // ��¼�Ѿ���ȡ����������ֽڸ���
        int hasRead = 0;
        try {
            while ((hasRead = pin.read(b))!=-1){
                // ������д�����������
                baos.write(b,0,hasRead);
            }
            // ��ȡ���������������
            byte[] rs = baos.toByteArray();
            // ���ֽ�����ת�����ַ���
            String s = new String(rs,0,rs.length);
            // ��ӡ���������������
            System.out.println("���յ�����Ϣ��:"+s);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

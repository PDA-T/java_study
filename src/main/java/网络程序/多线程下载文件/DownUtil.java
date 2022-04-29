package �������.���߳������ļ�;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * ���ع�����
 */
public class DownUtil {
    // ����Ŀ���ļ���URL��ַ
    private String urlPath;
    // �����ļ���λ��
    private String targetFile;
    // ʹ�ü����߳�����
    private int threadNum;
    // ����һ������,������ص��߳���
    private DownThread[] threads;
    // ���������ص��߳��ඨ��Ϊ��������ڲ���
    private class DownThread extends Thread{
        // ÿ���߳����ص��ļ���Ŀ�ʼλ��
        private int startPos;
        // ÿ���̸߳������ص��ļ���Ĵ�С
        private int currentPartSize;
        // ÿ���߳����ص��ļ���
        private RandomAccessFile currentPart;
        // ��¼ÿ���߳��Ѿ������������ļ��ֽ���
        private int length;
        // ����������
        public DownThread(int startPos,int currentPartSize,RandomAccessFile currentPart){
            // ��ʼ������
            this.startPos = startPos;
            this.currentPartSize = currentPartSize;
            this.currentPart = currentPart;
        }

        @Override
        public void run() {
            try {
                // ����URL
                URL url = new URL(urlPath);
                // �õ�����
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                // ���ó�ʱʱ��,���ӳ�ʱ�׳��쳣
                conn.setConnectTimeout(5000);
                // ��������Ŀ������󷽷�
                conn.setRequestMethod("GET");
                // ��������ͷ����Ϣ,Accept��ʾ�ͻ��˿��Դ�����Щ�ļ�,*/*��ʾ����ͻ��˴��������ļ�(��ֵ��)
                conn.setRequestProperty("Accept","*/*");
                // ���ý��ܵ���������
                conn.setRequestProperty("Accept-Language","zh-CN");
                // �����ַ���
                conn.setRequestProperty("Charset","UTF-8");
                // ��������ģʽ,Keep-Alive��ʾ��������Ӧ��tcp���Ӳ������ж�
                conn.setRequestProperty("Connection","Keep-Alive");
                // ����Ŀ��(����ʡ��)
                conn.connect();
                // ��ȡĿ���ļ�������
                InputStream in = conn.getInputStream();
                // ����������ָ��,�������̸߳������ص�λ��
                in.skip(this.startPos);
                // ����������
                byte[] buffer = new byte[1024];
                // �������
                int hasRead = 0;
                // ѭ���ж����صĳ���ҪС�ڵ�ǰ�̸߳������صĳ���,�����ļ��Ѿ�����
                while (length<currentPartSize && (hasRead=in.read(buffer))!=-1){
                    // �Ѷ�ȡ������д�뵱ǰ�ļ���
                    currentPart.write(buffer,0,hasRead);
                    // ��¼����
                    length += hasRead;
                }
                // �ر���
                currentPart.close();
                in.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    /*
     * ����Զ�̵��ļ�,�����ļ�,���̷ֱ߳�ʵ������
     * Ŀ���ļ��Ĵ�С
     */
    private int fileSize;
    // �������췽��
    public DownUtil(String urlPath,String targetFile,int threadNum){
        // ��ʼ������
        this.urlPath = urlPath;
        this.targetFile = targetFile;
        this.threadNum = threadNum;
        // ʵ�����߳�������,����Ϊ�̸߳���
        this.threads = new DownThread[threadNum];
    }
    // ʵ�������ļ�����
    public void download() throws IOException {
        // ����URL
        URL url = new URL(urlPath);
        // �õ�����
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // ���ó�ʱʱ��,���ӳ�ʱ�׳��쳣
        conn.setConnectTimeout(5000);
        // ��������Ŀ������󷽷�
        conn.setRequestMethod("GET");
        // ��������ͷ����Ϣ,Accept��ʾ�ͻ��˿��Դ�����Щ�ļ�,*/*��ʾ����ͻ��˴��������ļ�(��ֵ��)
        conn.setRequestProperty("Accept","*/*");
        // ���ý��ܵ���������
        conn.setRequestProperty("Accept-Language","zh-CN");
        // �����ַ���
        conn.setRequestProperty("Charset","UTF-8");
        // ��������ģʽ,Keep-Alive��ʾ��������Ӧ��tcp���Ӳ������ж�
        conn.setRequestProperty("Connection","Keep-Alive");
        // ����Ŀ��(����ʡ��)
        conn.connect();
        // ��ȡĿ���ļ���С
        fileSize = conn.getContentLength();
        // �ر�����
        conn.disconnect();

        // ������������ļ���
        RandomAccessFile file = new RandomAccessFile(targetFile,"rw");
        // �����ļ���С(�ڱ��ش���һ����Ŀ���ļ�ͬ��С���ļ�)
        file.setLength(fileSize);
        // �ر���Դ
        file.close();

        // ÿ���̸߳������ص��ļ���Ĵ�С
        int currentPartSize = fileSize / threadNum + 1;
        // �����ļ���С�����ص��߳�����,��Ŀ���ļ������п�
        for (int i=0;i<threadNum;i++){
            // ����ÿ���߳����ص��ļ���Ŀ�ʼλ��
            int startPos = i*currentPartSize;
            // ��ÿ���߳�ʹ��һ��RandomAccessFile�Ķ�������������
            RandomAccessFile currentPart = new RandomAccessFile(targetFile,"rw");
            // �ƶ��ļ�ָ�뵽����λ�õĵط�(ÿ���̸߳������ص��ļ���)
            currentPart.seek(startPos);
            // �����߳�,��������
            threads[i] = new DownThread(startPos,currentPartSize,currentPart);
            // �����߳�,��ʼ����
            threads[i].start();
        }
    }

    /**
     * ��ȡ������ɵİٷֱ�
     * @return
     */
    public double getCompleteRate(){
        // ���ص��ܳ���
        int sumSize = 0;
        for (int i=0;i<threadNum;i++){
            sumSize += threads[i].length;
        }
        // �����Ѿ������������ļ��ٷֱ�
        return sumSize*1.0/fileSize;
    }
}

package �������.BIO������;

import java.io.BufferedReader;

/**
 * �ͻ����߳���
 * �����ȡ����ʾ�ӷ�������������Ϣ
 */
public class ClientThread implements Runnable{
    // ����������
    private BufferedReader in = null;
    // ������
    public ClientThread(BufferedReader in) {
        // ��ʼ������
        this.in = in;
    }
    @Override
    public void run() {
        try {
            // ��������������Ϣ
            String content = null;
            // ���ϵĽ��շ���������Ϣ
            while (true) {
                // �жϷ�������������Ϣ�Ƿ�Ϊ��
                if (!((content = in.readLine())!=null)){
                    // ���
                    System.out.println(content);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                // �ر���Դ
                if (in!=null){
                    in.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

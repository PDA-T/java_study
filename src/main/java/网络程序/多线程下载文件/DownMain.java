package �������.���߳������ļ�;

import java.io.IOException;

public class DownMain {
    public static void main(String[] args) throws IOException {
        // ����������
        DownUtil downUtil = new DownUtil("https://wallpaperaccess.com/full/508751.jpg","D:/aaa.jpg",5);
        // ��ʼ����
        downUtil.download();
        // ��ȡ���صĽ���
        new Thread(new Runnable() {
            @Override
            public void run() {
                // �������С��1
                while (downUtil.getCompleteRate()<=1){
                    // �������
                    System.out.println("������:"+downUtil.getCompleteRate()*100);
                    try {
                        // ÿ0.1���ȡһ�½���
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}

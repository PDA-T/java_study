package �߳�.�̸߳߼�.JUC�߳̽�����;

import java.util.concurrent.Exchanger;

/**
 * �߳̽�����
 * һ���߳������һ���������������һ���߳̽�������
 * ���һ�����ó����ݵ��̻߳�һֱ�ȴ��ڶ����߳�
 * ֱ���ڶ����߳��������ݵ���ʱ���ܱ˴˽�����Ӧ����
 */
public class ExchangerTest {
    private static String msg1 = null;
    private static String msg2 = null;

    /**
     * ������������,���Ƿֱ���������߳��ﱻ����
     * @param exchanger
     */
    public void a(Exchanger<String> exchanger){
        // ����ǰ,msg1��ֵ
        msg1 = "1111111";
        // ���ֵ
        System.out.println("msg1:"+msg1);
        try {
            /*
             * �ȴ���һ���̵߳���˽����㣨���ǵ�ǰ�̱߳��жϣ���
             * Ȼ�󽫸����Ķ����͸����̣߳������ո��̵߳Ķ���
             */
            msg1 = exchanger.exchange(msg1);
            System.out.println("����a�������߳̽������");
            // �����߳�
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void b(Exchanger<String> exchanger){
        // ����ǰ,msg2��ֵ
        msg2 = "22222222";
        // ���ֵ
        System.out.println("msg2:"+msg2);
        try {
            /*
             * �ȴ���һ���̵߳���˽����㣨���ǵ�ǰ�̱߳��жϣ���
             * Ȼ�󽫸����Ķ����͸����̣߳������ո��̵߳Ķ���
             */
            msg2 = exchanger.exchange(msg2);
            System.out.println("����b�������߳̽������");
            // �����߳�
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // ʵ����������
        final Exchanger<String> exchanger = new Exchanger<String>();
        // ʵ���������
        final ExchangerTest exchangerTest = new ExchangerTest();
        // ���������߳�
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                exchangerTest.a(exchanger);
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                exchangerTest.b(exchanger);
            }
        });
        t1.start();
        t2.start();
        try {
            // ��֤t1ִ����ɺ���ִ������Ĵ���
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("������ɺ�msg1:"+msg1);
        System.out.println("������ɺ�msg2:"+msg2);
    }
}

package �߳�.�̸߳߼�.����ģʽ;

/**
 * ���µ����ֵ���ģʽ
 */
public class newSingleTon {
    public static void main(String[] args) {
        // ����20���߳�
        for (int i=0;i<20;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Singleton5.getInstance());
                }
            }).start();
        }
    }
}

/**
 * ��̬�ڲ���ĵ���ģʽ
 * ����˶���ģʽ������ģʽ���ŵ�
 * �����޷��������
 */
class Singleton4{
    // ����˽�й��췽��,�����ⲿ�ഴ��ʵ������
    private Singleton4(){}
    // ������̬˽�л��ڲ���
    private static class SingleTonHoler{
        // ���ڲ��ഴ����̬˽�л��ⲿ������
        private static Singleton4 singleton = new Singleton4();
    }
    // �������ط���,�����Ѿ������õ�ʵ������
    public static Singleton4 getInstance(){
        // ���ش����õ�ʵ������
        return SingleTonHoler.singleton;
    }
}

/**
 * ö�ٷ�ʽʵ�ֵ���ģʽ
 * ��,�̰߳�ȫ���ǿɶ��Բ���
 */
enum Singleton5{
    SINGLETON;
    // ����ʡ�Դ˷���ֱ��Singleton5.SINGLETON��ȡ
    public static Singleton5 getInstance(){
        return Singleton5.SINGLETON;
    }
}

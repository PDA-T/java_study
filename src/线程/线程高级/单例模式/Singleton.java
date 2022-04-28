package �߳�.�̸߳߼�.����ģʽ;

/**
 * ���ֵ���ģʽ���̰߳�ȫ
 * ����ģʽ:������ֻ�ܴ���һ��ʵ���Ķ���
 */
public class Singleton {
    public static void main(String[] args) {
        // ����20���߳�
        for (int i=0;i<20;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Singleton2.getInstance());
                }
            }).start();
        }
    }
}

/**
 * ����ģʽ
 * ���̰߳�ȫ����
 * ���ǳ��������ͻᴴ��ʵ������,ռ���ڴ�,�����ٶ���
 */
class Singleton1{
    // ����˽��ʵ������
    private static Singleton1 singleton1 = new Singleton1();
    // ����˽�й��췽��,�����ⲿ�ഴ��ʵ������
    private Singleton1(){}
    // �������ط���,�����Ѿ������õ�ʵ������
    public static Singleton1 getInstance(){
        return singleton1;
    }
}

/**
 * ����ģʽ
 * ���̰߳�ȫ����
 */
class Singleton2{
    // ������ʵ������
    private static Singleton2 singleton2 = null;
    // ����˽�й��췽��,�����ⲿ�ഴ��ʵ������
    private Singleton2(){}
    // �������ط���,�����Ѿ�������ʵ������
    public static Singleton2 getInstance(){
        // �ж�ʵ�������Ƿ��Ѿ�������
        if (singleton2 == null){
            // ����ʵ������
            singleton2 = new Singleton2();
        }
        // ���ش�����ʵ������
        return singleton2;
    }
}

/**
 * �������ģʽ���̰߳�ȫ����
 */
class Singleton3{
    // ������ʵ������
    private volatile static Singleton3 singleton3 = null;
    // ����˽�й��췽��,�����ⲿ�ഴ��ʵ������
    private Singleton3(){}
    // �������ط���,�����Ѿ�������ʵ������
    public static Singleton3 getInstance(){
        // �ж�ʵ�������Ƿ��Ѿ�������
        if (singleton3 == null){
            // ʹ��ͬ������鱣��,��Ϊ����Ϣ��,�ڶ��㱣��
            synchronized (Singleton3.class){
                // �ٴ��ж��Ƿ�Ϊ��,�����㱣��,���volatile�ؼ���
                if (singleton3 == null){
                    // ����ʵ������
                    singleton3 = new Singleton3();
                }
            }
        }
        // ���ش�����ʵ������
        return singleton3;
    }
}
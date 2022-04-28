package �߳�.�̸߳߼�.����;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Э������֮�䷢������
 * ʹ�ÿ������ñ�������
 * ����ڵ���ĳ������ʱ����Ҫ������,���ֵ��ñ���Ϊ���ŵ���
 * ��������ͬ������,��ʹ��ͬ�������
 */
public class CooperatingDeadlock {
}

/**
 * ���⳵��
 */
class Taxi{
    // ����λ�ú��û�Ŀ��λ��
    private Point location,destination;
    // ������
    private Dispatcher dispatcher;

    /**
     * ʹ�ù�������ʼ���ͻ�λ��
     * @param dispatcher
     */
    public Taxi(Dispatcher dispatcher){
        this.dispatcher=dispatcher;
    }

    /**
     * ��ȡλ�÷���
     * ͬ������,��ֹ�����̰߳�ȫ����
     * @return
     */
    public synchronized Point getLocation(){
        return location;
    }

    /**
     * ���ó���λ��
     * λ����Ϣ��Ϊ�������,����ʹ��ͬ��
     * @param location
     */
    public void setLocation(Point location){
        /*
         * ���Ƽ�ʹ��
         */
//        this.location=location;
//        // �ж��û���λ���Ƿ�ͳ���λ�����(������дequals�ж�)
//        if (location.equals((destination))){
//            // ����������,ͨ���������ѳ����뵽����ǲ�ļ�������ȥ
//            dispatcher.notifyAvailable(this);
//        }
        // ����һ�����
        boolean flag;
        // thisָ���ʵ������(��ͬ������ʹ�õ���һ��)
        synchronized (this){
            this.location = location;
            // �ж��û���λ���Ƿ�ͳ���λ�����(������дequals�ж�)
            flag = location.equals(destination);
        }
        // ���������Ѿ�������ͬ�������(�Ѿ��ͷ���),֮���ڵ�������ͬ�������ķ����Ͳ�������(���������Ľ�����)

        // �ж��û���λ���Ƿ�ͳ���λ�����
        if (flag){
            // ����������,ͨ���������ѳ����뵽����ǲ�ļ�������ȥ
            dispatcher.notifyAvailable(this);
        }
    }
}
class Dispatcher {
    // ȫ����������
    private final Set<Taxi> taxis;
    // ����ǲ��������
    private final Set<Taxi> availableTaxis;

    /**
     * ͨ����������ʼ����������
     */
    public Dispatcher() {
        taxis = new HashSet<Taxi>();
        availableTaxis = new HashSet<Taxi>();
    }

    /**
     * ������ǲ�ĳ������뼯��
     * ʹ�ù��������������Ϊͬ��
     *
     * @param taxi
     */
    public synchronized void notifyAvailable(Taxi taxi) {
        // ��ӽ�����ǲ����
        availableTaxis.add(taxi);
    }

    /**
     * �ڵ�ͼ�ϻ�������ǲ����
     * ���ڵ�ͼλ�õĲ�������ʹ��ͬ��
     *
     * @return
     */
    public Image getImage() {
//        Image image = new Image();
//        // �ѿɵ��ȵĳ���ѭ���������ڵ�ͼ��
//        for (Taxi t : availableTaxis){
//            // ���ðѳ������ڵ�ͼ�ϵķ���
//            image.drawMarker(t.getLocation());
//        }
//        return image;
//    }
        // ����һ����������
        Set<Taxi> copy;
        // thisָ���ʵ������(��ͬ������ʹ�õ���һ��)
        synchronized (this) {
            // ���Ƴ�һ������(����)
            copy = new HashSet<Taxi>(availableTaxis);
        }
        // ͬ������鵽�����Ѿ�����(���Ѿ����ͷ�),�����ڵ�������ͬ�������ķ���Ҳ����ν

        Image image = new Image();
        // �ѿɵ��ȵĳ���ѭ���������ڵ�ͼ��
        for (Taxi t : copy) {
            // ���ðѳ������ڵ�ͼ�ϵķ���
            image.drawMarker(t.getLocation());
        }
        return image;
    }

    /**
     * ��ͼ��
     */
    class Image {
        /**
         * �����ڵ�ͼ��ĳһ��λ�û�һ����
         *
         * @param p
         */
        public void drawMarker(Point p) {
        }
    }
}
package �߳�.�̸߳߼�.��д��;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ������
 * �Ȼ�ȡд��,�ڻ�ȡ����,���ͷ�д���ſɳ�Ϊ������
 */
public class CachedData {
    public static void main(String[] args) {
        cachedData2 cd = new cachedData2();
        // ����10���߳�
        for (int i=1;i<=10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // ����������
                    cd.putCachedData(Thread.currentThread().getName()+"����������");
                }
            },"t"+i).start();
        }
    }
}
class cachedData2{
    // �����̹߳������
    private String data = "ԭ��ֵ";
    // �����Ǳ���,��Ҫ�ڴ�ɼ�
    private volatile boolean isUpadte;
    // �õ���д��
    private ReadWriteLock rwl = new ReentrantReadWriteLock();
    // �������ݵ����淽��
    public void putCachedData(String data){
        // ��ȡд��ǰ,�Ȼ�ȡ����(�жϻ��������Ƿ����µ�)
        rwl.readLock().lock();
        // �Ա�ǽ����ж�,����ǹ��ڵĻ���
        if (!isUpadte){
            // �ͷŶ���(�����������Ȼ�ȡ����)
            rwl.readLock().unlock();
            // ��ȡд��
            rwl.writeLock().lock();
            try {
                // ����������ݲ������µ�
                if (!isUpadte){
                    // ���»���
                    this.data = data;
                    // ���±��
                    isUpadte = true;
                }
                // ��ȡ����(��ʱ��������)
                rwl.readLock().lock();
            }finally {
                // �ͷ�д��(������)
                rwl.writeLock().unlock();
            }
        }
        try {
            // ʹ�ö�����ȡ����
            System.out.println("���µĻ���:"+this.data);
        }finally {
            // �ͷŶ���
            rwl.readLock().unlock();
        }
    }
}

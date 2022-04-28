package �߳�.�̸߳߼�.���̼߳���;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ���ϵ��̰߳�ȫ
 * �̰߳�ȫ�ļ����滻�̲߳���ȫ�ļ���
 */
public class TestCopyOnWriteArrayList {
    public static void main(String[] args) {
        CollectionsThread ct = new CollectionsThread();
        new Thread(ct,"�߳�1").start();
        new Thread(ct,"�߳�2").start();
        new Thread(ct,"�߳�3").start();
        new Thread(ct,"�߳�4").start();
    }
}
class CollectionsThread implements Runnable{
    // ��ͳ���̰߳�ȫ�����������
//    private static List<String> list = Collections.synchronizedList(new ArrayList<String>());
    // ʹ��JUC�ṩ���̰߳�ȫ����
    private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
    static {
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
    }

    @Override
    public void run() {
        // ����������
        Iterator<String> it = list.iterator();
        // �жϴ������Ƿ���ֵ
        while (it.hasNext()){
            // ��ȡ���ϵ�ֵ
            System.out.println(it.next());
            // һ�߶�һ��дΪ���ϲ���,��ͳ���̰߳�ȫ���ϻ��׳��쳣
            list.add("111");
        }
    }
}

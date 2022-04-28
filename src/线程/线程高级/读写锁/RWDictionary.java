package �߳�.�̸߳߼�.��д��;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ���϶�д��
 * ���ϴ��ʱ�����ʹ�ö�д��
 */
public class RWDictionary {
}
class DollectionRWLTest{
    // �̹߳���ļ���,TreeMap�ᰴ�չ��ɽ�������1-9,a-z
    private Map<String,Object>m = new TreeMap<>();
    // ��ȡ��д��
    private ReadWriteLock rw = new ReentrantReadWriteLock();
    // ��ȡд��
    private Lock w = rw.writeLock();
    // ��ȡ����
    private Lock r = rw.readLock();
    // ͨ�����ϵ�Key��ȡ���ϵ�ֵ
    public Object get(String key){
        // ��ȡ����
        r.lock();
        try {
            // ���ػ�ȡ��ֵ
            return m.get(key);
        }finally {
            // �ͷŶ���
            r.unlock();
        }
    }
    // ��ȡ���������е�key
    public String[] allkeys(){
        // ��ȡ����
        r.lock();
        try {
            // ����Set����
            Set<String> rsSet = m.keySet();
            // ���ػ�ȡ��ֵ,�̶�д��,��Set���ϱ�Ϊ����
            return rsSet.toArray(new String[rsSet.size()]);
        }finally {
            // �ͷŶ���
            r.unlock();
        }
    }
    // ��ֵ���뼯��
    public Object put(String key,Object val){
        // ��ȡд��
        w.lock();
        try {
            // д��ֵ
            return m.put(key,val);
        }finally {
            // �ͷ�д��
            w.unlock();
        }
    }
}
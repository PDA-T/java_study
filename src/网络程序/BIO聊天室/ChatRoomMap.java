package �������.BIO������;

import java.util.*;

/**
 * ������(���ݽṹ)
 * ���������û���,�׽��ֺ��������ӳ���ϵ
 * @param <k> : ����ͻ��˵��û���
 * @param <v> : ��һ������� PrintStream
 */
public class ChatRoomMap<k,v> {
    // ����һ���̰߳�ȫ�ļ���
    public Map<k,v> map = Collections.synchronizedMap(new HashMap<>());
    /**
     * ����ֵ��ɾ��ָ����Ŀ
     */
    public synchronized void removeByValue(Object value){
        // ��������
        for (Object key:map.keySet()){
            // ���������ֵ�ʹ����ֵ���
            if (map.get(key) == value){
                // ɾ������
                map.remove(key);
                // ����ѭ��
                break;
            }
        }
    }
    /**
     * ��ȡ���е�ֵ��ϳɵ�set����
     */
    public synchronized Set<v> getValueSet(){
        // ʵ��������
        Set<v> res = new HashSet<>();
        // ��������
        for (Object key:map.keySet()){
            // ��map�е�ֵ��ӵ�res������
            res.add(map.get(key));
        }
        // ����
        return res;
    }
    /**
     * ����ֵ���ҵ�key
     */
    public synchronized k getKeyByValue(v val){
        // ��������
        for (k key:map.keySet()){
            // ���������ֵ�ʹ����ֵ���,����equals�ʹ����ֵ���
            if (map.get(key) == val || map.get(key).equals(val)){
                // ����key
                return key;
            }
        }
        // û�ж�Ӧ�ļ�
        return null;
    }
    /**
     * ������ݵ�ӳ�伯��,ֵ�����ظ�
     */
    public synchronized v put(k key,v value){
        // �������е�ֵ
        for (v val : getValueSet()){
            // �жϱ�����ֵ�ʹ����ֵ�Ƿ����,���Һʹ����ֵ��hashCodeҲҪ���
            if (val.equals(value) && val.hashCode() == value.hashCode()){
                // �����쳣
                throw new RuntimeException("Mapʵ�������ظ���ֵ");
            }
        }
        // û���ظ�,���봫���ֵ
        return map.put(key,value);
    }
}

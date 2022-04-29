package ���Ͽ��;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Key�����ظ�,value���ظ�
 */
public class Map_class {
    public static void main(String[] args) {
        // ����Map����
        Map hashmap = new HashMap();
        // ��Map�����Ԫ��
        hashmap.put("key1","val1");
        hashmap.put("key2","val2");
        hashmap.put("key3","val3");
        hashmap.put("key4","val4");
        hashmap.put("key5","val5");
        // ͨ��keyֵɾ��Ԫ��
        hashmap.remove("key5");
        // ͨ��keyֵ��ȡvalue
        hashmap.get("key1");
        // ͨ����ӷ����޸�value
        hashmap.put("key1","val11");
        // ͨ��values�����õ�Map�е�value����
        Collection values = hashmap.values();
        // ��������
        for (Object obj:values){
            System.out.println(obj);
        }
        // ͨ��keySet�����õ�Map��key����,Ȼ��ͨ��get(key)�õ�value
        Set keySet = hashmap.keySet();
        // ��������
        for (Object obj:keySet){
            System.out.println(obj);
            // ͨ��key�õ�value
            System.out.println(hashmap.get(obj));
        }
        // ͨ��entrySet�����õ�Map��Entry����,Ȼ�����
        Set<Map.Entry<String,String>> entrySet = hashmap.entrySet();
        for (Map.Entry<String,String> entry:entrySet){
            // ��ȡkeyֵ
            System.out.println(entry.getKey());
            // ��ȡvalueֵ
            System.out.println(entry.getValue());
        }
    }
}

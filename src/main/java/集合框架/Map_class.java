package 集合框架;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Key不可重复,value可重复
 */
public class Map_class {
    public static void main(String[] args) {
        // 创建Map集合
        Map hashmap = new HashMap();
        // 在Map内添加元素
        hashmap.put("key1","val1");
        hashmap.put("key2","val2");
        hashmap.put("key3","val3");
        hashmap.put("key4","val4");
        hashmap.put("key5","val5");
        // 通过key值删除元素
        hashmap.remove("key5");
        // 通过key值获取value
        hashmap.get("key1");
        // 通过添加方法修改value
        hashmap.put("key1","val11");
        // 通过values方法得到Map中的value集合
        Collection values = hashmap.values();
        // 遍历集合
        for (Object obj:values){
            System.out.println(obj);
        }
        // 通过keySet方法得到Map的key集合,然后通过get(key)得到value
        Set keySet = hashmap.keySet();
        // 遍历集合
        for (Object obj:keySet){
            System.out.println(obj);
            // 通过key得到value
            System.out.println(hashmap.get(obj));
        }
        // 通过entrySet方法得到Map的Entry集合,然后遍历
        Set<Map.Entry<String,String>> entrySet = hashmap.entrySet();
        for (Map.Entry<String,String> entry:entrySet){
            // 获取key值
            System.out.println(entry.getKey());
            // 获取value值
            System.out.println(entry.getValue());
        }
    }
}

package 网络程序.BIO聊天室;

import java.util.*;

/**
 * 聊天室(数据结构)
 * 用来保存用户名,套接字和输出流的映射关系
 * @param <k> : 代表客户端的用户名
 * @param <v> : 是一个输出流 PrintStream
 */
public class ChatRoomMap<k,v> {
    // 创建一个线程安全的集合
    public Map<k,v> map = Collections.synchronizedMap(new HashMap<>());
    /**
     * 根据值来删除指定项目
     */
    public synchronized void removeByValue(Object value){
        // 遍历集合
        for (Object key:map.keySet()){
            // 如果遍历的值和传入的值相等
            if (map.get(key) == value){
                // 删除数据
                map.remove(key);
                // 结束循环
                break;
            }
        }
    }
    /**
     * 获取所有的值组合成的set集合
     */
    public synchronized Set<v> getValueSet(){
        // 实例化集合
        Set<v> res = new HashSet<>();
        // 遍历集合
        for (Object key:map.keySet()){
            // 将map中的值添加到res集合里
            res.add(map.get(key));
        }
        // 返回
        return res;
    }
    /**
     * 根据值来找到key
     */
    public synchronized k getKeyByValue(v val){
        // 遍历集合
        for (k key:map.keySet()){
            // 如果遍历的值和传入的值相等,或者equals和传入的值相等
            if (map.get(key) == val || map.get(key).equals(val)){
                // 返回key
                return key;
            }
        }
        // 没有对应的键
        return null;
    }
    /**
     * 添加数据到映射集合,值不能重复
     */
    public synchronized v put(k key,v value){
        // 遍历所有的值
        for (v val : getValueSet()){
            // 判断遍历的值和传入的值是否相等,并且和传入的值的hashCode也要相等
            if (val.equals(value) && val.hashCode() == value.hashCode()){
                // 丢出异常
                throw new RuntimeException("Map实例中有重复的值");
            }
        }
        // 没有重复,放入传入的值
        return map.put(key,value);
    }
}

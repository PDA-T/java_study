package 线程.线程高级.读写锁;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 集合读写锁
 * 集合大的时候可以使用读写锁
 */
public class RWDictionary {
}
class DollectionRWLTest{
    // 线程共享的集合,TreeMap会按照规律进行排序1-9,a-z
    private Map<String,Object>m = new TreeMap<>();
    // 获取读写锁
    private ReadWriteLock rw = new ReentrantReadWriteLock();
    // 获取写锁
    private Lock w = rw.writeLock();
    // 获取读锁
    private Lock r = rw.readLock();
    // 通过集合的Key获取集合的值
    public Object get(String key){
        // 获取读锁
        r.lock();
        try {
            // 返回获取的值
            return m.get(key);
        }finally {
            // 释放读锁
            r.unlock();
        }
    }
    // 获取集合里所有的key
    public String[] allkeys(){
        // 获取读锁
        r.lock();
        try {
            // 创建Set集合
            Set<String> rsSet = m.keySet();
            // 返回获取的值,固定写法,把Set集合变为数组
            return rsSet.toArray(new String[rsSet.size()]);
        }finally {
            // 释放读锁
            r.unlock();
        }
    }
    // 放值进入集合
    public Object put(String key,Object val){
        // 获取写锁
        w.lock();
        try {
            // 写入值
            return m.put(key,val);
        }finally {
            // 释放写锁
            w.unlock();
        }
    }
}
package 基础类;

import java.util.HashMap;
import java.util.Map;

/**
 * 为什么要重写equals方法和hashCode方法
 */
public class Object_equals {
    public static void main(String[] args) {
        String s1=new String("Key");
        String s2=new String("Key");
        // 创建一个集合对象,数据容器
        Map<String,Value> map1=new HashMap<>();
        Value value=new Value(12);
        map1.put(s1,value);// 放入一个值
        System.out.println(s1.equals(s2));// 判断是否相等
        System.out.println(map1.get(s1));// 获取s1对应的值
        /*
         * 因为String重写了equals和hashCode所以使用s2依然能够获取
         * 没有重写hashCode方法会调用超类的hashCode方法(通过内存地址运算出一组整数)
         * s1和s2是两个对象,所以返回地址不会一样
         * Map是通过hashCode方法判断传入的键和保存的键是否一致
         */
        System.out.println(map1.get(s2));

        Map<Key,Value> map2=new HashMap<>();
        Value value2=new Value(32);
        Key key=new Key("11");
        Key key2=new Key("11");
        System.out.println(key.equals(key2));// 判断是否相等
        map2.put(key,value2);// 放入一个值
        System.out.println(map2.get(key));// 获取key对应的值
        /*
         * 因为没有重写equals和hashCode所以使用key2无法获取
         * 没有重写hashCode方法会调用超类的hashCode方法(通过内存地址运算出一组整数)
         * key和key2是两个对象,所以返回地址不会一样(返回为null)
         * Map是通过hashCode方法判断传入的键和保存的键是否一致
         */
        System.out.println(map2.get(key2));
    }
    static class Key{
        private String k;
        public Key(String k){
            this.k=k;
        }

        @Override
        public boolean equals(java.lang.Object o){
            if (o instanceof Key){
                Key oKey=(Key)o;
                return oKey.k.equals(this.k);
            }
            return false;
        }

        /**
         * 重写hashCode方法,使Map不会使用超类的hashCode方法去判断
         * 让hashCode判断类的属性值,如果属性值一样返回的哈希值就一样
         * (超类的值是通过内存地址运算出一组整数,内存地址不一样哈希值就不一样)
         * @return
         */
        @Override
        public int hashCode(){
            return k.hashCode();
        }
    }
    static class Value{
        private int v;
        public Value(int v){
            this.v=v;
        }

        @Override
        public String toString() {
            return "Value{" +
                    "v=" + v +
                    '}';
        }
    }
}

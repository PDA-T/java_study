package ������;

import java.util.HashMap;
import java.util.Map;

/**
 * ΪʲôҪ��дequals������hashCode����
 */
public class Object_equals {
    public static void main(String[] args) {
        String s1=new String("Key");
        String s2=new String("Key");
        // ����һ�����϶���,��������
        Map<String,Value> map1=new HashMap<>();
        Value value=new Value(12);
        map1.put(s1,value);// ����һ��ֵ
        System.out.println(s1.equals(s2));// �ж��Ƿ����
        System.out.println(map1.get(s1));// ��ȡs1��Ӧ��ֵ
        /*
         * ��ΪString��д��equals��hashCode����ʹ��s2��Ȼ�ܹ���ȡ
         * û����дhashCode��������ó����hashCode����(ͨ���ڴ��ַ�����һ������)
         * s1��s2����������,���Է��ص�ַ����һ��
         * Map��ͨ��hashCode�����жϴ���ļ��ͱ���ļ��Ƿ�һ��
         */
        System.out.println(map1.get(s2));

        Map<Key,Value> map2=new HashMap<>();
        Value value2=new Value(32);
        Key key=new Key("11");
        Key key2=new Key("11");
        System.out.println(key.equals(key2));// �ж��Ƿ����
        map2.put(key,value2);// ����һ��ֵ
        System.out.println(map2.get(key));// ��ȡkey��Ӧ��ֵ
        /*
         * ��Ϊû����дequals��hashCode����ʹ��key2�޷���ȡ
         * û����дhashCode��������ó����hashCode����(ͨ���ڴ��ַ�����һ������)
         * key��key2����������,���Է��ص�ַ����һ��(����Ϊnull)
         * Map��ͨ��hashCode�����жϴ���ļ��ͱ���ļ��Ƿ�һ��
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
         * ��дhashCode����,ʹMap����ʹ�ó����hashCode����ȥ�ж�
         * ��hashCode�ж��������ֵ,�������ֵһ�����صĹ�ϣֵ��һ��
         * (�����ֵ��ͨ���ڴ��ַ�����һ������,�ڴ��ַ��һ����ϣֵ�Ͳ�һ��)
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

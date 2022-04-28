package ����;

/**
 * ������,ʵ���������ʱ�������Tָ������������(�����βκ�ʵ��)
 */
public class Generic<T> {
    private T key;
    public Generic(T key){
        this.key=key;
    }
    public T getKey(){
        return key;
    }
}
class Demo{
    public static void main(String[] args) {
        Generic<String> generic = new Generic<String>("acv");
        // û��ָ�����Ͳ��ᱨ��
        Generic generic2 = new Generic("acd");
        // û��ָ�����Ϳ��Դ����κ�����,����T������Object
        Generic generic3 = new Generic(50);
        /*
         * NumberΪInteger�ĸ���,�Ƿ���԰�����Ķ����������ķ���:������
         */
        Generic<Number> g1 = new Generic<Number>(123);
        Generic<Integer> g2 = new Generic<Integer>(321);
        // ���÷���
        show(g1);
        // ���÷���,�жϷ���Number������Integer�Ķ����Ƿ���Դ���:������
        show(g2);
    }

    /**
     * NumberΪInteger�ĸ���,�Ƿ���԰�����Ķ����������Ķ���:������
     * ʹ�÷���ͨ���?���ɽ��,?��ʵ��ͨ���,�����β�(T)!
     * �β�(T)Ϊ���巺�����ʱ��ʹ��
     * ���԰�?����������ĸ���,��һ����ʵ������!
     * @param generic
     */
    public static void show(Generic<?> generic){
        System.out.println("genric");
    }
}

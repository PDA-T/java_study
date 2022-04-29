package ����;

/**
 * ���͵����±߽�,�и�����,�������±߽����Ӧ���ڷ��ͷ���
 * �����ҪƵ����ȡ����,ʹ���ϱ߽�
 * �����ҪƵ����������,ʹ���±߽�
 */
public class Generic_method2 {
    public static void main(String[] args) {
        /*
         * ʹ��Fruit�ķ����޷�ʵ����Apple2,��Ϊ��ʹ�м̳й�ϵ,java��Ȼ�޷�����
         * ʹ��ͨ���?(��ʾȫ������)��Χ̫��
         * �����ʹ���ϱ߽�<? extends Fruit>����
         * <? extends ��>��ʾ��?�ż̳е�������ȫ�����������඼�Ϸ�(ָ���ϱ߽�)
         * <? super ��>��ʾ��?��super�������ϵ�ȫ�����඼���Ը���������(ָ���±߽�)
         */
        Plate<? extends Fruit> p = new Plate<Apple2>(new Apple2());
        /*
         * ʹ��ͨ����ϱ߽�set����ʧЧ,�޷��޸�
         * ��Ϊ������ֻ֪����������Fruit��������������,����ʲô���Ͳ�֪��
         * ��ʹ��set��������ʱ���������֪���ܲ��ܺ�?ƥ��,�����޷�set
         */
//        p.set(new Fruit());// ����
//        p.set(new Apple2());// ����
        Fruit f = p.get();
        Object o = p.get();
        /*
         * ���ϱ�����ֻ֪����������Fruit������,��֪����������,���Բ�֪������������ǲ���Apple
         * �����޷���ֵ��Apple����
         */
//        Apple2 a = p.get();// ����
        // ������ת����һ��д��,��ȡ��Fruit,��ǿתת��
        Apple2 a = (Apple2) f;
        /*
         * ʹ���±߽�,set��������,get����ֻ�ܴ����Object
         */
        Plate<? super Fruit> p2 = new Plate<Fruit>(new Fruit());
        p2.set(new Fruit());
        p2.set(new Apple2());
//        Fruit f2 = p2.get();// ����
        Object o2 = p2.get();
    }
}

/**
 * ����ˮ����
 */
class Fruit{
}

/**
 * ����ƻ����̳�ˮ����
 */
class Apple2 extends Fruit{}
/**
 * ����������
 */
class Plate<T>{
    private T item;
    public Plate(T t){
        item=t;
    }

    public T get() {
        return item;
    }

    public void set(T t) {
        item = t;
    }
}

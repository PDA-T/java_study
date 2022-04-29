package ����;

/**
 * ���ͷ���,�Ƚϸ���
 */
public class Generic_method {
    /**
     * ���巺�ͷ���
     * @param generic
     * @param <T>
     * @return
     */
    public <T> T show(Generic<T> generic){
        System.out.println(generic.getKey());
        T t = generic.getKey();
        return t;
    }

    public static void main(String[] args) {
        Apple apple = new Apple();
        Person person = new Person();
        Generic2<Apple> generic = new Generic2<>();
        generic.show_1(apple);
        // show_1�������Ƿ��ͷ���,ʵ�������ʱ���Ѿ�ָ�����巺������Apple,�޷�������������
//        generic.show_1(person);// ����
        generic.show_2(apple);
        // ���ͷ������βε�T�ͷ������T����һ��T,���Լ�ʹ���������ʱָ���˾��巺��AppleҲ���ᱨ��
        generic.show_2(person);
        // show_3��show_2����һ��,ֻ�Ƿ������ֲ���T,Ҳ���ᱨ��
        generic.show_3(apple);
        generic.show_3(person);
    }
}

/**
 * ���巺����
 */
class Generic2<T>{
    // ���巽��
    public void show_1(T t){
        System.out.println(t.toString());
    }
    // ���巺�ͷ���
    public <T> void show_2(T t){
        System.out.println(t.toString());
    }
    // ���巺�ͷ���
    public <E> void show_3(E t){
        System.out.println(t.toString());
    }
}
class Apple{
    @Override
    public String toString() {
        return "Apple{}";
    }
}
class Person{
    @Override
    public String toString() {
        return "Person{}";
    }
}

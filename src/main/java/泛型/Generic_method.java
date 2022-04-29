package 泛型;

/**
 * 泛型方法,比较复杂
 */
public class Generic_method {
    /**
     * 定义泛型方法
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
        // show_1方法不是泛型方法,实例化类的时候已经指定具体泛型类型Apple,无法传入其他参数
//        generic.show_1(person);// 报错
        generic.show_2(apple);
        // 泛型方法内形参的T和泛型类的T不是一个T,所以即使创建类对象时指定了具体泛型Apple也不会报错
        generic.show_2(person);
        // show_3和show_2方法一样,只是泛型名字不是T,也不会报错
        generic.show_3(apple);
        generic.show_3(person);
    }
}

/**
 * 定义泛型类
 */
class Generic2<T>{
    // 定义方法
    public void show_1(T t){
        System.out.println(t.toString());
    }
    // 定义泛型方法
    public <T> void show_2(T t){
        System.out.println(t.toString());
    }
    // 定义泛型方法
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

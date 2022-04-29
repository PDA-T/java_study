package 泛型;

/**
 * 泛型类,实例化对象的时候给泛型T指定真正的类型(类似形参和实参)
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
        // 没有指定泛型不会报错
        Generic generic2 = new Generic("acd");
        // 没有指定泛型可以传入任何类型,泛型T就类似Object
        Generic generic3 = new Generic(50);
        /*
         * Number为Integer的父类,是否可以吧子类的对象赋予给父类的泛型:不可以
         */
        Generic<Number> g1 = new Generic<Number>(123);
        Generic<Integer> g2 = new Generic<Integer>(321);
        // 调用方法
        show(g1);
        // 调用方法,判断泛型Number的子类Integer的对象是否可以传入:不可以
        show(g2);
    }

    /**
     * Number为Integer的父类,是否可以吧子类的对象赋予给父类的对象:不可以
     * 使用泛型通配符?即可解决,?是实参通配符,不是形参(T)!
     * 形参(T)为定义泛型类的时候使用
     * 可以吧?看成所有类的父类,是一种真实的类型!
     * @param generic
     */
    public static void show(Generic<?> generic){
        System.out.println("genric");
    }
}

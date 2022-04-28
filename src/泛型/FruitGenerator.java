package 泛型;

/**
 * 实现未指定具体类型的泛型接口的类也必须实现泛型T
 * 如果给接口指定泛型的具体类型,则类可以不用写泛型T
 * 给接口泛型指定具体类型之后,所有接口方法(返回值为泛型T的方法)的返回值,也必须使用指定类型
 */
public class FruitGenerator<T> implements Generator<T>{
    @Override
    public T next() {
        return null;
    }
}

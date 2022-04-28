package 基础接口;

import java.util.Comparator;

public class Comparator_Dog implements Comparator<Comparable_Dog> {
    @Override
    public int compare(Comparable_Dog o1, Comparable_Dog o2) {
        // 和重写compareTo方法一样
        return o1.getId()-o2.getId();
    }
}

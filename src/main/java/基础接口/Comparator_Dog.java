package �����ӿ�;

import java.util.Comparator;

public class Comparator_Dog implements Comparator<Comparable_Dog> {
    @Override
    public int compare(Comparable_Dog o1, Comparable_Dog o2) {
        // ����дcompareTo����һ��
        return o1.getId()-o2.getId();
    }
}

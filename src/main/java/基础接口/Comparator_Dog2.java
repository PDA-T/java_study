package »ù´¡½Ó¿Ú;

import java.util.Comparator;

public class Comparator_Dog2 implements Comparator<Comparable_Dog> {
    @Override
    public int compare(Comparable_Dog o1, Comparable_Dog o2) {
        return o1.getAge()-o2.getAge();
    }
}

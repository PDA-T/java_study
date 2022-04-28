package 集合框架;

import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {
    /**
     * 临时比较规则
     */
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

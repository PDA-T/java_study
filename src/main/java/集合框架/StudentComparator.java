package ���Ͽ��;

import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {
    /**
     * ��ʱ�ȽϹ���
     */
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

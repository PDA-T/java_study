package 基础接口;

import java.util.Arrays;

/**
 * 如果项目中一个类需要排序却没有实现Comparable接口
 * 则可以使用Comparator接口(类比较器)
 */
public class Comparator {
    public static void main(String[] args) {
        // 排序对象
        Comparable_Dog dog1=new Comparable_Dog();
        dog1.setId(1);
        dog1.setAge(2);
        Comparable_Dog dog2=new Comparable_Dog();
        dog2.setId(2);
        dog2.setAge(5);
        Comparable_Dog dog3=new Comparable_Dog();
        dog3.setId(3);
        dog3.setAge(4);
        Comparable_Dog dog4=new Comparable_Dog();
        dog4.setId(4);
        dog4.setAge(1);
        // 创建数组
        Comparable_Dog[] arr=new Comparable_Dog[]{dog3,dog4,dog1,dog2};
        // 输出数组
        System.out.println(Arrays.toString(arr));
        // 数组排序(通过ID排序)
        Arrays.sort(arr,new Comparator_Dog());
        // 数组排序(通过age排序)
        Arrays.sort(arr,new Comparator_Dog2());
        // 输出数组
        System.out.println(Arrays.toString(arr));
    }
}

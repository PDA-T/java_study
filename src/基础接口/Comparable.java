package 基础接口;

import java.util.Arrays;

/**
 * 如果项目开发时已经知道需要排序就使用Comparable接口
 * Comparable接口的compareTo方法
 * 要先使用compareTo方法比大小(实现Comparable接口),之后才可通过sort方法排序
 */
public class Comparable {
    public static void main(String[] args) {
        String s1="a";
        String s2="c";
        // 通过ASCII码相减得出结果(如果相等就对比长度的差值)
        System.out.println(s1.compareTo(s2));
        // 排序对象
        ComParable_Person p1=new ComParable_Person();
        p1.setId(1);
        ComParable_Person p2=new ComParable_Person();
        p2.setId(2);
        ComParable_Person p3=new ComParable_Person();
        p3.setId(3);
        ComParable_Person p4=new ComParable_Person();
        p4.setId(4);
        // 创建数组
        ComParable_Person[] arr=new ComParable_Person[]{p3,p4,p1,p2};
        // 输出数组
        System.out.println(Arrays.toString(arr));
        // 数组排序
        Arrays.sort(arr);
        // 输出数组
        System.out.println(Arrays.toString(arr));
    }
}

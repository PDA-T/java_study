package 集合框架;

import java.util.ArrayList;
import java.util.List;

/**
 * List集合是有序集合,底层为数组实现
 */
public class List_class {
    public static void main(String[] args) {
        // 创建集合
        List li = new ArrayList();
        // 添加值
        li.add(1);
        li.add(2);
        li.add(3);
        li.add(4);
        li.add(5);
        // 普通for循环遍历
        for (int i=0;i<li.size();i++){
            System.out.println(li.get(i));
        }
        // 在指定位置添加元素
        li.add(3,44);
        // 在指定的位置修改元素
        li.set(3,33);
        // 获取指定值的索引
        System.out.println(li.indexOf(33));
    }
}

package 集合框架;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Iterator_class {
    public static void main(String[] args) {
        // 使用接口实例化集合对象
        Collection collection = new ArrayList();
        // 在集合里添加值
        collection.add("1");
        collection.add("2");
        collection.add("3");
        // 删除值
        collection.remove("1");
        // 创建迭代器遍历集合
        Iterator iterator = collection.iterator();
        // 判断迭代器是否有值,如果有值进入循环
        while (iterator.hasNext()){
            // 将迭代器的值赋值给String
            String str = (String) iterator.next();
            // 输出
            System.out.println(str);
        }
    }
}

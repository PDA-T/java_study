package 泛型;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型数组
 */
public class GenericArray {
    public static void main(String[] args) {
        // 数组不能指定具体的泛型类型
//        List<String>[] lia = new ArrayList<>[10];// 报错
        // 数组可以使用通配符
        List<?>[] lia = new ArrayList<?>[10];
        List<?>[] lia2 = new ArrayList[10];
    }
}

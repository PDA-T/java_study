package 基础类;

/**
 * String类每次使用字符串拼接等操作都会创建一个新对象
 * 原对象依然存在
 */
public class String_class {
    public static void main(String[] args) {
        String s="";
        // 根据String的源码,此循环将会创建10000个对象
        for (int i = 0; i<10000; i++){
            s+="hello";
        }
        System.out.println(s);
    }
}

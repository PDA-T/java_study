package 基础类;

/**
 * 大量字符串拼接使用StringBuilder
 * 大量字符串拼接并且是多线程使用StringBuffer
 */
public class StringBuilder_class {
    public static void main(String[] args) {
        StringBuilder sBuilder=new StringBuilder();
        // 此循环只会创建1个对象
        for (int i=0;i<10000;i++){
            sBuilder.append("hello");
        }
        System.out.println(sBuilder);
    }
}

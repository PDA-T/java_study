package 反射;
/**
 * 可以获取任何类的信息
 */
public class ClassDemo2 {
    public static void main(String[] args) {
        String s="hello";// 创建字符串
        ClassUtil.printClassMethodMessage(s);// 传入类型s对象
    }
}

package 反射;

public class ClassDemo3 {
    public static void main(String[] args) {
        ClassUtil.printFieldMessage("helo");// 传入参数
        System.out.println("------------------------------");// 分割线
        ClassUtil.printFieldMessage(new Integer(1));// 传入参数
    }
}

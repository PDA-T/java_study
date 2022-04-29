package 注解.测试;

/**
 * 获取注解各项属性
 */
@MyAnnotation("cqy")
public class Demo {
    public static void main(String[] args) {
        // 判断此类是否有注解
        if (Demo.class.isAnnotationPresent(MyAnnotation.class)){
            // 通过反射获取注解对象
            MyAnnotation myAnnotation = Demo.class.getAnnotation(MyAnnotation.class);
            // 通过注解对象获取注解属性
            System.out.println(myAnnotation.color());
            System.out.println(myAnnotation.value());
            System.out.println(myAnnotation.arrayAttr().length);
            System.out.println(myAnnotation.lamp());
            System.out.println(myAnnotation.mate());
        }else {
            System.out.println("无注解");
        }
    }
}

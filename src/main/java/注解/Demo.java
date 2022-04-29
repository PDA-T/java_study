package 注解;

// 注解类定义了属性之后就必须要赋值,如果指定了默认值,则可以不需要赋值
@AnnotationClass(value = "aaa",color = "red")
public class Demo {
    public static void main(String[] args) {
        // 通过反射判断是否存在注解
        if (Demo.class.isAnnotationPresent(AnnotationClass.class)){
            System.out.println("true");
            // 获取注解的实例对象
            AnnotationClass annotation = Demo.class.getAnnotation(AnnotationClass.class);
            // 输出注解对象和属性值
            System.out.println(annotation);
            System.out.println(annotation.color());
        }else {
            System.out.println("false");
        }
    }
}

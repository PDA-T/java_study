package 注解.测试;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义注解
 */
// 设置第三阶段存活
@Retention(RetentionPolicy.RUNTIME)
// 设置此注解可以应用于类和方法
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface MyAnnotation {
    // 设置颜色属性默认值为blue
    String color() default "blue";
    // 设置一个特殊属性
    String value();
    // 设置一个数组属性,默认值1,2,3
    int[] arrayAttr() default {1,2,3};
    // 设置枚举属性,默认值YELLOW
    EumTrafficLamp lamp() default EumTrafficLamp.YELLOW;
    // 设置注解属性,默认注解值为abc
    MetaAnnotation mate() default @MetaAnnotation("abc");
}

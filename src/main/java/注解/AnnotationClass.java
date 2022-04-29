package 注解;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解类
 * java执行三阶段:后缀为java的源码阶段,后缀为Class的编译阶段,jvm加载运行的运行阶段
 */
// 注解类上使用的注解为元注解,表示自定义注解的存在周期
//@Retention(RetentionPolicy.SOURCE)// 指定自定义的注解类的生命周期,只存在于第一阶段
//@Retention(RetentionPolicy.CLASS)// 指定自定义的注解类的生命周期,可以存在于第二阶段
@Retention(RetentionPolicy.RUNTIME)// 指定自定义的注解类的生命周期,可以存在于第三阶段
@Target({ElementType.TYPE,ElementType.METHOD})// 表示自定义注解可以放在那些地方(类,方法,变量,包...)
public @interface AnnotationClass {
    // 注解内的属性定义,可以使用default指定默认值
    String color() default "blue";
    // 特殊属性,赋值时可以不写value
    String value();
}

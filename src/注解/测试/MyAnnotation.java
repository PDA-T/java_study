package ע��.����;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ����ע��
 */
// ���õ����׶δ��
@Retention(RetentionPolicy.RUNTIME)
// ���ô�ע�����Ӧ������ͷ���
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface MyAnnotation {
    // ������ɫ����Ĭ��ֵΪblue
    String color() default "blue";
    // ����һ����������
    String value();
    // ����һ����������,Ĭ��ֵ1,2,3
    int[] arrayAttr() default {1,2,3};
    // ����ö������,Ĭ��ֵYELLOW
    EumTrafficLamp lamp() default EumTrafficLamp.YELLOW;
    // ����ע������,Ĭ��ע��ֵΪabc
    MetaAnnotation mate() default @MetaAnnotation("abc");
}

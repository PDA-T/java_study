package ע��;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * �Զ���ע����
 * javaִ�����׶�:��׺Ϊjava��Դ��׶�,��׺ΪClass�ı���׶�,jvm�������е����н׶�
 */
// ע������ʹ�õ�ע��ΪԪע��,��ʾ�Զ���ע��Ĵ�������
//@Retention(RetentionPolicy.SOURCE)// ָ���Զ����ע�������������,ֻ�����ڵ�һ�׶�
//@Retention(RetentionPolicy.CLASS)// ָ���Զ����ע�������������,���Դ����ڵڶ��׶�
@Retention(RetentionPolicy.RUNTIME)// ָ���Զ����ע�������������,���Դ����ڵ����׶�
@Target({ElementType.TYPE,ElementType.METHOD})// ��ʾ�Զ���ע����Է�����Щ�ط�(��,����,����,��...)
public @interface AnnotationClass {
    // ע���ڵ����Զ���,����ʹ��defaultָ��Ĭ��ֵ
    String color() default "blue";
    // ��������,��ֵʱ���Բ�дvalue
    String value();
}

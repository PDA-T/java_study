package ע��;

// ע���ඨ��������֮��ͱ���Ҫ��ֵ,���ָ����Ĭ��ֵ,����Բ���Ҫ��ֵ
@AnnotationClass(value = "aaa",color = "red")
public class Demo {
    public static void main(String[] args) {
        // ͨ�������ж��Ƿ����ע��
        if (Demo.class.isAnnotationPresent(AnnotationClass.class)){
            System.out.println("true");
            // ��ȡע���ʵ������
            AnnotationClass annotation = Demo.class.getAnnotation(AnnotationClass.class);
            // ���ע����������ֵ
            System.out.println(annotation);
            System.out.println(annotation.color());
        }else {
            System.out.println("false");
        }
    }
}

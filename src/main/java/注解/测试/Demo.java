package ע��.����;

/**
 * ��ȡע���������
 */
@MyAnnotation("cqy")
public class Demo {
    public static void main(String[] args) {
        // �жϴ����Ƿ���ע��
        if (Demo.class.isAnnotationPresent(MyAnnotation.class)){
            // ͨ�������ȡע�����
            MyAnnotation myAnnotation = Demo.class.getAnnotation(MyAnnotation.class);
            // ͨ��ע������ȡע������
            System.out.println(myAnnotation.color());
            System.out.println(myAnnotation.value());
            System.out.println(myAnnotation.arrayAttr().length);
            System.out.println(myAnnotation.lamp());
            System.out.println(myAnnotation.mate());
        }else {
            System.out.println("��ע��");
        }
    }
}

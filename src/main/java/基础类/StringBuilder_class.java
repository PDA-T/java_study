package ������;

/**
 * �����ַ���ƴ��ʹ��StringBuilder
 * �����ַ���ƴ�Ӳ����Ƕ��߳�ʹ��StringBuffer
 */
public class StringBuilder_class {
    public static void main(String[] args) {
        StringBuilder sBuilder=new StringBuilder();
        // ��ѭ��ֻ�ᴴ��1������
        for (int i=0;i<10000;i++){
            sBuilder.append("hello");
        }
        System.out.println(sBuilder);
    }
}

package ������;

/**
 * String��ÿ��ʹ���ַ���ƴ�ӵȲ������ᴴ��һ���¶���
 * ԭ������Ȼ����
 */
public class String_class {
    public static void main(String[] args) {
        String s="";
        // ����String��Դ��,��ѭ�����ᴴ��10000������
        for (int i = 0; i<10000; i++){
            s+="hello";
        }
        System.out.println(s);
    }
}

package ������ʽ;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ��֤�����Ƿ���ȷ
 */
public class Email {
    public static void main(String[] args) {
        // Ҫ��֤�������ַ���
        String email = "1581109028@vip.qq.com";
        // ������֤����
        String regEx = "^[a-zA-Z0-9]+([._-]*|[a-zA-Z0-9]*)*@[a-zA-Z0-9]+([-.]*|[a-zA-Z0-9]*)*\\.[a-zA-Z]{2,4}$";
        // ����������ʽ(��������ʽ��װ��һ������)
        Pattern pattern = Pattern.compile(regEx);
        // ���Դ�Сдд��
        Pattern pat = Pattern.compile(regEx,Pattern.CASE_INSENSITIVE);
        // ���÷�װ�Ķ��󴴽�ƥ����
        Matcher matcher = pattern.matcher(email);
        // �ַ����Ƿ���������ʽƥ��
        boolean rs = matcher.matches();
        // һ���ַ�������ҷ���������ʽ���ַ�
        boolean rs2 = matcher.find();
        // �滻����������ʽ���ַ�
        String newstr = email.replaceAll(regEx, "aaa");
        // ����Ƿ�ƥ��
        System.out.println(rs);
        // ����滻����ַ���
        System.out.println(newstr);
    }
}

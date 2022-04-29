package 正则表达式;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证邮箱是否正确
 */
public class Email {
    public static void main(String[] args) {
        // 要验证的邮箱字符串
        String email = "1581109028@vip.qq.com";
        // 邮箱验证规则
        String regEx = "^[a-zA-Z0-9]+([._-]*|[a-zA-Z0-9]*)*@[a-zA-Z0-9]+([-.]*|[a-zA-Z0-9]*)*\\.[a-zA-Z]{2,4}$";
        // 编译正则表达式(吧正则表达式封装成一个对象)
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写写法
        Pattern pat = Pattern.compile(regEx,Pattern.CASE_INSENSITIVE);
        // 利用封装的对象创建匹配器
        Matcher matcher = pattern.matcher(email);
        // 字符串是否与正则表达式匹配
        boolean rs = matcher.matches();
        // 一串字符里面查找符合正则表达式的字符
        boolean rs2 = matcher.find();
        // 替换符合正则表达式的字符
        String newstr = email.replaceAll(regEx, "aaa");
        // 输出是否匹配
        System.out.println(rs);
        // 输出替换后的字符串
        System.out.println(newstr);
    }
}

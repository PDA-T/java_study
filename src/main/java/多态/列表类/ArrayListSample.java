package 多态.列表类;

import java.util.ArrayList;
import java.util.List;

public class ArrayListSample {
    public static void main(String[] args) {
        List<String> booklist=new ArrayList<String>();// 实例化列表对象并且指定泛型为字符串类型
        booklist.add("红楼梦");// 给列表添加值
        booklist.add("西游记");// 给列表添加值
        booklist.add("水浒传");// 给列表添加值
        booklist.add("三国志");// 给列表添加值
        booklist.add(0,"镜花缘");// 给列表指定位置添加值
        System.out.println(booklist);// 输出列表
        String bookName=booklist.get(2);// 获取列表第二个的值
        System.out.println(bookName);// 输出列表第二个的值
        int size=booklist.size();// 获取列表一共有几个值
        System.out.println(size);// 输出列表一共有几个值
        booklist.remove(2);// 删除列表第二个的值
        System.out.println(booklist);// 输出删除后的列表
        booklist.remove(booklist.size()-1);// 逻辑方法删除列表最后一个值
        System.out.println(booklist);// 输出删除后的列表
        for (String book:booklist){// 循环遍历
            System.out.println(book);// 输出单个列表值
        }
    }
}

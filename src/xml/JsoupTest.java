package xml;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 需要导入jar包
 * 遍历xml元素
 * 也可以做爬虫
 */
public class JsoupTest {
    public static void main(String[] args) throws IOException {
        // 获取路径
//        String path = Jsoup.class.getClassLoader().getResource("xml.xml").getPath();
        // 获取文档对象,代表内存中的dom数
        Document document = Jsoup.parse(new File("E:\\Project\\Java\\study\\src\\xml\\xml.xml"), "UTF-8");
        // 输出整个xml文档内容
        System.out.println(document);

        System.out.println("------------------------------");

        // 通过文档对象获取元素集合
        Elements elements = document.getElementsByTag("name");

        // 输出指定下标元素的位置,转换为文本(否则为html格式)
        System.out.println("第1个name元素内的值为:"+elements.get(0).text());
        System.out.println("第2个name元素内的值为:"+elements.get(1).text());
        System.out.println("第3个name元素内的值为:"+elements.get(2).text());

        System.out.println("------------------------------");

        // 根据属性名称获取元素集合
        Elements elements2 = document.getElementsByAttribute("id");
        System.out.println(elements2);

        System.out.println("------------------------------");

        // 根据属性名和属性值获取元素集合
        Elements elements3 = document.getElementsByAttributeValue("id","2");
        System.out.println(elements3);

        System.out.println("------------------------------");

        // 获取子第1个books元素
        Element element = document.getElementsByTag("books").get(0);
        // 从第一个books元素内查找name元素
        Elements elements1 = element.getElementsByTag("name");
        // 找到了1个
        System.out.println("第一个books内name元素内的值为:"+elements1.text());

        System.out.println("------------------------------");

        // 根据ID属性获取
        Element elements4 = document.getElementById("1");
        System.out.println(elements4);

        System.out.println("------------------------------");

        // 获取第1个books属性的值
        String books = element.attr("id");
        System.out.println("属性值:"+books);

        System.out.println("------------------------------");

        // 获取第一个books元素内name的文本内容
        String text = elements1.text();
        // 获取第一个books元素内name的html内容
        String html = elements1.html();
        System.out.println("文本:"+text);
        System.out.println("HTML:"+html);

        System.out.println("------------------------------");

        // 使用选择器快速查询(具体语法参考jar官方文档),查询books标签并且id属性值为2
        Elements name = document.select("books[id='2']");
        System.out.println("选择器:"+name);

        System.out.println("------------------------------");

        // 查询books标签并且id属性值为2并且是子标签nianling
        Elements name2 = document.select("books[id='2']>nianling");
        System.out.println("选择器2:"+name2);

        System.out.println("------------------------------");

        URL url = new URL("http://1lin.xyz");
        // 获取网页HTML源码
        Document parse = Jsoup.parse(url, 10000);
        // 输出网站源码,可以做爬虫
        System.out.println(parse);
    }
}

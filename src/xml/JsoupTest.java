package xml;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * ��Ҫ����jar��
 * ����xmlԪ��
 * Ҳ����������
 */
public class JsoupTest {
    public static void main(String[] args) throws IOException {
        // ��ȡ·��
//        String path = Jsoup.class.getClassLoader().getResource("xml.xml").getPath();
        // ��ȡ�ĵ�����,�����ڴ��е�dom��
        Document document = Jsoup.parse(new File("E:\\Project\\Java\\study\\src\\xml\\xml.xml"), "UTF-8");
        // �������xml�ĵ�����
        System.out.println(document);

        System.out.println("------------------------------");

        // ͨ���ĵ������ȡԪ�ؼ���
        Elements elements = document.getElementsByTag("name");

        // ���ָ���±�Ԫ�ص�λ��,ת��Ϊ�ı�(����Ϊhtml��ʽ)
        System.out.println("��1��nameԪ���ڵ�ֵΪ:"+elements.get(0).text());
        System.out.println("��2��nameԪ���ڵ�ֵΪ:"+elements.get(1).text());
        System.out.println("��3��nameԪ���ڵ�ֵΪ:"+elements.get(2).text());

        System.out.println("------------------------------");

        // �����������ƻ�ȡԪ�ؼ���
        Elements elements2 = document.getElementsByAttribute("id");
        System.out.println(elements2);

        System.out.println("------------------------------");

        // ����������������ֵ��ȡԪ�ؼ���
        Elements elements3 = document.getElementsByAttributeValue("id","2");
        System.out.println(elements3);

        System.out.println("------------------------------");

        // ��ȡ�ӵ�1��booksԪ��
        Element element = document.getElementsByTag("books").get(0);
        // �ӵ�һ��booksԪ���ڲ���nameԪ��
        Elements elements1 = element.getElementsByTag("name");
        // �ҵ���1��
        System.out.println("��һ��books��nameԪ���ڵ�ֵΪ:"+elements1.text());

        System.out.println("------------------------------");

        // ����ID���Ի�ȡ
        Element elements4 = document.getElementById("1");
        System.out.println(elements4);

        System.out.println("------------------------------");

        // ��ȡ��1��books���Ե�ֵ
        String books = element.attr("id");
        System.out.println("����ֵ:"+books);

        System.out.println("------------------------------");

        // ��ȡ��һ��booksԪ����name���ı�����
        String text = elements1.text();
        // ��ȡ��һ��booksԪ����name��html����
        String html = elements1.html();
        System.out.println("�ı�:"+text);
        System.out.println("HTML:"+html);

        System.out.println("------------------------------");

        // ʹ��ѡ�������ٲ�ѯ(�����﷨�ο�jar�ٷ��ĵ�),��ѯbooks��ǩ����id����ֵΪ2
        Elements name = document.select("books[id='2']");
        System.out.println("ѡ����:"+name);

        System.out.println("------------------------------");

        // ��ѯbooks��ǩ����id����ֵΪ2�������ӱ�ǩnianling
        Elements name2 = document.select("books[id='2']>nianling");
        System.out.println("ѡ����2:"+name2);

        System.out.println("------------------------------");

        URL url = new URL("http://1lin.xyz");
        // ��ȡ��ҳHTMLԴ��
        Document parse = Jsoup.parse(url, 10000);
        // �����վԴ��,����������
        System.out.println(parse);
    }
}

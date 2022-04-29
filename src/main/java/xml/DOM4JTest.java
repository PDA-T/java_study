package xml;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * 非官方提供的xml解析,需导入jar包
 */
public class DOM4JTest {
    public static void main(String[] args) {
        // 创建SAXReader的对象
        SAXReader reader=new SAXReader();
        try {
            // 通过reader对象的read方法加载xml文件,获取document对象
            Document document=reader.read(new File("E:/Project/Java/study/src/xml/xml.xml"));
            // 通过document对象获取根节点bookStore
            Element bookStore=document.getRootElement();
            // 通过element对象的getRootElement方法获取迭代器
            Iterator it=bookStore.elementIterator();
            // 遍历迭代器,获取根节点中的信息
            while (it.hasNext()){
                System.out.println("-------------开始遍历-------------");
                Element book=(Element) it.next();
                // 获取book的属性名和属性值
                List<Attribute> bookAttrs=book.attributes();
                for (Attribute attr:bookAttrs){
                    System.out.println("属性名:"+attr.getName()+"---属性值:"+attr.getValue());
                }
                // 遍历子节点
                Iterator itt=book.elementIterator();
                while (itt.hasNext()){
                    Element bookChild=(Element) itt.next();
                    System.out.println("节点名:"+bookChild.getName()+"---节点值:"+bookChild.getStringValue());
                }
                System.out.println("-------------结束遍历-------------\n");
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}

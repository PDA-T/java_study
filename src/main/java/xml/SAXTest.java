package xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;

public class SAXTest {
    public static void main(String[] args) {
        // 获取SAXParserFactory实例
        SAXParserFactory factory=SAXParserFactory.newInstance();
        try {
            // 通过factory获取SAXParser实例
            SAXParser parser=factory.newSAXParser();
            // 创建SAXParserHandler对象
            SAXParserHandler handler=new SAXParserHandler();
            parser.parse("E:/Project/Java/study/src/xml/xml.xml",handler);
            System.out.println("共有:"+handler.getBookList().size()+"本书");
            for (Book book:handler.getBookList()){
                System.out.println(book.getId());
                System.out.println(book.getName());
                System.out.println(book.getNianling());
                System.out.println(book.getShenfenzheng());
                System.out.println("----------------------");
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class SAXParserHandler extends DefaultHandler{
    String value=null;
    Book book=null;
    private ArrayList<Book> bookList=new ArrayList<Book>();
    int bookIndex=0;
    /**
     * 用来遍历xml文件的开始标签
     * 解析xml元素
     * @param uri
     * @param localName
     * @param qName
     * @param attributes
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // 调用DefaultHandler类的startElement方法
        super.startElement(uri,localName,qName,attributes);
        // 开始解析books元素属性
        if (qName.equals("books")){
            book=new Book();// 创建一个Book对象
            bookIndex++;
            System.out.println("-----------开始遍历第"+bookIndex+"本书的内容---------");
            // 已知books元素下属性的名称,根据属性名称获取属性值
//            String value=attributes.getValue("id");
//            System.out.println("属性值为:"+value);
            // 不知道books属性下名称以及个数
            int num=attributes.getLength();// 获取属性个数
            for (int i=0;i<num;i++){
                System.out.print("books元素的第"+(i+1)+"个属性名是:"+
                        attributes.getQName(i));
                System.out.println("---属性值是:"+attributes.getValue(i));
                if (attributes.getQName(i).equals("id")){
                    book.setId(attributes.getValue(i));// 属性值
                }
            }
        }else if (!qName.equals("books")&&!qName.equals("book")){
            System.out.print("节点名是:"+qName+"---");
        }
    }

    /**
     * 用来遍历xml文件的结束标签
     * @param uri
     * @param localName
     * @param qName
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // 调用DefaultHandler类的startElement方法
        super.endElement(uri, localName, qName);
        // 判断是否针对一本书已经遍历结束
        if(qName.equals("books")){
            bookList.add(book);
            book=null;
            System.out.println("-----------结束遍历第"+bookIndex+"本书的内容-----------\n");
        }else if (qName.equals("name")){
            book.setName(value);// 子节点值
        }else if (qName.equals("nianling")){
            book.setNianling(value);// 子节点值
        }else if (qName.equals("shenfenzheng")){
            book.setShenfenzheng(value);// 子节点值
        }
    }

    /**
     * 用来标识解析开始
     * @throws SAXException
     */
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("SAX解析开始");
    }

    /**
     * 用来标识解析结束
     * @throws SAXException
     */
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("SAX解析结束");
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        value=new String(ch,start,length);
        if (!value.trim().equals("")){
            System.out.println(value);
        }
    }

    public ArrayList<Book> getBookList() {
        return bookList;
    }
}

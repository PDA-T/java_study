package xml;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 非官方提供的xml解析,需导入jar包
 */
public class JDOMTest {
    private static ArrayList<Book> booksList=new ArrayList<Book>();
    public static void main(String[] args) {
        // 进行对xml文件的jdom解析
        SAXBuilder saxBuilder=new SAXBuilder();
        InputStream in;
        try {
            // 创建文件输入流,将xml文件加载到输入流中
            in=new FileInputStream("E:/Project/Java/study/src/xml/xml.xml");
            // 字符输入流可以设置字符集
            InputStreamReader isr=new InputStreamReader(in,"UTF-8");
            // 通过build方法,将输入流加载到saxBuilder中
            Document document=saxBuilder.build(isr);
            // 通过document对象获取xml文件的根节点
            Element rootElement=document.getRootElement();
            // 获取跟节点下的子节点的List集合
            List<Element> bookList=rootElement.getChildren();
            // 继续进行解析
            for (Element book:bookList){
                Book bookEntity=new Book();
                System.out.println("----开始解析第:"+(bookList.indexOf(book)+1)+"本书----");
                // 解析books的属性集合
                List<Attribute> attrList=book.getAttributes();
                // 遍历attrList
                for (Attribute attr:attrList){
                    // 获取属性名
                    String attrName=attr.getName();
                    // 获取属性值
                    String attrValue=attr.getValue();
                    System.out.println("属性名:"+attrName+"---属性值:"+attrValue);
                    if (attrName.equals("id")){
                        bookEntity.setId(attrValue);
                    }
                }
                // 对book节点的子节点的节点名和节点值的遍历
                List<Element> bookChilds=book.getChildren();
                for (Element child:bookChilds){
                    System.out.println("节点名:"+child.getName()+"---节点值:"+child.getValue());
                    if (child.getName().equals("name")){
                        bookEntity.setName(child.getValue());
                    }else if (child.getName().equals("nianling")){
                        bookEntity.setNianling(child.getValue());
                    }else if (child.getName().equals("shenfenzheng")){
                        bookEntity.setShenfenzheng(child.getValue());
                    }
                }
                System.out.println("----结束解析第:"+(bookList.indexOf(book)+1)+"本书----\n");
                booksList.add(bookEntity);
                bookEntity=null;// 最后设置为空节省资源
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        }
    }
}

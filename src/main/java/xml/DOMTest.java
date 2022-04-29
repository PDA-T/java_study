package xml;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class DOMTest {
    /**
     * 使用 DOM 解析 XML 文件的属性名和属性值
     * @param args
     */
    public static void main(String[] args) {
        // 创建DocumentBuilderFactory对象
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        try {
            // 创建DocumentBuilder对象
            DocumentBuilder db=dbf.newDocumentBuilder();
            // 加载xml文件到当前项目下
            Document document=db.parse("E:/Project/Java/study/src/xml/xml.xml");
            // 获取所有books节点的集合
            NodeList bookList=document.getElementsByTagName("books");
            // 打印xml一共有几本书
            System.out.println("一共有:"+bookList.getLength()+"本书");
            for (int i=0;i<bookList.getLength();i++){// 遍历每个book节点
                System.out.println("----------------第"+(i+1)+"本书开始-----------------");
                Node book=bookList.item(i);// 通过itme方法获取一个book节点,索引从0开始
                NamedNodeMap attrs=book.getAttributes();// 获取节点中所有的属性值
                // 打印书中共有几个属性
                System.out.println("第"+(i+1)+"本书"+"一共有:"+attrs.getLength()+"个属性");
                for (int j=0;j<attrs.getLength();j++){// 遍历书中的属性
                    Node attr=attrs.item(j);// 通过item获取节点的属性
                    System.out.println("属性名为:"+attr.getNodeName());// 获取属性名
                    System.out.println("属性值为:"+attr.getNodeValue());// 获取属性值
                }
                NodeList childNodes=book.getChildNodes();// 解析book子节点
                // 遍历获取每个节点名和节点值
                System.out.println("第"+(i+1)+"本书共有"+childNodes.getLength()+"个子节点");
                for (int k=0;k<childNodes.getLength();k++){
                    // 区分出text类型的node和element类型的node
                    if (childNodes.item(k).getNodeType()==Node.ELEMENT_NODE){
                        // 获取element类型节点名称
                        System.out.println("节点名为:"+childNodes.item(k).getNodeName());
                        // 获取element类型节点值
                        System.out.println("节点值为:"+childNodes.item(k).getFirstChild().getNodeValue());
//                        System.out.println(childNodes.item(k).getTextContent());
                    }
                }
            }
//            new DOMTest().attrs();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    /**
     * 已知节点数量且只有一个id属性
     */
    public void attrs(){
        // 创建DocumentBuilderFactory对象
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        try {
            // 创建DocumentBuilder对象
            DocumentBuilder db=dbf.newDocumentBuilder();
            // 加载xml文件到当前项目下
            Document document=db.parse("E:/Project/Java/study/src/xml/xml.xml");
            // 获取所有books节点的集合
            NodeList bookList=document.getElementsByTagName("books");
            // 打印xml一共有几本书
            System.out.println("一共有:"+bookList.getLength()+"本书");
            // 强制类型转换,下标从0开始
            Element book= (Element) bookList.item(1);
            // 通过方法获取属性值
            String attrValue=book.getAttribute("id");
            System.out.println("属性值为:"+attrValue);// 打印属性值
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}

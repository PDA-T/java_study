package xml;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class DOMTest {
    /**
     * ʹ�� DOM ���� XML �ļ���������������ֵ
     * @param args
     */
    public static void main(String[] args) {
        // ����DocumentBuilderFactory����
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        try {
            // ����DocumentBuilder����
            DocumentBuilder db=dbf.newDocumentBuilder();
            // ����xml�ļ�����ǰ��Ŀ��
            Document document=db.parse("E:/Project/Java/study/src/xml/xml.xml");
            // ��ȡ����books�ڵ�ļ���
            NodeList bookList=document.getElementsByTagName("books");
            // ��ӡxmlһ���м�����
            System.out.println("һ����:"+bookList.getLength()+"����");
            for (int i=0;i<bookList.getLength();i++){// ����ÿ��book�ڵ�
                System.out.println("----------------��"+(i+1)+"���鿪ʼ-----------------");
                Node book=bookList.item(i);// ͨ��itme������ȡһ��book�ڵ�,������0��ʼ
                NamedNodeMap attrs=book.getAttributes();// ��ȡ�ڵ������е�����ֵ
                // ��ӡ���й��м�������
                System.out.println("��"+(i+1)+"����"+"һ����:"+attrs.getLength()+"������");
                for (int j=0;j<attrs.getLength();j++){// �������е�����
                    Node attr=attrs.item(j);// ͨ��item��ȡ�ڵ������
                    System.out.println("������Ϊ:"+attr.getNodeName());// ��ȡ������
                    System.out.println("����ֵΪ:"+attr.getNodeValue());// ��ȡ����ֵ
                }
                NodeList childNodes=book.getChildNodes();// ����book�ӽڵ�
                // ������ȡÿ���ڵ����ͽڵ�ֵ
                System.out.println("��"+(i+1)+"���鹲��"+childNodes.getLength()+"���ӽڵ�");
                for (int k=0;k<childNodes.getLength();k++){
                    // ���ֳ�text���͵�node��element���͵�node
                    if (childNodes.item(k).getNodeType()==Node.ELEMENT_NODE){
                        // ��ȡelement���ͽڵ�����
                        System.out.println("�ڵ���Ϊ:"+childNodes.item(k).getNodeName());
                        // ��ȡelement���ͽڵ�ֵ
                        System.out.println("�ڵ�ֵΪ:"+childNodes.item(k).getFirstChild().getNodeValue());
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
     * ��֪�ڵ�������ֻ��һ��id����
     */
    public void attrs(){
        // ����DocumentBuilderFactory����
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        try {
            // ����DocumentBuilder����
            DocumentBuilder db=dbf.newDocumentBuilder();
            // ����xml�ļ�����ǰ��Ŀ��
            Document document=db.parse("E:/Project/Java/study/src/xml/xml.xml");
            // ��ȡ����books�ڵ�ļ���
            NodeList bookList=document.getElementsByTagName("books");
            // ��ӡxmlһ���м�����
            System.out.println("һ����:"+bookList.getLength()+"����");
            // ǿ������ת��,�±��0��ʼ
            Element book= (Element) bookList.item(1);
            // ͨ��������ȡ����ֵ
            String attrValue=book.getAttribute("id");
            System.out.println("����ֵΪ:"+attrValue);// ��ӡ����ֵ
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}

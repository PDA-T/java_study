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
 * �ǹٷ��ṩ��xml����,�赼��jar��
 */
public class JDOMTest {
    private static ArrayList<Book> booksList=new ArrayList<Book>();
    public static void main(String[] args) {
        // ���ж�xml�ļ���jdom����
        SAXBuilder saxBuilder=new SAXBuilder();
        InputStream in;
        try {
            // �����ļ�������,��xml�ļ����ص���������
            in=new FileInputStream("E:/Project/Java/study/src/xml/xml.xml");
            // �ַ����������������ַ���
            InputStreamReader isr=new InputStreamReader(in,"UTF-8");
            // ͨ��build����,�����������ص�saxBuilder��
            Document document=saxBuilder.build(isr);
            // ͨ��document�����ȡxml�ļ��ĸ��ڵ�
            Element rootElement=document.getRootElement();
            // ��ȡ���ڵ��µ��ӽڵ��List����
            List<Element> bookList=rootElement.getChildren();
            // �������н���
            for (Element book:bookList){
                Book bookEntity=new Book();
                System.out.println("----��ʼ������:"+(bookList.indexOf(book)+1)+"����----");
                // ����books�����Լ���
                List<Attribute> attrList=book.getAttributes();
                // ����attrList
                for (Attribute attr:attrList){
                    // ��ȡ������
                    String attrName=attr.getName();
                    // ��ȡ����ֵ
                    String attrValue=attr.getValue();
                    System.out.println("������:"+attrName+"---����ֵ:"+attrValue);
                    if (attrName.equals("id")){
                        bookEntity.setId(attrValue);
                    }
                }
                // ��book�ڵ���ӽڵ�Ľڵ����ͽڵ�ֵ�ı���
                List<Element> bookChilds=book.getChildren();
                for (Element child:bookChilds){
                    System.out.println("�ڵ���:"+child.getName()+"---�ڵ�ֵ:"+child.getValue());
                    if (child.getName().equals("name")){
                        bookEntity.setName(child.getValue());
                    }else if (child.getName().equals("nianling")){
                        bookEntity.setNianling(child.getValue());
                    }else if (child.getName().equals("shenfenzheng")){
                        bookEntity.setShenfenzheng(child.getValue());
                    }
                }
                System.out.println("----����������:"+(bookList.indexOf(book)+1)+"����----\n");
                booksList.add(bookEntity);
                bookEntity=null;// �������Ϊ�ս�ʡ��Դ
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

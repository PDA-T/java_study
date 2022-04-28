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
 * �ǹٷ��ṩ��xml����,�赼��jar��
 */
public class DOM4JTest {
    public static void main(String[] args) {
        // ����SAXReader�Ķ���
        SAXReader reader=new SAXReader();
        try {
            // ͨ��reader�����read��������xml�ļ�,��ȡdocument����
            Document document=reader.read(new File("E:/Project/Java/study/src/xml/xml.xml"));
            // ͨ��document�����ȡ���ڵ�bookStore
            Element bookStore=document.getRootElement();
            // ͨ��element�����getRootElement������ȡ������
            Iterator it=bookStore.elementIterator();
            // ����������,��ȡ���ڵ��е���Ϣ
            while (it.hasNext()){
                System.out.println("-------------��ʼ����-------------");
                Element book=(Element) it.next();
                // ��ȡbook��������������ֵ
                List<Attribute> bookAttrs=book.attributes();
                for (Attribute attr:bookAttrs){
                    System.out.println("������:"+attr.getName()+"---����ֵ:"+attr.getValue());
                }
                // �����ӽڵ�
                Iterator itt=book.elementIterator();
                while (itt.hasNext()){
                    Element bookChild=(Element) itt.next();
                    System.out.println("�ڵ���:"+bookChild.getName()+"---�ڵ�ֵ:"+bookChild.getStringValue());
                }
                System.out.println("-------------��������-------------\n");
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}

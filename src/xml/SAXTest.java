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
        // ��ȡSAXParserFactoryʵ��
        SAXParserFactory factory=SAXParserFactory.newInstance();
        try {
            // ͨ��factory��ȡSAXParserʵ��
            SAXParser parser=factory.newSAXParser();
            // ����SAXParserHandler����
            SAXParserHandler handler=new SAXParserHandler();
            parser.parse("E:/Project/Java/study/src/xml/xml.xml",handler);
            System.out.println("����:"+handler.getBookList().size()+"����");
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
     * ��������xml�ļ��Ŀ�ʼ��ǩ
     * ����xmlԪ��
     * @param uri
     * @param localName
     * @param qName
     * @param attributes
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // ����DefaultHandler���startElement����
        super.startElement(uri,localName,qName,attributes);
        // ��ʼ����booksԪ������
        if (qName.equals("books")){
            book=new Book();// ����һ��Book����
            bookIndex++;
            System.out.println("-----------��ʼ������"+bookIndex+"���������---------");
            // ��֪booksԪ�������Ե�����,�����������ƻ�ȡ����ֵ
//            String value=attributes.getValue("id");
//            System.out.println("����ֵΪ:"+value);
            // ��֪��books�����������Լ�����
            int num=attributes.getLength();// ��ȡ���Ը���
            for (int i=0;i<num;i++){
                System.out.print("booksԪ�صĵ�"+(i+1)+"����������:"+
                        attributes.getQName(i));
                System.out.println("---����ֵ��:"+attributes.getValue(i));
                if (attributes.getQName(i).equals("id")){
                    book.setId(attributes.getValue(i));// ����ֵ
                }
            }
        }else if (!qName.equals("books")&&!qName.equals("book")){
            System.out.print("�ڵ�����:"+qName+"---");
        }
    }

    /**
     * ��������xml�ļ��Ľ�����ǩ
     * @param uri
     * @param localName
     * @param qName
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // ����DefaultHandler���startElement����
        super.endElement(uri, localName, qName);
        // �ж��Ƿ����һ�����Ѿ���������
        if(qName.equals("books")){
            bookList.add(book);
            book=null;
            System.out.println("-----------����������"+bookIndex+"���������-----------\n");
        }else if (qName.equals("name")){
            book.setName(value);// �ӽڵ�ֵ
        }else if (qName.equals("nianling")){
            book.setNianling(value);// �ӽڵ�ֵ
        }else if (qName.equals("shenfenzheng")){
            book.setShenfenzheng(value);// �ӽڵ�ֵ
        }
    }

    /**
     * ������ʶ������ʼ
     * @throws SAXException
     */
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("SAX������ʼ");
    }

    /**
     * ������ʶ��������
     * @throws SAXException
     */
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("SAX��������");
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

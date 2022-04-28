package ������.�߼�������.������;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.SortedMap;

/**
 * JAVA�ַ�������
 */
public class CharsetTest {
    public static void main(String[] args) throws CharacterCodingException {
        // ��ȡJAVA֧�ֵ�ȫ���ַ���
        SortedMap<String, Charset> map = Charset.availableCharsets();
        // ѭ������
        for (String s:map.keySet()){
            // ����ַ���
            System.out.println(s);
        }

        // ͨ�����䴴������GKB������ַ���ʵ��
        Charset charset = Charset.forName("GBK");
        // �õ�������
        CharsetEncoder encoder = charset.newEncoder();
        // �����ַ�������
        CharBuffer charBuffer = CharBuffer.allocate(20);
        // ��������
        charBuffer.put("����Դ");
        // ��ת������
        charBuffer.flip();
        // ת��Ϊ�ֽڻ�����
        ByteBuffer byteBuffer = encoder.encode(charBuffer);
        // ѭ������
        for (int i=0;i<byteBuffer.limit();i++){
            // ������ֶ�Ӧ���ֽ�
            System.out.println(byteBuffer.get(i));
        }
        
        // �õ�������
        CharsetDecoder decoder = charset.newDecoder();
        // ���ֽڻ��������ݲ�ͬ�ı����ʽ(������)ת��Ϊ�ַ�
        CharBuffer charBuffer2 = decoder.decode(byteBuffer);
        // ����������
        for (int i=0;i<charBuffer2.limit();i++){
            // ����ַ�������
            System.out.print(charBuffer2.get(i));
        }
    }
}

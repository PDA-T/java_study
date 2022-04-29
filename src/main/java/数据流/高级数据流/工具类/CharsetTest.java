package 数据流.高级数据流.工具类;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.SortedMap;

/**
 * JAVA字符集编码
 */
public class CharsetTest {
    public static void main(String[] args) throws CharacterCodingException {
        // 获取JAVA支持的全部字符集
        SortedMap<String, Charset> map = Charset.availableCharsets();
        // 循环遍历
        for (String s:map.keySet()){
            // 输出字符集
            System.out.println(s);
        }

        // 通过反射创建基于GKB编码的字符集实例
        Charset charset = Charset.forName("GBK");
        // 拿到编码器
        CharsetEncoder encoder = charset.newEncoder();
        // 创建字符缓冲区
        CharBuffer charBuffer = CharBuffer.allocate(20);
        // 放入数据
        charBuffer.put("陈清源");
        // 反转缓冲区
        charBuffer.flip();
        // 转换为字节缓冲区
        ByteBuffer byteBuffer = encoder.encode(charBuffer);
        // 循环遍历
        for (int i=0;i<byteBuffer.limit();i++){
            // 输出汉字对应的字节
            System.out.println(byteBuffer.get(i));
        }
        
        // 拿到解析器
        CharsetDecoder decoder = charset.newDecoder();
        // 把字节缓冲区根据不同的编码格式(解析器)转换为字符
        CharBuffer charBuffer2 = decoder.decode(byteBuffer);
        // 遍历缓冲区
        for (int i=0;i<charBuffer2.limit();i++){
            // 输出字符缓冲区
            System.out.print(charBuffer2.get(i));
        }
    }
}

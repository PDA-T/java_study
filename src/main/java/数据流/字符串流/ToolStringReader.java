package 数据流.字符串流;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;

/**
 * 一般使用工具类获取数据
 * 创建流标记器
 * 传入输入流
 */
public class ToolStringReader {
    public static void main(String[] args) throws IOException {
        // 创建字符串
        String s = "Google 1";
        // 创建字符串输入流
        StringReader sr = new StringReader(s);
        // 创建流标记器
        StreamTokenizer st = new StreamTokenizer(sr);
        // 记录字符串数字的个数
        int numCount = 0;
        // 记录字符串单词的个数
        int wordCount = 0;
        // nextToken方法调用后,st.ttype(流标记解析器里面的一个属性)就会有刚刚从流里读取的标记类型
        while (st.nextToken()!=StreamTokenizer.TT_EOF){// 读取到的标记不是流的末尾标记
            // 获取标记类型
            int flagType = st.ttype;
            // 如果这个标记等于数字
            if (flagType == StreamTokenizer.TT_NUMBER){
                // 拿出数字
                System.out.println(st.nval);
                // 数字个数加1
                numCount++;
                // 如果标记是单词
            }else if (flagType == StreamTokenizer.TT_WORD){
                // 拿出单词
                System.out.println(st.sval);
                // 单词个数加1
                wordCount++;
            }
        }
        System.out.println("整个字符串里的数字个数是:"+numCount);
        System.out.println("整个字符串里的单词个数是:"+wordCount);
    }
}

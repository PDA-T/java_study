package 数据流.字节数组流;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * 字节数组输出流
 */
public class outputStreamTest {
    public static void main(String[] args) throws IOException {
        // 定义一个数组,一个数占用一个字节
        byte[] b1 = new byte[]{1,2,3,4};
        // 创建数组输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 放数组里的数据到输出流
        baos.write(b1);
        // 创建一个新分配的字节数组,数组大小和当前输出流一样,内容为当前输出流的拷贝
        byte[] b2 = baos.toByteArray();
        // 输出b2值
        System.out.println(Arrays.toString(b2));
        // 乱码,因为是byte数据,无法编码为字符串
        System.out.println(baos.toString());
        // 创建文件输出流
        FileOutputStream fos = new FileOutputStream("D:/aaa.txt");
        // 把数组输出流换成其他输出流
        baos.writeTo(fos);
    }
}

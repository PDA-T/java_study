package 数据流.对象序列化;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import java.io.*;

/**
 * 对象序列化安全问题
 * 对象序列化文件可以反序列化,如果使用抓包软件就可以抓取到文件
 * 之后进行反序列化(不安全)
 * 加密后被抓包看不见内容,同时被修改也能检测到
 */
public class EncryptionObject {
    public static void main(String[] args) {
        /*
         * 创建文件输出流,对象输出流,文件输入流,对象输入流
         * 新版自动关闭流写法如果注释掉输出流只运行输入流会报错
         * 因为自动把需要的流关闭了
         * 推荐写常规手动关闭
         */
        try (OutputStream out = new FileOutputStream("D:/abc");
             ObjectOutputStream objout = new ObjectOutputStream(out);
             InputStream in = new FileInputStream("D:/abc");
             ObjectInputStream objin = new ObjectInputStream(in)){
            // 实例化序列化类对象
            PersonKey person = new PersonKey();
            // 设置类属性
            person.name = "zhangzheng";
            person.age = 18;
            // 创建密钥生成器类,传入加密算法名称
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
            // 生成密钥
            SecretKey encryptKey = keyGenerator.generateKey();
            // 创建加密器
            Cipher cipher = Cipher.getInstance("DESede");
            // 指定加密器工作模式
            cipher.init(Cipher.ENCRYPT_MODE,encryptKey);
            // 创建Key加密类
            SealedObject so = new SealedObject(person,cipher);
            // 序列化对象
            objout.writeObject(so);

            // 创建文件输出流
            OutputStream outKey = new FileOutputStream("D:/abcKey");
            // 封装对象输出流
            ObjectOutputStream objoutKey = new ObjectOutputStream(outKey);
            // 创建文件输入流
            InputStream inKey = new FileInputStream("D:/abcKey");
            // 封装对象输入流
            ObjectInputStream objinKey = new ObjectInputStream(inKey);
            // 反序列化,返回的是加密类,被抓包也无法打开
            SealedObject sors = (SealedObject) objinKey.readObject();

            // 创建文件输入流,拿取加密密钥
            InputStream getKeyin = new FileInputStream("D:/abcGeyKey");
            // 封装对象输入流
            ObjectInputStream objGetKeyin = new ObjectInputStream(getKeyin);
            // 获取密钥
            SecretKey openKey = (SecretKey) objGetKeyin.readObject();
            // 参数放入密钥,解密出对象
            PersonKey person2 =(PersonKey) sors.getObject(openKey);
            // 输出对象
            System.out.println(person2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
class PersonKey implements Serializable{
    public String name;
    public int age;

    @Override
    public String toString() {
        return "PersonKey{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
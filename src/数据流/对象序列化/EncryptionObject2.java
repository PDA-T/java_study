package 数据流.对象序列化;

import java.io.*;
import java.security.*;

/**
 * 对象序列化安全问题
 * 第二种加密写法,使用签名
 * 比第一种复杂
 */
public class EncryptionObject2 {
    public static void main(String[] args) {
        // 创建对象输出流
        ObjectOutputStream objout = null;
        // 创建对象输入流
        ObjectInputStream objin = null;
        // 创建对象输出流
        ObjectOutputStream objoutKey = null;
        // 创建对象输入流
        ObjectInputStream objGetKeyin = null;
        try {
            // 创建文件输出流
            OutputStream out = new FileOutputStream("D:/abc");
            // 封装文件输出流
            objout = new ObjectOutputStream(out);
            // 实例化序列化类对象
            PersonKey person = new PersonKey();
            // 设置类属性
            person.name = "zhangzheng";
            person.age = 18;
            // 密钥生成器,传入算法名称
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
            // 密钥的长度
            keyPairGenerator.initialize(1024);
            // 生成一对密钥(私钥,公钥)
            KeyPair keyPair = keyPairGenerator.genKeyPair();
            // 获取私钥
            PrivateKey privateKey = keyPair.getPrivate();
            // 获取公钥
            PublicKey publicKey = keyPair.getPublic();
            // 保存公钥
            objoutKey = new ObjectOutputStream(new FileOutputStream("D:/abc.txt"));
            objoutKey.writeObject(publicKey);

            // 创建签名引擎
            Signature signature = Signature.getInstance("DSA");
            // 创建数字签名加密类
            SignedObject so = new SignedObject(person,privateKey,signature);
            // 序列化
            objout.writeObject(so);

            // 文件输入流
            InputStream in = new FileInputStream("D:/abc");
            // 封装输入流
            objin = new ObjectInputStream(in);
            // 拿到加密类
            SignedObject so2 = (SignedObject)objin.readObject();
            // 文件输入流
            InputStream getKeyin = new FileInputStream("D:/abcKey");
            // 封装文件输入流
            objGetKeyin = new ObjectInputStream(getKeyin);
            // 拿到公共密钥
            PublicKey publicKey2 = (PublicKey)objGetKeyin.readObject();
            // 创建加密引擎
            Signature verifySignature = Signature.getInstance("DSA");
            // 判断对象是否被篡改,第二个参数传入和加密时一样的引擎算法
            if (so2.verify(publicKey2,verifySignature)){
                // 没被篡改,读取内容
                PersonKey personKey2 = (PersonKey) so2.getObject();
                // 输出
                System.out.println(personKey2);
            }else {
                // 内容被篡改
                System.out.println("内容被修改");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

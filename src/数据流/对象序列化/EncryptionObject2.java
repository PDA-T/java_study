package ������.�������л�;

import java.io.*;
import java.security.*;

/**
 * �������л���ȫ����
 * �ڶ��ּ���д��,ʹ��ǩ��
 * �ȵ�һ�ָ���
 */
public class EncryptionObject2 {
    public static void main(String[] args) {
        // �������������
        ObjectOutputStream objout = null;
        // ��������������
        ObjectInputStream objin = null;
        // �������������
        ObjectOutputStream objoutKey = null;
        // ��������������
        ObjectInputStream objGetKeyin = null;
        try {
            // �����ļ������
            OutputStream out = new FileOutputStream("D:/abc");
            // ��װ�ļ������
            objout = new ObjectOutputStream(out);
            // ʵ�������л������
            PersonKey person = new PersonKey();
            // ����������
            person.name = "zhangzheng";
            person.age = 18;
            // ��Կ������,�����㷨����
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
            // ��Կ�ĳ���
            keyPairGenerator.initialize(1024);
            // ����һ����Կ(˽Կ,��Կ)
            KeyPair keyPair = keyPairGenerator.genKeyPair();
            // ��ȡ˽Կ
            PrivateKey privateKey = keyPair.getPrivate();
            // ��ȡ��Կ
            PublicKey publicKey = keyPair.getPublic();
            // ���湫Կ
            objoutKey = new ObjectOutputStream(new FileOutputStream("D:/abc.txt"));
            objoutKey.writeObject(publicKey);

            // ����ǩ������
            Signature signature = Signature.getInstance("DSA");
            // ��������ǩ��������
            SignedObject so = new SignedObject(person,privateKey,signature);
            // ���л�
            objout.writeObject(so);

            // �ļ�������
            InputStream in = new FileInputStream("D:/abc");
            // ��װ������
            objin = new ObjectInputStream(in);
            // �õ�������
            SignedObject so2 = (SignedObject)objin.readObject();
            // �ļ�������
            InputStream getKeyin = new FileInputStream("D:/abcKey");
            // ��װ�ļ�������
            objGetKeyin = new ObjectInputStream(getKeyin);
            // �õ�������Կ
            PublicKey publicKey2 = (PublicKey)objGetKeyin.readObject();
            // ������������
            Signature verifySignature = Signature.getInstance("DSA");
            // �ж϶����Ƿ񱻴۸�,�ڶ�����������ͼ���ʱһ���������㷨
            if (so2.verify(publicKey2,verifySignature)){
                // û���۸�,��ȡ����
                PersonKey personKey2 = (PersonKey) so2.getObject();
                // ���
                System.out.println(personKey2);
            }else {
                // ���ݱ��۸�
                System.out.println("���ݱ��޸�");
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

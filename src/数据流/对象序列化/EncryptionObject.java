package ������.�������л�;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import java.io.*;

/**
 * �������л���ȫ����
 * �������л��ļ����Է����л�,���ʹ��ץ������Ϳ���ץȡ���ļ�
 * ֮����з����л�(����ȫ)
 * ���ܺ�ץ������������,ͬʱ���޸�Ҳ�ܼ�⵽
 */
public class EncryptionObject {
    public static void main(String[] args) {
        /*
         * �����ļ������,���������,�ļ�������,����������
         * �°��Զ��ر���д�����ע�͵������ֻ�����������ᱨ��
         * ��Ϊ�Զ�����Ҫ�����ر���
         * �Ƽ�д�����ֶ��ر�
         */
        try (OutputStream out = new FileOutputStream("D:/abc");
             ObjectOutputStream objout = new ObjectOutputStream(out);
             InputStream in = new FileInputStream("D:/abc");
             ObjectInputStream objin = new ObjectInputStream(in)){
            // ʵ�������л������
            PersonKey person = new PersonKey();
            // ����������
            person.name = "zhangzheng";
            person.age = 18;
            // ������Կ��������,��������㷨����
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
            // ������Կ
            SecretKey encryptKey = keyGenerator.generateKey();
            // ����������
            Cipher cipher = Cipher.getInstance("DESede");
            // ָ������������ģʽ
            cipher.init(Cipher.ENCRYPT_MODE,encryptKey);
            // ����Key������
            SealedObject so = new SealedObject(person,cipher);
            // ���л�����
            objout.writeObject(so);

            // �����ļ������
            OutputStream outKey = new FileOutputStream("D:/abcKey");
            // ��װ���������
            ObjectOutputStream objoutKey = new ObjectOutputStream(outKey);
            // �����ļ�������
            InputStream inKey = new FileInputStream("D:/abcKey");
            // ��װ����������
            ObjectInputStream objinKey = new ObjectInputStream(inKey);
            // �����л�,���ص��Ǽ�����,��ץ��Ҳ�޷���
            SealedObject sors = (SealedObject) objinKey.readObject();

            // �����ļ�������,��ȡ������Կ
            InputStream getKeyin = new FileInputStream("D:/abcGeyKey");
            // ��װ����������
            ObjectInputStream objGetKeyin = new ObjectInputStream(getKeyin);
            // ��ȡ��Կ
            SecretKey openKey = (SecretKey) objGetKeyin.readObject();
            // ����������Կ,���ܳ�����
            PersonKey person2 =(PersonKey) sors.getObject(openKey);
            // �������
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
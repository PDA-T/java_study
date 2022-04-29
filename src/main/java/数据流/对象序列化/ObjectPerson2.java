package ������.�������л�;

import java.io.*;

/**
 * �ڶ��ֶ������л��ķ���
 */
public class ObjectPerson2 {
    public static void main(String[] args) {
        // �������������
        ObjectOutputStream objout = null;
        try {
            // �����ļ������
            OutputStream out = new FileOutputStream("D:/abc.asve");
            // ��װ��Ϊ������
            objout = new ObjectOutputStream(out);
            // ʵ��������
            Man man = new Man("zhangzheng",55);
            // ���л�����
            objout.writeObject(man);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (objout!=null){
                try {
                    // �ر���
                    objout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // ��������������
        ObjectInputStream objis = null;
        try {
            // �����ļ�������
            InputStream is = new FileInputStream("D:/abc.save");
            // ��װΪ����������
            objis = new ObjectInputStream(is);
            // �����л�
            Man man = (Man)objis.readObject();
            // �������
            System.out.println(man);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objis!=null){
                try {
                    // �ر���
                    objis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

/**
 * ʵ�ֵڶ������л��ӿ�
 */
class Man implements Externalizable{
    private static final long serialVersionUID = -2978508319858955465L;
    public String name;
    public int age;

    public Man(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public Man(){}

    /**
     * ���л�
     * @param out
     * @throws IOException
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        // Ҫ�ֶ�д��Ҫ�����л�������
        out.writeObject(name);
        out.writeObject(age);
    }

    /**
     * �����л�
     * @param in
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        // Ҫ�ֶ�д��Ҫ�������л�������,Ҫ�����л���˳���Ӧ
        name = (String) in.readObject();
        age = in.readInt();
    }

    @Override
    public String toString() {
        return "Man{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

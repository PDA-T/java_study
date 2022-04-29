package 数据流.对象序列化;

import java.io.*;

/**
 * 第二种对象序列化的方法
 */
public class ObjectPerson2 {
    public static void main(String[] args) {
        // 创建对象输出流
        ObjectOutputStream objout = null;
        try {
            // 创建文件输出流
            OutputStream out = new FileOutputStream("D:/abc.asve");
            // 封装成为对象流
            objout = new ObjectOutputStream(out);
            // 实例化对象
            Man man = new Man("zhangzheng",55);
            // 序列化对象
            objout.writeObject(man);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (objout!=null){
                try {
                    // 关闭流
                    objout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // 创建对象输入流
        ObjectInputStream objis = null;
        try {
            // 创建文件输入流
            InputStream is = new FileInputStream("D:/abc.save");
            // 封装为对象输入流
            objis = new ObjectInputStream(is);
            // 反序列化
            Man man = (Man)objis.readObject();
            // 输出对象
            System.out.println(man);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objis!=null){
                try {
                    // 关闭流
                    objis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

/**
 * 实现第二种序列化接口
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
     * 序列化
     * @param out
     * @throws IOException
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        // 要手动写需要被序列化的属性
        out.writeObject(name);
        out.writeObject(age);
    }

    /**
     * 反序列化
     * @param in
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        // 要手动写需要被反序列化的属性,要和序列化的顺序对应
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

package 异常;

public class ChainTest {
    public static void main(String[] args) {
        ChainTest ch=new ChainTest();// 实例化本类
        try {// 捕捉异常
            ch.test2();// 调用类方法
        }catch (Exception e){// 处理异常
            e.printStackTrace();// 打印红色异常
        }
    }
    public void test() throws DrunkException{// 创建方法抛出自定义异常类
        throw new DrunkException("Exc");// 抛出实例化自定义异常类传入有参构造方法
    }
    public void test2(){// 创建方法
        try {// 捕捉异常
            test();// 调用方法
        } catch (DrunkException e) {// 处理异常
            RuntimeException Exc=new RuntimeException("Runtime");// 实例化运行时异常类
            Exc.initCause(e);// 对异常进行包装,捕捉原始异常
            throw Exc;// 抛出异常对象
        }
    }
}

package 多态.方法重写;

/**
 * 银行类
 */
public class Bank {
    public static void main(String[] args) {
        // 无法实例化抽象类,父类实例化子类,如果调用的方法父类子类都有优先使用子类
        Service service=new CashDesk();// 父类实例化子类
        service.deposit(300);// 调用重写方法
        Service service2=new ATM();// 父类实例化子类
        service2.deposit(100);// 调用重写方法
    }
}
/**
 * 业务类
 */
abstract class Service{
    public abstract String deposit(double money);// 抽象方法必须声明在抽象类
}
/**
 * 柜台类
 */
class CashDesk extends Service{
    @Override
    public String deposit(double money) {// 重写抽象方法
        System.out.println("柜台存款:"+money);// 打印提示
        return "柜台类";// 返回类型
    }
}
/**
 * ATM机类
 */
class ATM extends Service{
    @Override
    public String deposit(double money) {// 重写抽象方法
        System.out.println("ATM存款:"+money);// 打印提示
        return "ATM类";// 返回类型
    }
}

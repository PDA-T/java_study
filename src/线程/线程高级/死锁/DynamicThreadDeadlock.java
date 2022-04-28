package 线程.线程高级.死锁;

/**
 * 动态锁顺序死锁
 */
public class DynamicThreadDeadlock {
    public static void main(String[] args) {
        DeadLockDemo2 dl = new DeadLockDemo2();
        new Thread(dl,"线程1").start();
        new Thread(dl,"线程2").start();
    }
}
class DeadLockDemo2 implements Runnable{
    // 初始化两个账户
    Account a = new Account("A",1000);
    Account b = new Account("B",1000);
    @Override
    public void run() {
        // 执行转账方法
        transferMoney(a,b,100);
        transferMoney(b,a,100);
    }

    /**
     * 创建转账方法
     * @param fromAccount
     * @param toAccount
     * @param money
     */
    public void transferMoney(Account fromAccount,Account toAccount,int money){
        // 拿到转出账户的锁(保护转出账户)
        synchronized (fromAccount){
            System.out.println("线程"+Thread.currentThread().getName()+"得到锁"+fromAccount.getName());
            // 拿到转入账户的锁(保护转入账户)
            synchronized (toAccount){
                System.out.println("线程"+Thread.currentThread().getName()+"得到锁"+toAccount.getName());
                // 判断转出账户的余额是否足够
                if (fromAccount.getMoney() < money){
                    // 输出余额不足
                    System.out.println("余额不足");
                }else {
                    // 进行转账,转出账户扣钱
                    fromAccount.setMoney(fromAccount.getMoney()-money);
                    // 转入账户加钱
                    toAccount.setMoney(toAccount.getMoney()+money);
                    // 输出两个账户的余额
                    System.out.println("转账后:"+fromAccount.getName()+"有"+fromAccount.getMoney());
                    System.out.println("转账后:"+toAccount.getName()+"有"+toAccount.getMoney());
                }
            }
        }
    }
}
/**
 * 创建账户类
 */
class Account{
    private String name;
    private int money;
    // 初始化构造器
    public Account(String name,int money){
        this.name=name;
        this.money=money;
    }
    public String getName() {
        return name;
    }

    public Account setName(String name) {
        this.name = name;
        return this;
    }

    public int getMoney() {
        return money;
    }

    public Account setMoney(int money) {
        this.money = money;
        return this;
    }
}
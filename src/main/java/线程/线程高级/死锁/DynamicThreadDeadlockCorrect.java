package 线程.线程高级.死锁;

/**
 * 动态锁顺序死锁解决方法
 */
public class DynamicThreadDeadlockCorrect {
    public static void main(String[] args) {
        DeadLockDemo2Correct dl = new DeadLockDemo2Correct();
        new Thread(dl,"线程1").start();
        new Thread(dl,"线程2").start();
    }
}
class DeadLockDemo2Correct implements Runnable{
    // 初始化两个账户
    Account2 a = new Account2("A",1000);
    Account2 b = new Account2("B",1000);
    // 加一把锁
    private Object jiasuo = new Object();
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
    public void transferMoney(Account2 fromAccount,Account2 toAccount,int money){
        // 拿到锁的hash值
        int faHash = System.identityHashCode(fromAccount);
        int taHash = System.identityHashCode(toAccount);
        /**
         * 由于代码过长(重复过多)
         * 可以在方法里面创建一个内部类来封装重复的代码
         */
        class Helper{
            public void transfer(){
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
        // 判断hash值大小
        if (faHash > taHash){
            // 拿到转出账户的锁(保护转出账户)
            synchronized (fromAccount){
                System.out.println("线程"+Thread.currentThread().getName()+"得到锁"+fromAccount.getName());
                // 拿到转入账户的锁(保护转入账户)
                synchronized (toAccount){
                    // 调用内部类的方法
                    new Helper().transfer();
                }
            }
        }else if (faHash < taHash){
            // 拿到转出账户的锁(保护转出账户)
            synchronized (toAccount){
                System.out.println("线程"+Thread.currentThread().getName()+"得到锁"+fromAccount.getName());
                // 拿到转入账户的锁(保护转入账户)
                synchronized (fromAccount){
                    // 调用内部类的方法
                    new Helper().transfer();
                }
            }
            // 如果两个锁的Hash值相等执行(几乎不可能)
        }else {
            // 添加第三把锁
            synchronized (jiasuo){
                // 拿到转出账户的锁(保护转出账户)
                synchronized (fromAccount){
                    System.out.println("线程"+Thread.currentThread().getName()+"得到锁"+fromAccount.getName());
                    // 拿到转入账户的锁(保护转入账户)
                    synchronized (toAccount){
                        // 调用内部类的方法
                        new Helper().transfer();
                    }
                }
            }
        }
    }
}
/**
 * 创建账户类
 */
class Account2{
    private String name;
    private int money;
    // 初始化构造器
    public Account2(String name,int money){
        this.name=name;
        this.money=money;
    }
    public String getName() {
        return name;
    }

    public Account2 setName(String name) {
        this.name = name;
        return this;
    }

    public int getMoney() {
        return money;
    }

    public Account2 setMoney(int money) {
        this.money = money;
        return this;
    }
}

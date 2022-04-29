package �߳�.�̸߳߼�.����;

/**
 * ��̬��˳������
 */
public class DynamicThreadDeadlock {
    public static void main(String[] args) {
        DeadLockDemo2 dl = new DeadLockDemo2();
        new Thread(dl,"�߳�1").start();
        new Thread(dl,"�߳�2").start();
    }
}
class DeadLockDemo2 implements Runnable{
    // ��ʼ�������˻�
    Account a = new Account("A",1000);
    Account b = new Account("B",1000);
    @Override
    public void run() {
        // ִ��ת�˷���
        transferMoney(a,b,100);
        transferMoney(b,a,100);
    }

    /**
     * ����ת�˷���
     * @param fromAccount
     * @param toAccount
     * @param money
     */
    public void transferMoney(Account fromAccount,Account toAccount,int money){
        // �õ�ת���˻�����(����ת���˻�)
        synchronized (fromAccount){
            System.out.println("�߳�"+Thread.currentThread().getName()+"�õ���"+fromAccount.getName());
            // �õ�ת���˻�����(����ת���˻�)
            synchronized (toAccount){
                System.out.println("�߳�"+Thread.currentThread().getName()+"�õ���"+toAccount.getName());
                // �ж�ת���˻�������Ƿ��㹻
                if (fromAccount.getMoney() < money){
                    // �������
                    System.out.println("����");
                }else {
                    // ����ת��,ת���˻���Ǯ
                    fromAccount.setMoney(fromAccount.getMoney()-money);
                    // ת���˻���Ǯ
                    toAccount.setMoney(toAccount.getMoney()+money);
                    // ��������˻������
                    System.out.println("ת�˺�:"+fromAccount.getName()+"��"+fromAccount.getMoney());
                    System.out.println("ת�˺�:"+toAccount.getName()+"��"+toAccount.getMoney());
                }
            }
        }
    }
}
/**
 * �����˻���
 */
class Account{
    private String name;
    private int money;
    // ��ʼ��������
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
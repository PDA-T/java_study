package �߳�.�̸߳߼�.����;

/**
 * ��̬��˳�������������
 */
public class DynamicThreadDeadlockCorrect {
    public static void main(String[] args) {
        DeadLockDemo2Correct dl = new DeadLockDemo2Correct();
        new Thread(dl,"�߳�1").start();
        new Thread(dl,"�߳�2").start();
    }
}
class DeadLockDemo2Correct implements Runnable{
    // ��ʼ�������˻�
    Account2 a = new Account2("A",1000);
    Account2 b = new Account2("B",1000);
    // ��һ����
    private Object jiasuo = new Object();
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
    public void transferMoney(Account2 fromAccount,Account2 toAccount,int money){
        // �õ�����hashֵ
        int faHash = System.identityHashCode(fromAccount);
        int taHash = System.identityHashCode(toAccount);
        /**
         * ���ڴ������(�ظ�����)
         * �����ڷ������洴��һ���ڲ�������װ�ظ��Ĵ���
         */
        class Helper{
            public void transfer(){
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
        // �ж�hashֵ��С
        if (faHash > taHash){
            // �õ�ת���˻�����(����ת���˻�)
            synchronized (fromAccount){
                System.out.println("�߳�"+Thread.currentThread().getName()+"�õ���"+fromAccount.getName());
                // �õ�ת���˻�����(����ת���˻�)
                synchronized (toAccount){
                    // �����ڲ���ķ���
                    new Helper().transfer();
                }
            }
        }else if (faHash < taHash){
            // �õ�ת���˻�����(����ת���˻�)
            synchronized (toAccount){
                System.out.println("�߳�"+Thread.currentThread().getName()+"�õ���"+fromAccount.getName());
                // �õ�ת���˻�����(����ת���˻�)
                synchronized (fromAccount){
                    // �����ڲ���ķ���
                    new Helper().transfer();
                }
            }
            // �����������Hashֵ���ִ��(����������)
        }else {
            // ��ӵ�������
            synchronized (jiasuo){
                // �õ�ת���˻�����(����ת���˻�)
                synchronized (fromAccount){
                    System.out.println("�߳�"+Thread.currentThread().getName()+"�õ���"+fromAccount.getName());
                    // �õ�ת���˻�����(����ת���˻�)
                    synchronized (toAccount){
                        // �����ڲ���ķ���
                        new Helper().transfer();
                    }
                }
            }
        }
    }
}
/**
 * �����˻���
 */
class Account2{
    private String name;
    private int money;
    // ��ʼ��������
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

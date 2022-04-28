package ��̬.������д;

/**
 * ������
 */
public class Bank {
    public static void main(String[] args) {
        // �޷�ʵ����������,����ʵ��������,������õķ����������඼������ʹ������
        Service service=new CashDesk();// ����ʵ��������
        service.deposit(300);// ������д����
        Service service2=new ATM();// ����ʵ��������
        service2.deposit(100);// ������д����
    }
}
/**
 * ҵ����
 */
abstract class Service{
    public abstract String deposit(double money);// ���󷽷����������ڳ�����
}
/**
 * ��̨��
 */
class CashDesk extends Service{
    @Override
    public String deposit(double money) {// ��д���󷽷�
        System.out.println("��̨���:"+money);// ��ӡ��ʾ
        return "��̨��";// ��������
    }
}
/**
 * ATM����
 */
class ATM extends Service{
    @Override
    public String deposit(double money) {// ��д���󷽷�
        System.out.println("ATM���:"+money);// ��ӡ��ʾ
        return "ATM��";// ��������
    }
}

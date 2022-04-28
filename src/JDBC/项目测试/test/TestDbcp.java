package JDBC.��Ŀ����.test;

import JDBC.��Ŀ����.dao.AccountDao;
import JDBC.��Ŀ����.model.Account;

import java.util.Date;

public class TestDbcp {
    public static void main(String[] args) {
        Date a=new Date();
        trans();
        Date b=new Date();
        System.out.println(b.getTime()-a.getTime());
        // ͨ��DBCP���ӳط�ʽ�������ݿ�
        Date c=new Date();
        transByDbcp();
        Date d=new Date();
        System.out.println(d.getTime()-c.getTime());
    }
    public static void trans(){
        AccountDao accountDao=new AccountDao();
        Account from=null;
        from=accountDao.get(1);
        System.out.println(from.toString());
    }
    public static void transByDbcp(){
        AccountDao accountDao=new AccountDao();
        Account from=null;
        from=accountDao.getByDbcpConn(1);
        System.out.println(from.toString());
    }
}

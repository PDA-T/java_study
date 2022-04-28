package JDBC.项目测试.test;

import JDBC.项目测试.dao.AccountDao;
import JDBC.项目测试.model.Account;

import java.util.Date;

public class TestDbcp {
    public static void main(String[] args) {
        Date a=new Date();
        trans();
        Date b=new Date();
        System.out.println(b.getTime()-a.getTime());
        // 通过DBCP连接池方式操作数据库
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

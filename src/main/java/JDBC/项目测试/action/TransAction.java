package JDBC.项目测试.action;

import JDBC.项目测试.dao.AccountDao;
import JDBC.项目测试.model.Account;
import JDBC.项目测试.service.TransService;

public class TransAction {
    public static void main(String[] args) {
        String res=trans();
        System.out.println(res);
    }
    public static String trans(){
        AccountDao accountDao=new AccountDao();
        TransService transService=new TransService();
        Account from=null;
        Account to=null;
        from=accountDao.get(1);
        to=accountDao.get(2);
        return transService.trans(from,to,10d);
    }
}

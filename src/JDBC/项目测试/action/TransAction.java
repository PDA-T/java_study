package JDBC.��Ŀ����.action;

import JDBC.��Ŀ����.dao.AccountDao;
import JDBC.��Ŀ����.model.Account;
import JDBC.��Ŀ����.service.TransService;

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

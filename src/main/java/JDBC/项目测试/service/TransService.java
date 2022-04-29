package JDBC.��Ŀ����.service;

import JDBC.��Ŀ����.dao.AccountDao;
import JDBC.��Ŀ����.dao.TransInfoDao;
import JDBC.��Ŀ����.db.DBUtil;
import JDBC.��Ŀ����.model.Account;
import JDBC.��Ŀ����.model.Transinfo;

import java.sql.Connection;
import java.sql.SQLException;

public class TransService {
    /**
     * û���������,Ĭ���ύ����
     * @param from �տ
     * @param to ���
     * @param amount ת�����
     * @return
     */
    public String trans(Account from,Account to,Double amount){
        AccountDao accountDao=new AccountDao();
        TransInfoDao transInfoDao=new TransInfoDao();
        from.setAmount(from.getAmount()+amount);// �տ����
        accountDao.update(from);// �������ݿ�
        to.setAmount(to.getAmount()-amount);// �����ȥ
        accountDao.update(to);// �������ݿ�
        Transinfo info=new Transinfo();// ��¼������Ϣ
        info.setSourceAccount(from.getAccount());
        info.setSourceId(from.getId());
        info.setDesinationAccount(to.getAccount());
        info.setDestinationId(to.getId());
        info.setAmount(amount);
        transInfoDao.insert(info);// ��ӽ�����Ϣ
        return "sccess";
    }

    /**
     * ���������,�ֶ��ύ����,��ֹ��;�������ʽ��쳣
     * @param from
     * @param to
     * @param amount
     * @return
     * @throws SQLException
     */
    public String transaction(Account from,Account to,Double amount) throws SQLException {
        Connection conn= DBUtil.getConnection();
        try {
            conn.setAutoCommit(false);// �ر��¼��Զ��ύ
            AccountDao accountDao=new AccountDao();
            TransInfoDao transInfoDao=new TransInfoDao();
            from.setAmount(from.getAmount()+amount);// �տ����
            accountDao.update(from);// �������ݿ�
            to.setAmount(to.getAmount()-amount);// �����ȥ
            accountDao.update(to);// �������ݿ�
            Transinfo info=new Transinfo();// ��¼������Ϣ
            info.setSourceAccount(from.getAccount());
            info.setSourceId(from.getId());
            info.setDesinationAccount(to.getAccount());
            info.setDestinationId(to.getId());
            info.setAmount(amount);
            transInfoDao.insert(info);// ��ӽ�����Ϣ
            conn.commit();// �ֶ��ύ�¼�
            return "sccess";
        } catch (SQLException e) {
            conn.rollback();// �ع��¼�
            e.printStackTrace();
            return "fail";
        }
    }
}

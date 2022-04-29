package JDBC.项目测试.service;

import JDBC.项目测试.dao.AccountDao;
import JDBC.项目测试.dao.TransInfoDao;
import JDBC.项目测试.db.DBUtil;
import JDBC.项目测试.model.Account;
import JDBC.项目测试.model.Transinfo;

import java.sql.Connection;
import java.sql.SQLException;

public class TransService {
    /**
     * 没有事务管理,默认提交数据
     * @param from 收款方
     * @param to 付款方
     * @param amount 转出金额
     * @return
     */
    public String trans(Account from,Account to,Double amount){
        AccountDao accountDao=new AccountDao();
        TransInfoDao transInfoDao=new TransInfoDao();
        from.setAmount(from.getAmount()+amount);// 收款方加入
        accountDao.update(from);// 更新数据库
        to.setAmount(to.getAmount()-amount);// 付款方减去
        accountDao.update(to);// 更新数据库
        Transinfo info=new Transinfo();// 记录交易信息
        info.setSourceAccount(from.getAccount());
        info.setSourceId(from.getId());
        info.setDesinationAccount(to.getAccount());
        info.setDestinationId(to.getId());
        info.setAmount(amount);
        transInfoDao.insert(info);// 添加交易信息
        return "sccess";
    }

    /**
     * 有事务管理,手动提交数据,防止中途报错导致资金异常
     * @param from
     * @param to
     * @param amount
     * @return
     * @throws SQLException
     */
    public String transaction(Account from,Account to,Double amount) throws SQLException {
        Connection conn= DBUtil.getConnection();
        try {
            conn.setAutoCommit(false);// 关闭事件自动提交
            AccountDao accountDao=new AccountDao();
            TransInfoDao transInfoDao=new TransInfoDao();
            from.setAmount(from.getAmount()+amount);// 收款方加入
            accountDao.update(from);// 更新数据库
            to.setAmount(to.getAmount()-amount);// 付款方减去
            accountDao.update(to);// 更新数据库
            Transinfo info=new Transinfo();// 记录交易信息
            info.setSourceAccount(from.getAccount());
            info.setSourceId(from.getId());
            info.setDesinationAccount(to.getAccount());
            info.setDestinationId(to.getId());
            info.setAmount(amount);
            transInfoDao.insert(info);// 添加交易信息
            conn.commit();// 手动提交事件
            return "sccess";
        } catch (SQLException e) {
            conn.rollback();// 回滚事件
            e.printStackTrace();
            return "fail";
        }
    }
}

package JDBC.项目测试.dao;

import JDBC.项目测试.db.DBCPUtil;
import JDBC.项目测试.db.DBUtil;
import JDBC.项目测试.model.Account;
import JDBC.项目测试.model.Goddess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 账户信息操作
 */
public class AccountDao {
    /**
     * 增加
     * @param account
     */
    public void insert(Account account){
        Connection conn= DBUtil.getConnection();// 获得数据库连接
        String sql=""+"insert into account_info "+"(account,amount) values (?,?)";
        try {
            PreparedStatement ptmt=conn.prepareStatement(sql);// 编译sql语句
            ptmt.setString(1,account.getAccount());// 给?传递参数
            ptmt.setDouble(2,account.getAmount());// 给?传递参数
            ptmt.execute();// 执行sql语句
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改
     * @param account
     */
    public void update(Account account){
        Connection conn= DBUtil.getConnection();// 获得数据库连接
        String sql=""+"update account_info "+"set account=?,amount=? "+"where id=?";
        try {
            PreparedStatement ptmt=conn.prepareStatement(sql);// 编译sql语句
            ptmt.setString(1,account.getAccount());// 给?传递参数
            ptmt.setDouble(2,account.getAmount());// 给?传递参数
            ptmt.setInt(3,account.getId());// 给?传递参数
            ptmt.execute();// 执行sql语句
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除
     * @param account
     */
    public void delete(Account account){
        Connection conn= DBUtil.getConnection();// 获得数据库连接
        String sql=""+"delete from account_info "+"where id=?";
        try {
            PreparedStatement ptmt=conn.prepareStatement(sql);// 编译sql语句
            ptmt.setInt(1,account.getId());// 给?传递参数
            ptmt.execute();// 执行sql语句
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询
     * @param account
     */
    public List<Account> query(Account account){
        List<Account> result=new ArrayList<Account>();// 创建列表
        Connection conn= DBUtil.getConnection();// 获得数据库连接
        StringBuilder sb=new StringBuilder();// 可变的字符序列,可连接字符串
        sb.append("select * from account_info ");
        sb.append("where account like ?");
        try {
            PreparedStatement ptmt=conn.prepareStatement(sb.toString());// 编译sql语句
            ptmt.setString(1,"%"+account.getAccount()+"%");
            ResultSet rs=ptmt.executeQuery();// 创建结果集
            Account a=null;
            while (rs.next()){// 如果不为空进行循环
                a=new Account();
                a.setId(rs.getInt("id"));// 给类属性赋值
                a.setAccount(rs.getString("account"));// 给类属性赋值
                a.setAmount(rs.getDouble("amount"));// 给类属性赋值
                a.setCreateAt(rs.getDate("create_at"));// 给类属性赋值
                result.add(a);// 添加列表
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查询单个
     * @param id
     */
    public Account get(Integer id){
        Account a=null;// 创建类对象
        // 获得数据库连接
        Connection conn=DBUtil.getConnection();
        String sql=""+" select * from account_info"+" where id=? ";
        try {
            PreparedStatement ptmt=conn.prepareStatement(sql);// 编译sql语句
            ptmt.setInt(1,id);// 给?传递参数
            ResultSet rs=ptmt.executeQuery();// 执行查询sql语句,返回结果集
            while (rs.next()){// 如果结果集有数据开始循环
                a=new Account();// 实例化类对象
                a.setId(rs.getInt("id"));// 给类属性赋值
                a.setAccount(rs.getString("account"));// 给类属性赋值
                a.setAmount(rs.getDouble("amount"));// 给类属性赋值
                a.setCreateAt(rs.getDate("create_at"));// 给类属性赋值
            }
            return a;// 返回对象
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;// 返回对象
    }
    public Account getByDbcpConn(Integer id){
        DBCPUtil db=new DBCPUtil();
        Connection conn=db.getConn();
        Account a=null;// 创建类对象
        // 获得数据库连接
        String sql=""+" select * from account_info"+" where id=? ";
        try {
            PreparedStatement ptmt=conn.prepareStatement(sql);// 编译sql语句
            ptmt.setInt(1,id);// 给?传递参数
            ResultSet rs=ptmt.executeQuery();// 执行查询sql语句,返回结果集
            while (rs.next()){// 如果结果集有数据开始循环
                a=new Account();// 实例化类对象
                a.setId(rs.getInt("id"));// 给类属性赋值
                a.setAccount(rs.getString("account"));// 给类属性赋值
                a.setAmount(rs.getDouble("amount"));// 给类属性赋值
                a.setCreateAt(rs.getDate("create_at"));// 给类属性赋值
            }
            return a;// 返回对象
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;// 返回对象
    }
}

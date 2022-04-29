package JDBC.��Ŀ����.dao;

import JDBC.��Ŀ����.db.DBCPUtil;
import JDBC.��Ŀ����.db.DBUtil;
import JDBC.��Ŀ����.model.Account;
import JDBC.��Ŀ����.model.Goddess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * �˻���Ϣ����
 */
public class AccountDao {
    /**
     * ����
     * @param account
     */
    public void insert(Account account){
        Connection conn= DBUtil.getConnection();// ������ݿ�����
        String sql=""+"insert into account_info "+"(account,amount) values (?,?)";
        try {
            PreparedStatement ptmt=conn.prepareStatement(sql);// ����sql���
            ptmt.setString(1,account.getAccount());// ��?���ݲ���
            ptmt.setDouble(2,account.getAmount());// ��?���ݲ���
            ptmt.execute();// ִ��sql���
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * �޸�
     * @param account
     */
    public void update(Account account){
        Connection conn= DBUtil.getConnection();// ������ݿ�����
        String sql=""+"update account_info "+"set account=?,amount=? "+"where id=?";
        try {
            PreparedStatement ptmt=conn.prepareStatement(sql);// ����sql���
            ptmt.setString(1,account.getAccount());// ��?���ݲ���
            ptmt.setDouble(2,account.getAmount());// ��?���ݲ���
            ptmt.setInt(3,account.getId());// ��?���ݲ���
            ptmt.execute();// ִ��sql���
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * ɾ��
     * @param account
     */
    public void delete(Account account){
        Connection conn= DBUtil.getConnection();// ������ݿ�����
        String sql=""+"delete from account_info "+"where id=?";
        try {
            PreparedStatement ptmt=conn.prepareStatement(sql);// ����sql���
            ptmt.setInt(1,account.getId());// ��?���ݲ���
            ptmt.execute();// ִ��sql���
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * ��ѯ
     * @param account
     */
    public List<Account> query(Account account){
        List<Account> result=new ArrayList<Account>();// �����б�
        Connection conn= DBUtil.getConnection();// ������ݿ�����
        StringBuilder sb=new StringBuilder();// �ɱ���ַ�����,�������ַ���
        sb.append("select * from account_info ");
        sb.append("where account like ?");
        try {
            PreparedStatement ptmt=conn.prepareStatement(sb.toString());// ����sql���
            ptmt.setString(1,"%"+account.getAccount()+"%");
            ResultSet rs=ptmt.executeQuery();// ���������
            Account a=null;
            while (rs.next()){// �����Ϊ�ս���ѭ��
                a=new Account();
                a.setId(rs.getInt("id"));// �������Ը�ֵ
                a.setAccount(rs.getString("account"));// �������Ը�ֵ
                a.setAmount(rs.getDouble("amount"));// �������Ը�ֵ
                a.setCreateAt(rs.getDate("create_at"));// �������Ը�ֵ
                result.add(a);// ����б�
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * ��ѯ����
     * @param id
     */
    public Account get(Integer id){
        Account a=null;// ���������
        // ������ݿ�����
        Connection conn=DBUtil.getConnection();
        String sql=""+" select * from account_info"+" where id=? ";
        try {
            PreparedStatement ptmt=conn.prepareStatement(sql);// ����sql���
            ptmt.setInt(1,id);// ��?���ݲ���
            ResultSet rs=ptmt.executeQuery();// ִ�в�ѯsql���,���ؽ����
            while (rs.next()){// �������������ݿ�ʼѭ��
                a=new Account();// ʵ���������
                a.setId(rs.getInt("id"));// �������Ը�ֵ
                a.setAccount(rs.getString("account"));// �������Ը�ֵ
                a.setAmount(rs.getDouble("amount"));// �������Ը�ֵ
                a.setCreateAt(rs.getDate("create_at"));// �������Ը�ֵ
            }
            return a;// ���ض���
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;// ���ض���
    }
    public Account getByDbcpConn(Integer id){
        DBCPUtil db=new DBCPUtil();
        Connection conn=db.getConn();
        Account a=null;// ���������
        // ������ݿ�����
        String sql=""+" select * from account_info"+" where id=? ";
        try {
            PreparedStatement ptmt=conn.prepareStatement(sql);// ����sql���
            ptmt.setInt(1,id);// ��?���ݲ���
            ResultSet rs=ptmt.executeQuery();// ִ�в�ѯsql���,���ؽ����
            while (rs.next()){// �������������ݿ�ʼѭ��
                a=new Account();// ʵ���������
                a.setId(rs.getInt("id"));// �������Ը�ֵ
                a.setAccount(rs.getString("account"));// �������Ը�ֵ
                a.setAmount(rs.getDouble("amount"));// �������Ը�ֵ
                a.setCreateAt(rs.getDate("create_at"));// �������Ը�ֵ
            }
            return a;// ���ض���
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;// ���ض���
    }
}

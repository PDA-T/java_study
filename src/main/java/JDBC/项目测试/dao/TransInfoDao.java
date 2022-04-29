package JDBC.��Ŀ����.dao;

import JDBC.��Ŀ����.db.DBUtil;
import JDBC.��Ŀ����.model.Account;
import JDBC.��Ŀ����.model.Transinfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ������Ϣ����
 */
public class TransInfoDao {
    /**
     * ����
     * @param transinfo
     */
    public void insert(Transinfo transinfo){
        Connection conn=DBUtil.getConnection();// ������ݿ�����
        String sql=""+"insert into trans_info "
                +"(source_id,source_account,destination_id,destination_account,amount) "
                +"values (?,?,?,?,?)";
        try {
            PreparedStatement ptmt=conn.prepareStatement(sql);// ����sql
            ptmt.setInt(1,transinfo.getSourceId());// ��?���ݲ���
            ptmt.setString(2,transinfo.getSourceAccount());// ��?���ݲ���
            ptmt.setInt(3,transinfo.getDestinationId());// ��?���ݲ���
            ptmt.setString(4,transinfo.getDesinationAccount());// ��?���ݲ���
            ptmt.setDouble(5,transinfo.getAmount());// ��?���ݲ���
            ptmt.execute();// ִ��sql���
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * �޸�
     * @param transinfo
     */
    public void update(Transinfo transinfo){
        Connection conn=DBUtil.getConnection();// ������ݿ�����
        String sql=""+"update trans_info "+"set source_id=?,source_account=?" +
                ",destination_id=?,destination_account=?,amount=? " +"where id=?";
        try {
            PreparedStatement ptmt=conn.prepareStatement(sql);// ����sql
            ptmt.setInt(1,transinfo.getSourceId());// ��?���ݲ���
            ptmt.setString(2,transinfo.getSourceAccount());// ��?���ݲ���
            ptmt.setInt(3,transinfo.getDestinationId());// ��?���ݲ���
            ptmt.setString(4,transinfo.getDesinationAccount());// ��?���ݲ���
            ptmt.setDouble(5,transinfo.getAmount());// ��?���ݲ���
            ptmt.setInt(6,transinfo.getId());// ��?���ݲ���
            ptmt.execute();// ִ��sql���
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * ɾ��
     * @param transinfo
     */
    public void delete(Transinfo transinfo){
        Connection conn=DBUtil.getConnection();// ������ݿ�����
        String sql=""+"delete from trans_info "+"where id=?";
        try {
            PreparedStatement ptmt=conn.prepareStatement(sql);// ����sql
            ptmt.setInt(1,transinfo.getId());// ��?���ݲ���
            ptmt.execute();// ִ��sql���
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * ��ѯ
     * @param transinfo
     */
    public List<Transinfo> query(Transinfo transinfo){
        List<Transinfo> result=new ArrayList<Transinfo>();// �����б�
        Connection conn=DBUtil.getConnection();// ������ݿ�����
        StringBuilder sb=new StringBuilder();// �ɱ���ַ�����,�������ַ���
        sb.append("select * from trans_info ");
        sb.append("where account like ?");
        try {
            PreparedStatement ptmt=conn.prepareStatement(sb.toString());// ����sql
            // ��?���ݲ���
            ptmt.setString(1,"%"+transinfo.getSourceAccount()+"%");
            ResultSet rs=ptmt.executeQuery();// ���������
            Transinfo t=null;
            while (rs.next()){// �����Ϊ�ս���ѭ��
                t=new Transinfo();
                t.setId(rs.getInt("id"));// �������Ը�ֵ
                t.setSourceId(rs.getInt("source_id"));// �������Ը�ֵ
                // �������Ը�ֵ
                t.setSourceAccount(rs.getString("source_account"));
                // �������Ը�ֵ
                t.setDestinationId(rs.getInt("destination_id"));
                // �������Ը�ֵ
                t.setDesinationAccount(rs.getString("destination_account"));
                t.setAmount(rs.getDouble("amount"));// �������Ը�ֵ
                result.add(t);// ����б�
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}

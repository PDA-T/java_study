package JDBC.��Ŀ����.dao;

import JDBC.��Ŀ����.db.DBUtil;
import JDBC.��Ŀ����.model.Goddess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduceDao {
    public static Integer select_count(){
        Integer count=0;
        Connection conn= DBUtil.getConnection();// �������
        CallableStatement cs= null;
        try {
            cs = conn.prepareCall("CALL sp_select_count(?)");
            cs.registerOutParameter(1, Types.INTEGER);// ע���������
            cs.execute();// ִ�д洢����
            count=cs.getInt(1);// �����صĽ��:�����
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    /**
     * �вδ洢����
     * @param sp_name
     * @return
     */
    public static List<Goddess> select_filter(String sp_name){
        List<Goddess> result=new ArrayList<Goddess>();
        Connection conn= DBUtil.getConnection();// �������
        try {
            // ���CallableStatement
            CallableStatement cs=conn.prepareCall("CALL sp_select_filter(?)");
            cs.setString(1,sp_name);// ��?��ֵ
            cs.execute();// ִ�д洢����
            ResultSet rs=cs.getResultSet();// �����صĽ��:�����
            Goddess g;
            while (rs.next()){// ����
                g=new Goddess();
                g.setId(rs.getInt("id"));
                g.setUser_name(rs.getString("user_name"));
                g.setAge(rs.getInt("age"));
                g.setMobile(rs.getString("mobile"));
                result.add(g);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * �޲δ洢����
     */
    public static void select_nofilter(){
        Connection conn= DBUtil.getConnection();// �������
        try {
            // ���CallableStatement
            CallableStatement cs=conn.prepareCall("CALL sp_select_nofilter()");
            cs.execute();// ִ�д洢����
            ResultSet rs=cs.getResultSet();// �����صĽ��:�����
            while (rs.next()){// ����
                System.out.println(rs.getString("user_name")
                        +rs.getString("email")
                        +rs.getString("mobile"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

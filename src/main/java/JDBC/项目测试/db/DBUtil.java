package JDBC.��Ŀ����.db;

import java.sql.*;

/**
 * ������ͨ
 */
public class DBUtil {
    // ���ݿ�URL
    private static final String URL="jdbc:mysql://rm-t4n4141f6qw6o31n5yo.mysql.singapore.rds.aliyuncs.com/thread";
    // ���ݿ��û���
    private static final String USER="debug";
    // ���ݿ�����
    private static final String PASSWORD="PDA1580_0";
    // ���ݿ�����
    private static Connection conn=null;
    // ��̬����
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");// ��������
            // �������ݿ�
            conn=DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * ��ȡ����
     * @return ��������
     */
    public static Connection getConnection(){
        return conn;
    }
    /**
     * ������
     * @param args
     */
    public static void main(String[] args) {
        try {
            // ͨ�����ݿ����Ӳٿ����ݿ�
            Statement stmt=conn.createStatement();
            // ���Ͳ�ѯ���
            ResultSet rs=stmt.executeQuery("select user_name,age from imooc_goddess");
            // ��������������ݷ���true
            while (rs.next()){
                // ��ӡ��ѯ������,get����Ҫ��ӡ����
                System.out.println(rs.getString("user_name")+","+rs.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
package JDBC;

import java.sql.*;

public class ���� {
    public static void main(String[] args) {
        Connection connection=null;// ����
        Statement statement=null;// �����ݿⷢ�����
        ResultSet resultSet=null;// ���صĽ����
        PreparedStatement paredStatement=null;// ��̬sql
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");// װ����
            String url="jdbc:mysql://rm-t4n4141f6qw6o31n5yo.mysql.singapore.rds.aliyuncs.com/thread";// ���ݿ���Ϣ
            connection=DriverManager.getConnection(url,"ordinary","PDA1580_0");// �������ݿ�
            statement=connection.createStatement();// ��ȡһ��statement
            resultSet=statement.executeQuery("show tables");// ��ѯ���б�
            while (resultSet.next()){
                String id=resultSet.getString(1);
                System.out.println(id);
            }
            resultSet=statement.executeQuery("select * from pw_user");// ��ѯ���
            while (resultSet.next()){
                System.out.println(resultSet.getString(2));// ��ѯ�ڶ���
            }

            /*int i=statement.executeUpdate("insert inout pw_user(uid) values(2)");
            System.out.println(i);*/

            paredStatement=connection.prepareStatement("select * from pw_user where username=?");// ��̬sql
            paredStatement.setString(1,"username");
            resultSet=paredStatement.executeQuery();// ��ѯ��������
            while (resultSet.next()){
                System.out.println(resultSet.getString(2));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if (resultSet!=null){
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}

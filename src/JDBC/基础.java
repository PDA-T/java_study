package JDBC;

import java.sql.*;

public class 基础 {
    public static void main(String[] args) {
        Connection connection=null;// 连接
        Statement statement=null;// 向数据库发送语句
        ResultSet resultSet=null;// 返回的结果集
        PreparedStatement paredStatement=null;// 动态sql
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");// 装载类
            String url="jdbc:mysql://rm-t4n4141f6qw6o31n5yo.mysql.singapore.rds.aliyuncs.com/thread";// 数据库信息
            connection=DriverManager.getConnection(url,"ordinary","PDA1580_0");// 连接数据库
            statement=connection.createStatement();// 获取一个statement
            resultSet=statement.executeQuery("show tables");// 查询所有表
            while (resultSet.next()){
                String id=resultSet.getString(1);
                System.out.println(id);
            }
            resultSet=statement.executeQuery("select * from pw_user");// 查询语句
            while (resultSet.next()){
                System.out.println(resultSet.getString(2));// 查询第二列
            }

            /*int i=statement.executeUpdate("insert inout pw_user(uid) values(2)");
            System.out.println(i);*/

            paredStatement=connection.prepareStatement("select * from pw_user where username=?");// 动态sql
            paredStatement.setString(1,"username");
            resultSet=paredStatement.executeQuery();// 查询表中数据
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

package JDBC.项目测试.db;

import java.sql.*;

/**
 * 测试连通
 */
public class DBUtil {
    // 数据库URL
    private static final String URL="jdbc:mysql://rm-t4n4141f6qw6o31n5yo.mysql.singapore.rds.aliyuncs.com/thread";
    // 数据库用户名
    private static final String USER="debug";
    // 数据库密码
    private static final String PASSWORD="PDA1580_0";
    // 数据库连接
    private static Connection conn=null;
    // 静态语句块
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");// 加载驱动
            // 连接数据库
            conn=DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 获取连接
     * @return 返回连接
     */
    public static Connection getConnection(){
        return conn;
    }
    /**
     * 主方法
     * @param args
     */
    public static void main(String[] args) {
        try {
            // 通过数据库连接操控数据库
            Statement stmt=conn.createStatement();
            // 发送查询语句
            ResultSet rs=stmt.executeQuery("select user_name,age from imooc_goddess");
            // 如果对象里有数据返回true
            while (rs.next()){
                // 打印查询的数据,get参数要打印的列
                System.out.println(rs.getString("user_name")+","+rs.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
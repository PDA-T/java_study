package JDBC.项目测试.dao;

import JDBC.项目测试.db.DBUtil;
import JDBC.项目测试.model.Goddess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduceDao {
    public static Integer select_count(){
        Integer count=0;
        Connection conn= DBUtil.getConnection();// 获得连接
        CallableStatement cs= null;
        try {
            cs = conn.prepareCall("CALL sp_select_count(?)");
            cs.registerOutParameter(1, Types.INTEGER);// 注册输出参数
            cs.execute();// 执行存储过程
            count=cs.getInt(1);// 处理返回的结果:结果集
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    /**
     * 有参存储过程
     * @param sp_name
     * @return
     */
    public static List<Goddess> select_filter(String sp_name){
        List<Goddess> result=new ArrayList<Goddess>();
        Connection conn= DBUtil.getConnection();// 获得连接
        try {
            // 获得CallableStatement
            CallableStatement cs=conn.prepareCall("CALL sp_select_filter(?)");
            cs.setString(1,sp_name);// 给?赋值
            cs.execute();// 执行存储过程
            ResultSet rs=cs.getResultSet();// 处理返回的结果:结果集
            Goddess g;
            while (rs.next()){// 遍历
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
     * 无参存储过程
     */
    public static void select_nofilter(){
        Connection conn= DBUtil.getConnection();// 获得连接
        try {
            // 获得CallableStatement
            CallableStatement cs=conn.prepareCall("CALL sp_select_nofilter()");
            cs.execute();// 执行存储过程
            ResultSet rs=cs.getResultSet();// 处理返回的结果:结果集
            while (rs.next()){// 遍历
                System.out.println(rs.getString("user_name")
                        +rs.getString("email")
                        +rs.getString("mobile"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

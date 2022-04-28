package JDBC.项目测试.dao;

import JDBC.项目测试.db.DBUtil;
import JDBC.项目测试.model.Account;
import JDBC.项目测试.model.Transinfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 交易信息操作
 */
public class TransInfoDao {
    /**
     * 增加
     * @param transinfo
     */
    public void insert(Transinfo transinfo){
        Connection conn=DBUtil.getConnection();// 获得数据库连接
        String sql=""+"insert into trans_info "
                +"(source_id,source_account,destination_id,destination_account,amount) "
                +"values (?,?,?,?,?)";
        try {
            PreparedStatement ptmt=conn.prepareStatement(sql);// 编译sql
            ptmt.setInt(1,transinfo.getSourceId());// 给?传递参数
            ptmt.setString(2,transinfo.getSourceAccount());// 给?传递参数
            ptmt.setInt(3,transinfo.getDestinationId());// 给?传递参数
            ptmt.setString(4,transinfo.getDesinationAccount());// 给?传递参数
            ptmt.setDouble(5,transinfo.getAmount());// 给?传递参数
            ptmt.execute();// 执行sql语句
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改
     * @param transinfo
     */
    public void update(Transinfo transinfo){
        Connection conn=DBUtil.getConnection();// 获得数据库连接
        String sql=""+"update trans_info "+"set source_id=?,source_account=?" +
                ",destination_id=?,destination_account=?,amount=? " +"where id=?";
        try {
            PreparedStatement ptmt=conn.prepareStatement(sql);// 编译sql
            ptmt.setInt(1,transinfo.getSourceId());// 给?传递参数
            ptmt.setString(2,transinfo.getSourceAccount());// 给?传递参数
            ptmt.setInt(3,transinfo.getDestinationId());// 给?传递参数
            ptmt.setString(4,transinfo.getDesinationAccount());// 给?传递参数
            ptmt.setDouble(5,transinfo.getAmount());// 给?传递参数
            ptmt.setInt(6,transinfo.getId());// 给?传递参数
            ptmt.execute();// 执行sql语句
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除
     * @param transinfo
     */
    public void delete(Transinfo transinfo){
        Connection conn=DBUtil.getConnection();// 获得数据库连接
        String sql=""+"delete from trans_info "+"where id=?";
        try {
            PreparedStatement ptmt=conn.prepareStatement(sql);// 编译sql
            ptmt.setInt(1,transinfo.getId());// 给?传递参数
            ptmt.execute();// 执行sql语句
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询
     * @param transinfo
     */
    public List<Transinfo> query(Transinfo transinfo){
        List<Transinfo> result=new ArrayList<Transinfo>();// 创建列表
        Connection conn=DBUtil.getConnection();// 获得数据库连接
        StringBuilder sb=new StringBuilder();// 可变的字符序列,可连接字符串
        sb.append("select * from trans_info ");
        sb.append("where account like ?");
        try {
            PreparedStatement ptmt=conn.prepareStatement(sb.toString());// 编译sql
            // 给?传递参数
            ptmt.setString(1,"%"+transinfo.getSourceAccount()+"%");
            ResultSet rs=ptmt.executeQuery();// 创建结果集
            Transinfo t=null;
            while (rs.next()){// 如果不为空进行循环
                t=new Transinfo();
                t.setId(rs.getInt("id"));// 给类属性赋值
                t.setSourceId(rs.getInt("source_id"));// 给类属性赋值
                // 给类属性赋值
                t.setSourceAccount(rs.getString("source_account"));
                // 给类属性赋值
                t.setDestinationId(rs.getInt("destination_id"));
                // 给类属性赋值
                t.setDesinationAccount(rs.getString("destination_account"));
                t.setAmount(rs.getDouble("amount"));// 给类属性赋值
                result.add(t);// 添加列表
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}

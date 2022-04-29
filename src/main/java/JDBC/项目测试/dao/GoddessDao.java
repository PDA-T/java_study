package JDBC.项目测试.dao;

import JDBC.项目测试.db.DBUtil;
import JDBC.项目测试.model.Goddess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GoddessDao {
    /**
     * 添加
     */
    public void addGoddess(Goddess g){
        // 获得数据库连接
        Connection conn= DBUtil.getConnection();
        String sql=""+"insert into imooc_goddess"+"(user_name,sex,age,birthday,email,mobile,"+
                "create_user,create_date,update_user,update_date,isdel)"+
                "values("+"?,?,?,?,?,?,?,current_date(),?,current_date(),?)";
        try {
            PreparedStatement ptmt=conn.prepareStatement(sql);// 编译sql语句
            ptmt.setString(1,g.getUser_name());// 给?传递参数
            ptmt.setInt(2,g.getSex());// 给?传递参数
            ptmt.setInt(3,g.getAge());// 给?传递参数
            ptmt.setDate(4,new Date(g.getBirthday().getTime()));// 给?传递参数
            ptmt.setString(5,g.getEmail());// 给?传递参数
            ptmt.setString(6,g.getMobile());// 给?传递参数
            ptmt.setString(7,g.getCreate_user());// 给?传递参数
            ptmt.setString(8,g.getUpdate_user());// 给?传递参数
            ptmt.setInt(9,g.getIsdel());// 给?传递参数
            ptmt.execute();// 执行sql语句
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 修改
     */
    public void updateGoddess(Goddess g){
        // 获得数据库连接
        Connection conn=DBUtil.getConnection();
        String sql=""+" update imooc_goddess"+" set user_name=?,sex=?,age=?,birthday=?" +
                ",email=?,mobile=? ,"+" update_user=?,update_date=current_date()" + ",isdel=? "+
                " where id=? ";
        try {
            PreparedStatement ptmt=conn.prepareStatement(sql);// 编译sql语句
            ptmt.setString(1,g.getUser_name());// 给?传递参数
            ptmt.setInt(2,g.getSex());// 给?传递参数
            ptmt.setInt(3,g.getAge());// 给?传递参数
            ptmt.setDate(4,new Date(g.getBirthday().getTime()));// 给?传递参数
            ptmt.setString(5,g.getEmail());// 给?传递参数
            ptmt.setString(6,g.getMobile());// 给?传递参数
            ptmt.setString(7,g.getUpdate_user());// 给?传递参数
            ptmt.setInt(8,g.getIsdel());// 给?传递参数
            ptmt.setInt(9,g.getId());// 给?传递参数
            ptmt.execute();// 执行sql语句
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 删除
     */
    public void delGoddess(Integer id){
        // 获得数据库连接
        Connection conn=DBUtil.getConnection();
        String sql=""+" delete from imooc_goddess"+" where id=? ";
        try {
            PreparedStatement ptmt=conn.prepareStatement(sql);// 编译sql语句
            ptmt.setInt(1,id);// 给?传递参数
            ptmt.execute();// 执行sql语句
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 查询全部
     * @return
     */
    public List<Goddess> query(){
        List<Goddess> result=new ArrayList<Goddess>();
        Connection conn=DBUtil.getConnection();
        StringBuilder sb=new StringBuilder();// 可变的字符序列,可连接字符串
        sb.append("select id,user_name,age from imooc_goddess ");
        PreparedStatement ptmt= null;
        try {
            ptmt = conn.prepareStatement(sb.toString());
            ResultSet rs=ptmt.executeQuery();
            Goddess g=null;
            while (rs.next()){
                g=new Goddess();
                g.setId(rs.getInt("id"));
                g.setUser_name(rs.getString("user_name"));
                g.setAge(rs.getInt("age"));
                result.add(g);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 查询多个
     */
    public List<Goddess> query(List<Map<String,Object>> params){
        // 创建列表类
        List<Goddess> result=new ArrayList<Goddess>();
        // 与特定数据库的连接(会话),在连接上下文中执行SQL语句并返回结果
        Connection conn=DBUtil.getConnection();
        // 可变的字符序列类
        StringBuilder sb=new StringBuilder();
        // 在列表末尾追加新的对象,追加子串
        sb.append("select * from imooc_goddess where 1=1");
        // 判断集合是否为空
        if (params!=null&&params.size()>0){
            // 遍历列表
            for (int i=0;i<params.size();i++){
                // 取得集合中第i个元素
                Map<String,Object> map=params.get(i);
                // 在列表末尾追加新的对象,追加子串
                sb.append(" and "+map.get("name")+" "+map.get("rela")+" "+map.get("value")+" ");
            }
        }
        try {
            // 初始化SQL
            PreparedStatement ptmt=conn.prepareStatement(sb.toString());
            // 输出连接的字符串
            System.out.println(sb.toString());
            // 数据库结果集的数据表,把数据库响应的查询结果存放在ResultSet类对象中
            ResultSet rs=ptmt.executeQuery();
            // 通过数据库连接操控数据库
//            Statement stmt=conn.createStatement();
            // 发送查询语句
//            ResultSet rs=stmt.executeQuery("select user_name,age from imooc_goddess");
//            List<Goddess> gs=new ArrayList<Goddess>();
            Goddess g=null;
            // 如果对象里有数据返回true
            while (rs.next()){
                g=new Goddess();// 实例化类对象
                g.setId(rs.getInt("id"));
                g.setUser_name(rs.getString("user_name"));
                g.setAge(rs.getInt("age"));
                g.setSex(rs.getInt("sex"));
                g.setBirthday(rs.getDate("birthday"));
                g.setEmail(rs.getString("email"));
                g.setMobile(rs.getString("mobile"));
                g.setCreate_date(rs.getDate("create_date"));
                g.setCreate_user(rs.getString("create_user"));
                g.setUpdate_date(rs.getDate("update_date"));
                g.setUpdate_user(rs.getString("update_user"));
                g.setIsdel(rs.getInt("isdel"));
                result.add(g);// 放入集合
            }
            return result;// 返回列表值
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询单个
     * @param id
     * @return
     */
    public Goddess get(Integer id){
        Goddess g=null;// 创建类对象
        // 获得数据库连接
        Connection conn=DBUtil.getConnection();
        String sql=""+" select * from imooc_goddess"+" where id=? ";
        try {
            PreparedStatement ptmt=conn.prepareStatement(sql);// 编译sql语句
            ptmt.setInt(1,id);// 给?传递参数
            ResultSet rs=ptmt.executeQuery();// 执行查询sql语句,返回结果集
            while (rs.next()){// 如果结果集有数据开始循环
                g=new Goddess();// 实例化类对象
                g.setId(rs.getInt("id"));
                g.setUser_name(rs.getString("user_name"));
                g.setAge(rs.getInt("age"));
                g.setSex(rs.getInt("sex"));
                g.setBirthday(rs.getDate("birthday"));
                g.setEmail(rs.getString("email"));
                g.setMobile(rs.getString("mobile"));
                g.setCreate_date(rs.getDate("create_date"));
                g.setCreate_user(rs.getString("create_user"));
                g.setUpdate_date(rs.getDate("update_date"));
                g.setUpdate_user(rs.getString("update_user"));
                g.setIsdel(rs.getInt("isdel"));
            }
            return g;// 返回对象
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return g;
    }
}

package JDBC.��Ŀ����.dao;

import JDBC.��Ŀ����.db.DBUtil;
import JDBC.��Ŀ����.model.Goddess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GoddessDao {
    /**
     * ���
     */
    public void addGoddess(Goddess g){
        // ������ݿ�����
        Connection conn= DBUtil.getConnection();
        String sql=""+"insert into imooc_goddess"+"(user_name,sex,age,birthday,email,mobile,"+
                "create_user,create_date,update_user,update_date,isdel)"+
                "values("+"?,?,?,?,?,?,?,current_date(),?,current_date(),?)";
        try {
            PreparedStatement ptmt=conn.prepareStatement(sql);// ����sql���
            ptmt.setString(1,g.getUser_name());// ��?���ݲ���
            ptmt.setInt(2,g.getSex());// ��?���ݲ���
            ptmt.setInt(3,g.getAge());// ��?���ݲ���
            ptmt.setDate(4,new Date(g.getBirthday().getTime()));// ��?���ݲ���
            ptmt.setString(5,g.getEmail());// ��?���ݲ���
            ptmt.setString(6,g.getMobile());// ��?���ݲ���
            ptmt.setString(7,g.getCreate_user());// ��?���ݲ���
            ptmt.setString(8,g.getUpdate_user());// ��?���ݲ���
            ptmt.setInt(9,g.getIsdel());// ��?���ݲ���
            ptmt.execute();// ִ��sql���
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * �޸�
     */
    public void updateGoddess(Goddess g){
        // ������ݿ�����
        Connection conn=DBUtil.getConnection();
        String sql=""+" update imooc_goddess"+" set user_name=?,sex=?,age=?,birthday=?" +
                ",email=?,mobile=? ,"+" update_user=?,update_date=current_date()" + ",isdel=? "+
                " where id=? ";
        try {
            PreparedStatement ptmt=conn.prepareStatement(sql);// ����sql���
            ptmt.setString(1,g.getUser_name());// ��?���ݲ���
            ptmt.setInt(2,g.getSex());// ��?���ݲ���
            ptmt.setInt(3,g.getAge());// ��?���ݲ���
            ptmt.setDate(4,new Date(g.getBirthday().getTime()));// ��?���ݲ���
            ptmt.setString(5,g.getEmail());// ��?���ݲ���
            ptmt.setString(6,g.getMobile());// ��?���ݲ���
            ptmt.setString(7,g.getUpdate_user());// ��?���ݲ���
            ptmt.setInt(8,g.getIsdel());// ��?���ݲ���
            ptmt.setInt(9,g.getId());// ��?���ݲ���
            ptmt.execute();// ִ��sql���
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * ɾ��
     */
    public void delGoddess(Integer id){
        // ������ݿ�����
        Connection conn=DBUtil.getConnection();
        String sql=""+" delete from imooc_goddess"+" where id=? ";
        try {
            PreparedStatement ptmt=conn.prepareStatement(sql);// ����sql���
            ptmt.setInt(1,id);// ��?���ݲ���
            ptmt.execute();// ִ��sql���
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * ��ѯȫ��
     * @return
     */
    public List<Goddess> query(){
        List<Goddess> result=new ArrayList<Goddess>();
        Connection conn=DBUtil.getConnection();
        StringBuilder sb=new StringBuilder();// �ɱ���ַ�����,�������ַ���
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
     * ��ѯ���
     */
    public List<Goddess> query(List<Map<String,Object>> params){
        // �����б���
        List<Goddess> result=new ArrayList<Goddess>();
        // ���ض����ݿ������(�Ự),��������������ִ��SQL��䲢���ؽ��
        Connection conn=DBUtil.getConnection();
        // �ɱ���ַ�������
        StringBuilder sb=new StringBuilder();
        // ���б�ĩβ׷���µĶ���,׷���Ӵ�
        sb.append("select * from imooc_goddess where 1=1");
        // �жϼ����Ƿ�Ϊ��
        if (params!=null&&params.size()>0){
            // �����б�
            for (int i=0;i<params.size();i++){
                // ȡ�ü����е�i��Ԫ��
                Map<String,Object> map=params.get(i);
                // ���б�ĩβ׷���µĶ���,׷���Ӵ�
                sb.append(" and "+map.get("name")+" "+map.get("rela")+" "+map.get("value")+" ");
            }
        }
        try {
            // ��ʼ��SQL
            PreparedStatement ptmt=conn.prepareStatement(sb.toString());
            // ������ӵ��ַ���
            System.out.println(sb.toString());
            // ���ݿ����������ݱ�,�����ݿ���Ӧ�Ĳ�ѯ��������ResultSet�������
            ResultSet rs=ptmt.executeQuery();
            // ͨ�����ݿ����Ӳٿ����ݿ�
//            Statement stmt=conn.createStatement();
            // ���Ͳ�ѯ���
//            ResultSet rs=stmt.executeQuery("select user_name,age from imooc_goddess");
//            List<Goddess> gs=new ArrayList<Goddess>();
            Goddess g=null;
            // ��������������ݷ���true
            while (rs.next()){
                g=new Goddess();// ʵ���������
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
                result.add(g);// ���뼯��
            }
            return result;// �����б�ֵ
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ��ѯ����
     * @param id
     * @return
     */
    public Goddess get(Integer id){
        Goddess g=null;// ���������
        // ������ݿ�����
        Connection conn=DBUtil.getConnection();
        String sql=""+" select * from imooc_goddess"+" where id=? ";
        try {
            PreparedStatement ptmt=conn.prepareStatement(sql);// ����sql���
            ptmt.setInt(1,id);// ��?���ݲ���
            ResultSet rs=ptmt.executeQuery();// ִ�в�ѯsql���,���ؽ����
            while (rs.next()){// �������������ݿ�ʼѭ��
                g=new Goddess();// ʵ���������
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
            return g;// ���ض���
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return g;
    }
}

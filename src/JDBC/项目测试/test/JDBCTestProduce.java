package JDBC.项目测试.test;

import JDBC.项目测试.dao.ProduceDao;
import JDBC.项目测试.model.Goddess;

import java.util.List;

public class JDBCTestProduce {
    /**
     * 主方法
     * @param args
     */
    public static void main(String[] args) {
        String sp_name="";
        List<Goddess> res=null;
        Integer count=0;
        // 带入参的过程
        res=select_filter(sp_name);
        showResult(res);
        // 出参过程
        count=select_count();
        System.out.println(count);
    }

    /**
     * 处理
     * @param sp_name
     * @return
     */
    public static List<Goddess> select_filter(String sp_name){
        ProduceDao dao=new ProduceDao();
        return dao.select_filter(sp_name);
    }

    /**
     * 调用出参方法
     * @return
     */
    public static Integer select_count(){
        ProduceDao dao=new ProduceDao();
        return dao.select_count();
    }

    /**
     * 遍历
     * @param res
     */
    public static void showResult(List<Goddess> res){
        for (int i=0;i<res.size();i++){
            System.out.println(res.get(i).getId()+":"+res.get(i).getUser_name()
                    +":"+res.get(i).getMobile());
        }
    }
}

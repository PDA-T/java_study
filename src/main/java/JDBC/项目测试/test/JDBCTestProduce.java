package JDBC.��Ŀ����.test;

import JDBC.��Ŀ����.dao.ProduceDao;
import JDBC.��Ŀ����.model.Goddess;

import java.util.List;

public class JDBCTestProduce {
    /**
     * ������
     * @param args
     */
    public static void main(String[] args) {
        String sp_name="";
        List<Goddess> res=null;
        Integer count=0;
        // ����εĹ���
        res=select_filter(sp_name);
        showResult(res);
        // ���ι���
        count=select_count();
        System.out.println(count);
    }

    /**
     * ����
     * @param sp_name
     * @return
     */
    public static List<Goddess> select_filter(String sp_name){
        ProduceDao dao=new ProduceDao();
        return dao.select_filter(sp_name);
    }

    /**
     * ���ó��η���
     * @return
     */
    public static Integer select_count(){
        ProduceDao dao=new ProduceDao();
        return dao.select_count();
    }

    /**
     * ����
     * @param res
     */
    public static void showResult(List<Goddess> res){
        for (int i=0;i<res.size();i++){
            System.out.println(res.get(i).getId()+":"+res.get(i).getUser_name()
                    +":"+res.get(i).getMobile());
        }
    }
}

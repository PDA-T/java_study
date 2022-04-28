package JDBC.项目测试.action;

import JDBC.项目测试.dao.GoddessDao;
import JDBC.项目测试.model.Goddess;

import java.util.*;

public class GoddessAction {
    /**
     * 添加
     * @param goddess
     */
    public void add(Goddess goddess){
        GoddessDao dao=new GoddessDao();
        dao.addGoddess(goddess);
    }

    /**
     * 查询单个
     * @param id
     */
    public Goddess get(Integer id){
        GoddessDao dao=new GoddessDao();
        return dao.get(id);
    }

    /**
     * 修改
     * @param goddess
     */
    public void edit(Goddess goddess){
        GoddessDao dao=new GoddessDao();
        dao.updateGoddess(goddess);
    }

    /**
     * 删除
     * @param id
     */
    public void del(Integer id){
        GoddessDao dao=new GoddessDao();
        dao.delGoddess(id);
    }

    /**
     * 查询全部
     * @return
     */
    public List<Goddess> query(){
        GoddessDao dao=new GoddessDao();
        return dao.query();
    }

    /**
     * 参数查询
     * @param params
     * @return
     */
    public List<Goddess> query(List<Map<String,Object>> params){
        GoddessDao dao=new GoddessDao();
        return dao.query(params);
    }
}

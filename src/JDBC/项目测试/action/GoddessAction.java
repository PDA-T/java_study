package JDBC.��Ŀ����.action;

import JDBC.��Ŀ����.dao.GoddessDao;
import JDBC.��Ŀ����.model.Goddess;

import java.util.*;

public class GoddessAction {
    /**
     * ���
     * @param goddess
     */
    public void add(Goddess goddess){
        GoddessDao dao=new GoddessDao();
        dao.addGoddess(goddess);
    }

    /**
     * ��ѯ����
     * @param id
     */
    public Goddess get(Integer id){
        GoddessDao dao=new GoddessDao();
        return dao.get(id);
    }

    /**
     * �޸�
     * @param goddess
     */
    public void edit(Goddess goddess){
        GoddessDao dao=new GoddessDao();
        dao.updateGoddess(goddess);
    }

    /**
     * ɾ��
     * @param id
     */
    public void del(Integer id){
        GoddessDao dao=new GoddessDao();
        dao.delGoddess(id);
    }

    /**
     * ��ѯȫ��
     * @return
     */
    public List<Goddess> query(){
        GoddessDao dao=new GoddessDao();
        return dao.query();
    }

    /**
     * ������ѯ
     * @param params
     * @return
     */
    public List<Goddess> query(List<Map<String,Object>> params){
        GoddessDao dao=new GoddessDao();
        return dao.query(params);
    }
}

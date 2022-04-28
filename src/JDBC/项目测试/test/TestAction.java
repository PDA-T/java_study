package JDBC.��Ŀ����.test;

import JDBC.��Ŀ����.action.GoddessAction;
import JDBC.��Ŀ����.model.Goddess;

import java.util.*;

public class TestAction {
    public static void main(String[] args) {
        // ��ѯ
        GoddessAction action=new GoddessAction();
        List<Goddess> result=action.query();
        for (int i=0;i<result.size();i++){
            System.out.println(result.get(i).getId()+":"+result.get(i).getUser_name());
        }
        // ����
        Goddess g=new Goddess();
        g.setUser_name("С��1");
        g.setSex(1);
        g.setAge(25);
        g.setBirthday(new Date());
        g.setEmail("xiaoqing@qq.com");
        g.setMobile("15688888888");
        g.setIsdel(0);
        g.setId(4);
//        action.add(g);
        // ����
//        action.edit(g);
        // ɾ��
//        action.del(4);
        // ������ѯ
        List<Map<String,Object>> params=new ArrayList<Map<String,Object>>();
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("name","user_name");
        map.put("rela","=");
        map.put("value","'С��'");
        params.add(map);
        List<Goddess> results=action.query(params);
        for (int i=0;i<results.size();i++){
            System.out.println(results.get(i).getId()+":"+results.get(i).getUser_name());
        }
    }
}

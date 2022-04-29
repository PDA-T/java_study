package JDBC.项目测试.test;

import JDBC.项目测试.action.GoddessAction;
import JDBC.项目测试.model.Goddess;

import java.util.*;

public class TestAction {
    public static void main(String[] args) {
        // 查询
        GoddessAction action=new GoddessAction();
        List<Goddess> result=action.query();
        for (int i=0;i<result.size();i++){
            System.out.println(result.get(i).getId()+":"+result.get(i).getUser_name());
        }
        // 新增
        Goddess g=new Goddess();
        g.setUser_name("小青1");
        g.setSex(1);
        g.setAge(25);
        g.setBirthday(new Date());
        g.setEmail("xiaoqing@qq.com");
        g.setMobile("15688888888");
        g.setIsdel(0);
        g.setId(4);
//        action.add(g);
        // 更新
//        action.edit(g);
        // 删除
//        action.del(4);
        // 参数查询
        List<Map<String,Object>> params=new ArrayList<Map<String,Object>>();
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("name","user_name");
        map.put("rela","=");
        map.put("value","'小夏'");
        params.add(map);
        List<Goddess> results=action.query(params);
        for (int i=0;i<results.size();i++){
            System.out.println(results.get(i).getId()+":"+results.get(i).getUser_name());
        }
    }
}

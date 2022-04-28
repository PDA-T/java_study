package 多态.方法重写;

import java.util.SortedMap;

/**
 * 医院类型
 */
public class Hospital {
    /**
     * 主方法
     */
    public static void main(String[] args) {
        Hospital hs=new Hospital();// 实例化医院类
        Orthopaedics op=new Orthopaedics();// 实例化骨科类
        op.setName("骨科");
        Surgery sg=new Surgery();// 实例化外科类
        sg.setName("外科");
        Patient patient=new Patient();// 实例化病人
        hs.register(patient,op);// 调用挂号方法,传入病人和科室
        hs.register(patient,sg);// 调用挂号方法,传入病人和科室
    }
    public void register(Patient patient,Department department){// 创建挂号方法,传入病人和某个科室
        System.out.println("正在挂号科室:"+department.name);// 打印提示
        department.treatment(patient);// 调用接收病人方法
    }
}
/**
 * 病人类型
 */
class Patient{
    public int id;// 编号
    public String name;// 姓名
    public String gander;// 性别
    public int age;// 年龄
    public float health;// 健康状态
}
/**
 * 科室类
 */
class Department{
    public int id;// 编号
    public String name;// 名称
    public String intro;// 科室介绍
    public void treatment(Patient patient){// 创建接收病人方法,传入病人参数
        System.out.println("已接收:"+this.name+"病人");// 打印提示
    }
    public String setName(String name){// 修改科室名称方法
        return this.name=name;// 修改方法属性为传入的值
    }
}
/**
 * 骨科类,继承科室类
 */
class Orthopaedics extends Department{
    public void treatment(Patient patient){// 重写父类接收病人方法,传入病人参数
        System.out.println("已接收:"+this.name+"病人");// 打印提示
    }
}
/**
 * 外壳类,继承科室类
 */
class Surgery extends Department{
    public void treatment(Patient patient){// 重写父类接收病人方法,传入病人参数
        System.out.println("已接收:"+this.name+"病人");// 打印提示
    }
}
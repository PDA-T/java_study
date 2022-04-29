package JDBC.项目测试.view;

import JDBC.项目测试.action.GoddessAction;
import JDBC.项目测试.model.Goddess;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class View {
    private static final String CONTEXT="欢迎来到视频查询:\n"+
            "下面是视频查询的功能列表:\n"+
            "[MAIN/M]:主菜单\n"+
            "[QUERY/Q]:查看全部视频的信息\n"+
            "[GET/G]:查看某个视频的详细信息\n"+
            "[ADD/A]:添加视频信息\n"+
            "[UPDATE/U]:更新视频信息\n"+
            "[DELETE/D]:删除视频信息\n"+
            "[SEARCH/S]:查询视频信息(根据名字、手机号查询)\n"+
            "[EXIT/E]:退出视频查询\n"+
            "[BREAK/B]:退出当前功能,返回主菜单";
    private static final String OPERATION_MAIN="MAIN";
    private static final String OPERATION_QUERY="QUERY";
    private static final String OPERATION_GET="GET";
    private static final String OPERATION_ADD="ADD";
    private static final String OPERATION_UPDATE="UPDATE";
    private static final String OPERATION_DELETE="DELETE";
    private static final String OPERATION_SEARCH="SEARCH";
    private static final String OPERATION_EXIT="EXIT";
    private static final String OPERATION_BREAK="BREAK";
    /**
     * 主方法
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(CONTEXT);// 打印提示信息
        Scanner scan=new Scanner(System.in);// 扫描器
        Goddess goddess=new Goddess();
        GoddessAction action=new GoddessAction();
        String prenious=null;// 循环标记,防止跳出当前判断
        Integer step=1;// 步骤
        while (scan.hasNext()){// 有输入值进行循环
            String in=scan.next().toString();// 储存输入的值
            // 如果输入的值为退出
            if (OPERATION_EXIT.equals(in.toUpperCase())
                    ||OPERATION_EXIT.substring(0,1).equals(in.toUpperCase())){
                System.out.println("已退出");// 打印退出
                break;// 结束循环
                // 查询
            }else if(OPERATION_QUERY.equals(in.toUpperCase())
                    ||OPERATION_QUERY.substring(0,1).equals(in.toUpperCase())){
                List<Goddess> list=action.query();// 查询
                for (Goddess go:list){// 遍历列表
                    System.out.println(go.getId()+",姓名:"+go.getUser_name());
                }
                // 添加
            }else if (OPERATION_ADD.equals(in.toUpperCase())
                    ||OPERATION_ADD.substring(0,1).equals(in.toUpperCase())
                    ||OPERATION_ADD.equals(prenious)){
                prenious=OPERATION_ADD;
                if (step==1){
                    System.out.println("请输入视频的[名字]");
                }else if (step==2){
                    goddess.setUser_name(in);// 存入第一次输入的名字
                    System.out.println("请输入视频的[年龄]");
                }else if (step==3){
                    goddess.setAge(Integer.valueOf(in));// 存入视频年龄
                    System.out.println("请输入视频的[生日],格式如:yyyy-mm-dd");
                }else if (step==4){
                    SimpleDateFormat sf=new SimpleDateFormat("yyyy-mm-dd");
                    Date birthday= null;
                    try {
                        birthday = sf.parse(in);
                        goddess.setBirthday(birthday);
                        System.out.println("请输入视频的[邮箱]");
                    } catch (ParseException e) {
                        e.printStackTrace();
                        System.out.println("您输入的格式有无,请重写输入");
                        step=3;
                    }
                }else if (step==5){
                    goddess.setEmail(in);// 存入输入的邮箱
                    System.out.println("请输入视频的[手机号]");
                    goddess.setCreate_user("ADMIN");
                    goddess.setUpdate_user("ADMIN");
                    goddess.setSex(1);
                    goddess.setIsdel(0);
                }else if (step==6){
                    goddess.setMobile(in);// 存入输入的手机号
                    action.add(goddess);// 存入数据库
                    System.out.println("新增视频成功");
                }
                if (OPERATION_ADD.equals(prenious)){
                    step++;// 标记自增
                }
            }else {
                System.out.println("您输入的值为:"+in);
            }
        }
    }
}

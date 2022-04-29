package JDBC.��Ŀ����.view;

import JDBC.��Ŀ����.action.GoddessAction;
import JDBC.��Ŀ����.model.Goddess;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class View {
    private static final String CONTEXT="��ӭ������Ƶ��ѯ:\n"+
            "��������Ƶ��ѯ�Ĺ����б�:\n"+
            "[MAIN/M]:���˵�\n"+
            "[QUERY/Q]:�鿴ȫ����Ƶ����Ϣ\n"+
            "[GET/G]:�鿴ĳ����Ƶ����ϸ��Ϣ\n"+
            "[ADD/A]:�����Ƶ��Ϣ\n"+
            "[UPDATE/U]:������Ƶ��Ϣ\n"+
            "[DELETE/D]:ɾ����Ƶ��Ϣ\n"+
            "[SEARCH/S]:��ѯ��Ƶ��Ϣ(�������֡��ֻ��Ų�ѯ)\n"+
            "[EXIT/E]:�˳���Ƶ��ѯ\n"+
            "[BREAK/B]:�˳���ǰ����,�������˵�";
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
     * ������
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(CONTEXT);// ��ӡ��ʾ��Ϣ
        Scanner scan=new Scanner(System.in);// ɨ����
        Goddess goddess=new Goddess();
        GoddessAction action=new GoddessAction();
        String prenious=null;// ѭ�����,��ֹ������ǰ�ж�
        Integer step=1;// ����
        while (scan.hasNext()){// ������ֵ����ѭ��
            String in=scan.next().toString();// ���������ֵ
            // ��������ֵΪ�˳�
            if (OPERATION_EXIT.equals(in.toUpperCase())
                    ||OPERATION_EXIT.substring(0,1).equals(in.toUpperCase())){
                System.out.println("���˳�");// ��ӡ�˳�
                break;// ����ѭ��
                // ��ѯ
            }else if(OPERATION_QUERY.equals(in.toUpperCase())
                    ||OPERATION_QUERY.substring(0,1).equals(in.toUpperCase())){
                List<Goddess> list=action.query();// ��ѯ
                for (Goddess go:list){// �����б�
                    System.out.println(go.getId()+",����:"+go.getUser_name());
                }
                // ���
            }else if (OPERATION_ADD.equals(in.toUpperCase())
                    ||OPERATION_ADD.substring(0,1).equals(in.toUpperCase())
                    ||OPERATION_ADD.equals(prenious)){
                prenious=OPERATION_ADD;
                if (step==1){
                    System.out.println("��������Ƶ��[����]");
                }else if (step==2){
                    goddess.setUser_name(in);// �����һ�����������
                    System.out.println("��������Ƶ��[����]");
                }else if (step==3){
                    goddess.setAge(Integer.valueOf(in));// ������Ƶ����
                    System.out.println("��������Ƶ��[����],��ʽ��:yyyy-mm-dd");
                }else if (step==4){
                    SimpleDateFormat sf=new SimpleDateFormat("yyyy-mm-dd");
                    Date birthday= null;
                    try {
                        birthday = sf.parse(in);
                        goddess.setBirthday(birthday);
                        System.out.println("��������Ƶ��[����]");
                    } catch (ParseException e) {
                        e.printStackTrace();
                        System.out.println("������ĸ�ʽ����,����д����");
                        step=3;
                    }
                }else if (step==5){
                    goddess.setEmail(in);// �������������
                    System.out.println("��������Ƶ��[�ֻ���]");
                    goddess.setCreate_user("ADMIN");
                    goddess.setUpdate_user("ADMIN");
                    goddess.setSex(1);
                    goddess.setIsdel(0);
                }else if (step==6){
                    goddess.setMobile(in);// ����������ֻ���
                    action.add(goddess);// �������ݿ�
                    System.out.println("������Ƶ�ɹ�");
                }
                if (OPERATION_ADD.equals(prenious)){
                    step++;// �������
                }
            }else {
                System.out.println("�������ֵΪ:"+in);
            }
        }
    }
}

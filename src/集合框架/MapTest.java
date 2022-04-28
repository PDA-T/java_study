package ���Ͽ��;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class MapTest {
    /**
     * ������װѧ�����Ͷ���
     */
    public Map<String,Student> students;
    /**
     * �ڹ������г�ʼ��students����
     */
    public MapTest(){
        this.students=new HashMap<String,Student>();
    }
    /**
     * �������:����ID,�ж��Ƿ�ռ��
     */
    public void testPut(){
        Scanner console=new Scanner(System.in);// ����ɨ����
        int i=0;// ����ѭ������
        while (i<3){// ����ѭ������
            System.out.println("������ѧ��ID:");// ��ӡ���
            String ID=console.next();// ��ȡ�����ֵ
            Student st=students.get(ID);// �жϸ�ID�Ƿ�ռ��
            if (st==null){// �ж�ֵ�Ƿ����
                System.out.println("������ѧ������:");// ��ӡ���
                String name=console.next();// ��ȡ�����ֵ
                Student newStudent=new Student(ID,name);// �����µ�ѧ�������
                students.put(ID,newStudent);// ���÷������ID-ѧ��ӳ��,��Student�ഫ���Ԫ�ر�Ϊֵ���
                System.out.println("�ɹ����ѧ��:"+students.get(ID).getName());// ��ӡ������
                i++;// ѭ��������һ
            }else {
                System.out.println("��ѧ��ID�ѱ�ռ��!");// ��ӡ���
                continue;// ���¿�ʼѭ��
            }
        }
    }
    /**
     * ����Map��KeySet����
     */
    public void testKeySet(){
        Set<String> KeySet=students.keySet();// ��ȡMap�����еļ�
        for(String stuId:KeySet){// ����KeySet,ȡ��ÿһ����,�ٵ���get����ȡ��ÿ������Ӧ��value
            Student st=students.get(stuId);// ��ȡֵ
            if (st!=null){// �жϸ�ID�Ƿ�Ϊ��
                System.out.println("ѧ��:"+st.getName());// ��ӡ���
            }
        }
    }

    /**
     * ����ɾ��Map�е�ӳ��
     */
    public void testRemove(){
        System.out.println("������Ҫɾ����ѧ��ID:");// ��ʾҪɾ����ѧ��ID
        Scanner console=new Scanner(System.in);// ����ɨ����
        String ID=console.next();// ��ȡ�����ID
        Student st=students.get(ID);// ��ȡ��ID��ֵ
        while (true){// ��ѭ��
            if (st==null){// �жϸ�ֵ�Ƿ�Ϊ��
                System.out.println("��");// ��ӡ���
                continue;// ����ѭ��
            }
            System.out.println("��ɾ��:"+st.getName());// ɾ����ֵ
            students.remove(ID);// ɾ����ID��ֵ
            break;// ����ѭ��
        }
    }

    /**
     * ͨ��entrySet����������Map
     */
    public void testEntrySet(){
        Set<Map.Entry<String,Student>> entrySet=students.entrySet();// ��ȡMap�ļ���ֵ
        for (Map.Entry<String,Student> entry:entrySet){// ѭ������
            System.out.println(entry.getKey());// ��ȡ��
            System.out.println(entry.getValue().getName());// ��ȡֵ
        }
    }

    /**
     * ����put�����޸�Map�����ֵ�ӳ��
     */
    public void testModify(){
        System.out.println("������Ҫ�޸ĵ�ѧ��ID");// ��ӡ�����ʾ
        Scanner sc=new Scanner(System.in);// ����ɨ����
        String ID=sc.next();// ��ȡ�����ֵ
        Student st=students.get(ID);// ��ȡID��Ӧ��ֵ
        while (true){// ��ѭ��
            if (st==null){// �ж�����ID�Ƿ�Ϊ��
                System.out.println("��");// ��ӡ������ʾ
                continue;// ����ѭ��
            }
            System.out.println("������Ҫ�޸ĵ�ֵ:");// ��ӡ���
            String name=sc.next();// ��ȡ�����ֵ
            Student vale=new Student(ID,name);// ���������ֵ
            students.put(ID,vale);// �޸�����
            break;// ����ѭ��
        }
    }
    /**
     * ����Map���Ƿ����ĳ��Key����ĳ��Valueֵ
     */
    public void testContainsKeyOrValue(){
        System.out.println("������Ҫ��ѯ��ѧ��ID:");// ��ӡ��ʾ
        Scanner console=new Scanner(System.in);// ����ɨ����
        String id=console.next();// ��ȡ�����id
        // ��Map����containsKey()�������ж��Ƿ����ĳ��Key
        System.out.println("�������ѧ��IDΪ:"+id+"��ѧ��ӳ������Ƿ����:"+students.containsKey(id));
        if (students.containsKey(id)){// �ж��Ƿ����
            // ��Map����containsValue()�������ж��Ƿ����ĳ��Valueֵ
            System.out.println("��Ӧ��ѧ��Ϊ:"+students.get(id).getName());
        }
        System.out.println("������Ҫ��ѯ��ѧ������:");// ��ӡ��ʾ
        String name=console.next();// ��ȡ����ֵ
        if (students.containsValue(new Student(null,name))){// �ж�����ѧ���Ƿ����
            System.out.println("��ѧ��ӳ����а���ѧ��:"+name);// ��ӡ���
        }else {// �жϲ�����
            System.out.println("���벻����");// ��ӡ���
        }
    }
    public static void main(String[] args) {
        MapTest mt=new MapTest();// ʵ��������
        mt.testPut();
        mt.testKeySet();
//        mt.testRemove();
//        mt.testEntrySet();
//        mt.testModify();
//        mt.testEntrySet();
        mt.testContainsKeyOrValue();
    }
}

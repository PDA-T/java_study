package ��̬.������д;

import java.util.SortedMap;

/**
 * ҽԺ����
 */
public class Hospital {
    /**
     * ������
     */
    public static void main(String[] args) {
        Hospital hs=new Hospital();// ʵ����ҽԺ��
        Orthopaedics op=new Orthopaedics();// ʵ�����ǿ���
        op.setName("�ǿ�");
        Surgery sg=new Surgery();// ʵ���������
        sg.setName("���");
        Patient patient=new Patient();// ʵ��������
        hs.register(patient,op);// ���ùҺŷ���,���벡�˺Ϳ���
        hs.register(patient,sg);// ���ùҺŷ���,���벡�˺Ϳ���
    }
    public void register(Patient patient,Department department){// �����Һŷ���,���벡�˺�ĳ������
        System.out.println("���ڹҺſ���:"+department.name);// ��ӡ��ʾ
        department.treatment(patient);// ���ý��ղ��˷���
    }
}
/**
 * ��������
 */
class Patient{
    public int id;// ���
    public String name;// ����
    public String gander;// �Ա�
    public int age;// ����
    public float health;// ����״̬
}
/**
 * ������
 */
class Department{
    public int id;// ���
    public String name;// ����
    public String intro;// ���ҽ���
    public void treatment(Patient patient){// �������ղ��˷���,���벡�˲���
        System.out.println("�ѽ���:"+this.name+"����");// ��ӡ��ʾ
    }
    public String setName(String name){// �޸Ŀ������Ʒ���
        return this.name=name;// �޸ķ�������Ϊ�����ֵ
    }
}
/**
 * �ǿ���,�̳п�����
 */
class Orthopaedics extends Department{
    public void treatment(Patient patient){// ��д������ղ��˷���,���벡�˲���
        System.out.println("�ѽ���:"+this.name+"����");// ��ӡ��ʾ
    }
}
/**
 * �����,�̳п�����
 */
class Surgery extends Department{
    public void treatment(Patient patient){// ��д������ղ��˷���,���벡�˲���
        System.out.println("�ѽ���:"+this.name+"����");// ��ӡ��ʾ
    }
}
package ��̬.ģ��绰.ϵͳ;

public class CustomerService {// �����ͻ�������
    public Language contact(int areaCode){// �������ŷ���
        if (areaCode==86){// ������ŵ���86
            return new Chinese();// ����ʵ����������
        }else if (areaCode==33){// ������ŵ���33
            return new French();// ����ʵ����������
        }else {// ����
            return new English();// ����ʵ����Ӣ����
        }
    }
}

package ��̬.�̳���ɱģ��;

/**
 * �̳�ϵͳ��
 * ���ʱ��̬:��Ŀ��Ҫ���ͬ��������������ͬʹ�����ʱ��̬
 */
public class Shopping {
    public void seckill(String name,int leixing,String xinxi){// ������ɱ����,������Ʒ����,�ն����ͣ��ն���Ϣ
        System.out.println("������ɱ��Ʒ:"+name);// ��ӡ��ʾ
        if (leixing==1){// ����ն����͵���1
            int shouji=Integer.parseInt(xinxi);// ת��int��,ʹ���ƶ��˷���
            record(name,shouji);// �����ƶ��˷���
        }else if (leixing==2){// ����ն����͵���2
            record(name,xinxi);// ����PC�˷���
        }
    }
    private void record(String name,int shouji){// ������¼�ƶ�����ɱ��Ϣ����,������Ʒ����,�ֻ�����
        System.out.println("��¼--��ɱ��Ʒ:"+name+";�ƶ����ֻ���:"+shouji);// ��ӡ��ʾ
    }
    private void record(String name,String url){// ������¼PC����ɱ��Ϣ����,������Ʒ����,PC�ն���Ϣ
        System.out.println("��¼--��ɱ��Ʒ:"+name+";PC��IP��ַ:"+url);// ��ӡ��ʾ
    }
    public static void main(String[] args) {// ������
        Shopping shopping=new Shopping();// ʵ�����̳���
        shopping.seckill("�ʼǱ�",1,"1581103605");// ������ɱ����
        shopping.seckill("�ֻ�",2,"125.365.124.120");// ������ɱ����
    }
}

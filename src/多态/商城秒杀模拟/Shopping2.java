package ��̬.�̳���ɱģ��;
/**
 * �̳�ϵͳ��
 * ����ʱ��̬:��Ŀ��Ҫ���ͬ���������Ҳ�����ͬʹ������ʱ��̬
 */
public class Shopping2 {
    public void seckill(String name,Device device){// ������ɱ����,������Ʒ����,�豸��Ϣ
        System.out.println("������ɱ��Ʒ:"+name);// �����ʾ
        device.record(name);// �����豸�෽��,�൱�ڴ���Ķ���.record(name);
    }
    public static void main(String[] args) {// ������
        Shopping2 shopping=new Shopping2();// ʵ�����̳���
        Device phone=new Phone();// �豸��ʵ�����ֻ���
        Device pc=new PC();// �豸��ʵ����PC��
        shopping.seckill("�ʼǱ�",phone);// ������ɱ����,�������ƺ��豸������ò�ͬ�����෽��
        shopping.seckill("�ֻ�",pc);// ������ɱ����,�������ƺ��豸������ò�ͬ�����෽��
    }
}
/**
 * �豸��,�������������չ
 */
abstract class Device{
    public abstract void record(String name);// �豸��¼��,������Ʒ����
}
/**
 * �����ֻ���̳��豸��
 */
class Phone extends Device{
    @Override// ��дע��
    public void record(String name) {// ��д�豸�෽��
        System.out.println("�ƶ��˷�����ɱ:"+name);// �����ʾ
    }
}
/**
 * ����PC��̳��豸��
 */
class PC extends Device{
    @Override// ��дע��
    public void record(String name) {// ��д�豸�෽��
        System.out.println("PC�˷�����ɱ:"+name);// �����ʾ
    }
}
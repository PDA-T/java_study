package �߳�.��������;
/**
 * ����������̨��
 */
public class Stage extends Thread{
    @Override// ע��
    public void run() {// ��д�̷߳���
        System.out.println("��ʼ");// ��ӡ��ʾ
        try {// ��׽�쳣
            Thread.sleep(5000);// �߳�����
        } catch (InterruptedException e) {// �����쳣
            e.printStackTrace();// ��ӡ�쳣
        }
        ArmyRunnable armyTaskOfSuiDynasty=new ArmyRunnable();// ʵ����������
        ArmyRunnable armyTaskOfRevolt=new ArmyRunnable();// ʵ������������
        Thread armyOfSuiDynasty=new Thread(armyTaskOfSuiDynasty,"���");// ʵ�����߳�
        Thread armyOfRevolt=new Thread(armyTaskOfRevolt,"�����");// ʵ�������������߳�
        armyOfSuiDynasty.start();// �����߳�
        armyOfRevolt.start();// �����߳�
        try {// ��׽�쳣
            Thread.sleep(50);// �߳�����,�Է�Ӱ������߳�
        } catch (InterruptedException e) {// �����쳣
            e.printStackTrace();// ��ӡ�쳣
        }
        armyTaskOfSuiDynasty.keepRunning=false;// ֹͣѭ��
        armyTaskOfRevolt.keepRunning=false;// ֹͣѭ��
        try {// ��׽�쳣
            armyOfRevolt.join();// ����ִ��
        } catch (InterruptedException e) {// �����쳣
            e.printStackTrace();// ��ӡ�쳣
        }
        System.out.println("�ؼ������ҧ���볡");// ��ӡ��ʾ
        Thread mrCheng=new KeyPersonThread();// �����߳�
        mrCheng.setName("��ҧ��");// �����߳�����
        System.out.println("��ҧ��Ҫ����ս��");// ��ӡ��ʾ
        armyTaskOfSuiDynasty.keepRunning=false;// ֹͣ�߳�
        armyTaskOfRevolt.keepRunning=false;// ֹͣ�߳�
        try {// ��׽�쳣
            Thread.sleep(2000);// �߳�����
        } catch (InterruptedException e) {// �����쳣
            e.printStackTrace();// ��ӡ�쳣
        }
        mrCheng.start();// �����߳�
        try {// ��׽�쳣
            mrCheng.join();// ����ִ��
        } catch (InterruptedException e) {// �����쳣
            e.printStackTrace();// ��ӡ�쳣
        }
        System.out.println("ս������");// ��ӡ��ʾ
        System.out.println("�������");// ��ӡ��ʾ
    }
    public static void main(String[] args) {
        new Stage().start();// ���г���
    }
}

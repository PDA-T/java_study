package �߳�.�̸߳߼�;

/**
 * yield�������õ�ǰ�߳�������״̬���뵽����״̬
 * �������ȴ��е��̻߳�ȡִ��Ȩ,������֤һ�����Ի�ȡִ��Ȩ
 * (�п����˻ؾ���״̬֮��������������Ȩ��)
 */
public class yieldTest {
    public static void main(String[] args) {
        Thread t1 = new PriorityThread("�߳�1");
        Thread t2 = new PriorityThread("�߳�2");
        t1.start();
        t2.start();
    }
}
class PriorityThread extends Thread{
    public PriorityThread(String name){
        // ����������Thread��Ĺ�������
        super(name);
    }
    @Override
    public void run() {
        for (int i=1;i<=50;i++){
            System.out.println(this.getName()+":"+this.getPriority()+":"+i);
            // �������������4
            if (i % 4 == 0){
                // �߳��ò�
                Thread.yield();
            }
        }
    }
}

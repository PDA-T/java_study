package �߳�.��������;

/**
 * �����ؼ�������
 */
public class KeyPersonThread extends Thread{
    @Override// ע��
    public void run() {// ��д�̷߳���
        System.out.println(Thread.currentThread().getName()+"��ʼ��ս��");// ��ӡ��ʾ
        for (int i=0;i<10;i++){// ÿ�η�������10��
            System.out.println(Thread.currentThread().getName()+"���������");// ��ӡ��ʾ
        }
        System.out.println(Thread.currentThread().getName()+"������ս��");// ��ӡ��ʾ
    }
}

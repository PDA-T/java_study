package �߳�.����ת��;

/**
 * ������
 */
public class EnergySystemTest {
    public static final int BOX_AMOUNT=100;// ��Ҫ����������������������������
    public static final double INITIAL_ENERGY=1000;// ÿ�����ӳ�ʼ����
    /**
     * ������
     */
    public static void main(String[] args) {
        // ʵ��������ϵͳ��,�������������ÿ���������ӳ�ʼ���е�����ֵ
        EnergySystem eng=new EnergySystem(BOX_AMOUNT,INITIAL_ENERGY);
        for (int i=0;i<BOX_AMOUNT;i++){// ѭ������
            // ʵ��������ת����,����ʵ��������ϵͳ�����,����ת�Ƶ�Դ���������±�,��������ת�����Ԫ
            EnergyTransferTask task=new EnergyTransferTask(eng,i,INITIAL_ENERGY);
            Thread t=new Thread(task,"TransferThread_"+i);// �����̴߳�������ת�������,�߳�����
            t.start();// �����߳�
        }
    }
}

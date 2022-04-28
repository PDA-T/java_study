package �߳�.����ת��;

/**
 * ����������ת��
 */
public class EnergyTransferTask implements Runnable{
    private EnergySystem energySystem;// ���������
    private int fromBox;// ����ת�Ƶ�Դ���������±�
    private double maxAmount;// ��������ת�����Ԫ
    private int DELAY=10;// �������ʱ��

    /**
     * �������Ը�ֵ
     * @param energySystem ���������
     * @param from ����ת�Ƶ�Դ���������±�
     * @param max ��������ת�����Ԫ
     */
    public EnergyTransferTask(EnergySystem energySystem,int from,double max){
        this.energySystem=energySystem;// �������Ը�ֵΪ����Ĳ���
        this.fromBox=from;// �������Ը�ֵΪ����Ĳ���
        this.maxAmount=max;// �������Ը�ֵΪ����Ĳ���
    }
    @Override// ע��
    public void run() {// ��д�̷߳���
        try {// ��׽�쳣
            while (true){// ����ѭ��
                // ���ӳ���+Ĭ�ϲ������ڵ���0.0��С��1.0֮������double�������,��:0.0<=Math.random()<1.0
                int toBox=(int)(energySystem.getBoxAmount()*Math.random());
                double amount=maxAmount*Math.random();// ��������ת�����Ԫ+�����
                energySystem.transfer(fromBox,toBox,amount);// ��������ת�Ƶ��������ӷ���
                Thread.sleep((int)(DELAY*Math.random()));// ����ʱ��Ϊ�������ʱ��+�����
            }
        }catch (InterruptedException e){// �����쳣
            e.printStackTrace();// ��ӡ�쳣
        }
    }
}

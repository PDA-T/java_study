package �߳�.����ת��;

/**
 * ����ϵͳ��
 */
public class EnergySystem {
    private final double[] energyBoxes;// ��������,��������
    private final Object lockObj=new Object();// ����һ��˽�е���,������
    /**
     * @param n �������ӵ�����
     * @param initialEnergy ÿ���������ӳ�ʼ���е�����ֵ
     */
    public EnergySystem(int n,double initialEnergy){
        energyBoxes=new double[n];// ��ʼ�����ӵ�ֵ
        for (int i=0;i<energyBoxes.length;i++){// ѭ������
            energyBoxes[i]=initialEnergy;// ��ֵ
        }
    }

    /**
     * ����ת�Ƶ���������
     * @param from ����Դ
     * @param to �����յ�
     * @param amount ����ֵ
     */
    public void transfer(int from,int to,double amount){
        synchronized (lockObj){// ����ͬ����,ͬ���߳�
//            if (energyBoxes[from]<amount){// ����������������ԴС��ת��������ֵ
//                return;// ����
//            }
            // ��֤����������ʱ���񶼻ᱻ�����赲,�����Ǽ�������CPU��Դ
            while (energyBoxes[from]<amount){// ����������������ԴС��ת��������ֵ
                try {// �����쳣
                    // С��ת��������ֵ�߳��޷�������²���,����ȴ�״̬,�������̳߳��Բ���
                    lockObj.wait();// ʹ�߳̽���ȴ�״̬
                } catch (InterruptedException e) {// �����쳣
                    e.printStackTrace();// ��ӡ�쳣
                }
            }
            System.out.print(Thread.currentThread().getName());// ��ӡ�߳�����
            energyBoxes[from]-=amount;// ��ȥת������
            System.out.printf("��%dת��%10.2f��λ������%d",from,amount,to);// ��ӡ��ʾ,��ʽ���ģʽ
            energyBoxes[to]+=amount;// ����ת������
            System.out.printf("�����ܺ�:%10.2f%n",getTotalEnergies());// ��ӡ��ʾ
            lockObj.notifyAll();// ����������lockObj�����ϵȴ����߳�
        }
    }
    /**
     * ��ȡ�������ܺ�
     */
    public double getTotalEnergies(){
        double sum=0;// ������ʼֵ
        for (double amount:energyBoxes){// ѭ��������������
            sum+=amount;// ��Ӻ��ӵ�ֵ
        }
        return sum;// ������ֵ
    }
    /**
     * �����������ӵĳ���
     */
    public int getBoxAmount(){
        return energyBoxes.length;// ��������ĳ���
    }
}

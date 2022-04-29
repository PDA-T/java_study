package �߳�.�̸߳߼�;

public class TestVolatile {
    public static void main(String[] args) {
        VolatileThread vt = new VolatileThread();
        new Thread(vt,"�߳�").start();
        while (true){
            // ���flag��true
            if (vt.isFlag()){
                System.out.println("�߳���flagΪtrue");
                // ����ѭ��
                break;
            }
        }
    }
}
class VolatileThread implements Runnable{
    // ���߳��������λ��,���������,������Ա������������̹߳���ı���,Ҫ���ڴ�ɼ��ؼ���
    private volatile boolean flag = false;

    public boolean isFlag() {
        return flag;
    }

    public VolatileThread setFlag(boolean flag) {
        this.flag = flag;
        return this;
    }

    @Override
    public void run() {
        try {
            // �Ŵ��̰߳�ȫ����
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.flag = true;
        System.out.println("�߳��޸�flag��ֵ,true");
    }
}

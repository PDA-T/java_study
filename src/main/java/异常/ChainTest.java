package �쳣;

public class ChainTest {
    public static void main(String[] args) {
        ChainTest ch=new ChainTest();// ʵ��������
        try {// ��׽�쳣
            ch.test2();// �����෽��
        }catch (Exception e){// �����쳣
            e.printStackTrace();// ��ӡ��ɫ�쳣
        }
    }
    public void test() throws DrunkException{// ���������׳��Զ����쳣��
        throw new DrunkException("Exc");// �׳�ʵ�����Զ����쳣�ഫ���вι��췽��
    }
    public void test2(){// ��������
        try {// ��׽�쳣
            test();// ���÷���
        } catch (DrunkException e) {// �����쳣
            RuntimeException Exc=new RuntimeException("Runtime");// ʵ��������ʱ�쳣��
            Exc.initCause(e);// ���쳣���а�װ,��׽ԭʼ�쳣
            throw Exc;// �׳��쳣����
        }
    }
}

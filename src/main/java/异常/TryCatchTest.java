package �쳣;

public class TryCatchTest {
    public static void main(String[] args) {
        //System.out.println(new TryCatchTest().test());// ���е�һ������
        //System.out.println(new TryCatchTest().test2());// ���еڶ�������
        System.out.println(new TryCatchTest().test3());// ���е���������
    }
    public int test(){// ��һ������
        int divider=10;// ����
        int result=100;// ���
        try {// ��׽�쳣
            while (divider>-1){// �����������-1��ʼѭ��
                divider--;// ������1
                result=result+100/divider;// ������ڽ����100���Գ���
            }
            return result;// ���ؽ��
        }catch (Exception e){// �׳��쳣
            e.printStackTrace();// �׳���ɫ�쳣
            System.out.println("Exc");// ��ӡ�쳣
            return -1;// ����-1
        }
    }
    public int test2(){// �ڶ�������
        int divider=10;// ����
        int result=100;// ���
        try {// ��׽�쳣
            while (divider>-1){// �����������-1��ʼѭ��
                divider--;// ������1
                result=result+100/divider;// ������ڽ����100���Գ���
            }
            return result;// ���ؽ��
        }catch (Exception e){// �׳��쳣
            e.printStackTrace();// �׳���ɫ�쳣
            System.out.println("Exc");// ��ӡ�쳣
            return 999;// ����999
        }finally {// ��������
            System.out.println("finally");// ��ӡ����
        }
    }
    public int test3(){// ����������
        int divider=10;// ����
        int result=100;// ���
        try {// ��׽�쳣
            while (divider>-1){// �����������-1��ʼѭ��
                divider--;// ������1
                result=result+100/divider;// ������ڽ����100���Գ���
            }
        }catch (Exception e){// �׳��쳣
            e.printStackTrace();// �׳���ɫ�쳣
            System.out.println("Exc");// ��ӡ�쳣
        }finally {// ��������
            System.out.println("finally");// ��ӡ����
        }
        System.out.println("test3");// ���
        return 1111;// ����1111
    }
}
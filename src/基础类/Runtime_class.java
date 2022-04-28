package ������;

/**
 * �鿴������м�������
 */
public class Runtime_class {
    public static void main(String[] args) {
        java.lang.Runtime rt= java.lang.Runtime.getRuntime();// ����Runtime����
        System.out.println("���Ժ�����:"+rt.availableProcessors());// ���������
        System.out.println("���ڴ��С:"+rt.totalMemory());// ������ڴ�
        // �����ʼ��״̬��,�����õ����ڴ�
        System.out.println("��ʼ��״̬��,���õ����ڴ�:"+rt.freeMemory());
        rt.gc();// ����jvmִ����������
        // �����һ�ν����������պ���õ��ڴ�
        System.out.println("��һ��gc����õ��ڴ�:"+rt.freeMemory());
        // �õ�һ�����ڴ�
        Integer[] arr=new Integer[1000000];
        // ѭ�������������
        for (int i=0;i<1000000;i++){
            arr[i]=new Integer(i);// ��ֵ����
        }
        // ���ʹ��һ�����ڴ����õ��ڴ�
        System.out.println("ʹ�õ������ڴ����õ��ڴ�:"+rt.freeMemory());
        // ��������Ķ���������
        for (int i=0;i<1000000;i++){
            arr[i]=null;// ���������ñ�ɿ�
        }
        rt.gc();// ����jvmִ����������
        // ����ڶ��ν����������պ���õ��ڴ�
        System.out.println("gc����õ��ڴ�:"+rt.freeMemory());
    }
}

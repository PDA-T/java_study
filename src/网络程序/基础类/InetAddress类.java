package �������.������;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * IP��ĸ�������
 */
public class InetAddress�� {
    public static void main(String[] args) {
        try {
            InetAddress local=InetAddress.getLocalHost();// ��ȡ����������ַ
            InetAddress local2=InetAddress.getByName("www.1lin.xyz");// ��ȡ�ȸ�������ַ
            System.out.println("������������Ϣ:"+local);// ���
            // ���getByName��������д��ֵ
            System.out.println("www.1lin.xyz����������:"+local2.getHostName());
            System.out.println("www.1lin.xyz��IP��:"+local2.getHostAddress());// ���IP��ַ
            InetAddress local3[]=InetAddress.getAllByName("www.google.com");// ��ȡ�ȸ�ȫ����ַ
            for (InetAddress ad:local3){// ѭ������
                System.out.println("�ȸ�ȫ��IP��ַ:"+ad.getHostAddress());// �����ַ
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}

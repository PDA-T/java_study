package �������.UDP����;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class UDP���Ͷ� extends Thread{
    int port=9000;// �����˿�
    InetAddress group;// �����㲥���ַ
    MulticastSocket socket;// �����ಥ���ݰ��׽���
    public UDP���Ͷ�(){
        try {
            group=InetAddress.getByName("224.255.10.0");// ָ���㲥���ַ
            socket=new MulticastSocket(port);// ʵ�����ಥ���ݰ��׽���
            socket.joinGroup(group);// ����㲥��
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        while (true) {
            DatagramPacket packet;// �������ݰ�
            String massege = "����";// �����ַ���
            byte data[] = massege.getBytes();// ���ľ�����Ϣ
            packet = new DatagramPacket(data, data.length, group, port);// ʵ�������ݰ�
            try {
                socket.send(packet);// �������ݰ�
                System.out.println(massege);// ���ֵ
                Thread.sleep(1000);// ����
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        UDP���Ͷ� send=new UDP���Ͷ�();// ���������
        send.start();// �����߳�
    }
}

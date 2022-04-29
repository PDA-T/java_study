package �������.UDP����;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class UDP���ն� extends JFrame implements Runnable, ActionListener {
    JButton ince=new JButton("��ʼ����");// ʵ������ʼ���հ�ť
    JButton stop=new JButton("ֹͣ����");// ʵ����ֹͣ���հ�ť
    JTextArea inceAr=new JTextArea(10,10);// �����ı���,ָ������
    JTextArea inced=new JTextArea(10,10);// �����ı���,ָ������
    Thread thread;// �����߳�
    boolean getMessage=true;// �Ƿ���չ㲥
    int port=9000;// �����˿�
    InetAddress group;// �����㲥���ַ
    MulticastSocket socket;// �����ಥ���ݰ��׽���
    public UDP���ն�(){// ���췽��
        setTitle("���չ㲥");// ���ô������
        setDefaultCloseOperation(EXIT_ON_CLOSE);// ���ô���رռ���������
        inceAr.setForeground(Color.blue);// �����ı���������ɫ
        JPanel north=new JPanel();// ʵ��������
        north.add(ince);// ��ӿ�ʼ���հ�ť
        north.add(stop);// ���ֹͣ���հ�ť
        add(north,BorderLayout.NORTH);// �������,���������ڴ��嶥��(�߽粼��)
        JPanel center=new JPanel();// ʵ��������
        center.setLayout(new GridLayout(1,2));// �����������񲼾�
        center.add(inceAr);// ����ı�
        JScrollPane scrollPane=new JScrollPane();// ���ù�����
        center.add(scrollPane);// ������ӹ�����
        scrollPane.setViewportView(inced);// ���ı�����ӹ�����
        add(center,BorderLayout.CENTER);// �������,���������ڴ�������(�߽粼��)
        validate();// ˢ�´����е����
        setSize(360,260);// ���ô����С
        setLocationRelativeTo(null);// ���ô������
        setVisible(true);// ��ʾ����
        thread=new Thread(this);// ʵ�����߳�
        try {
            group=InetAddress.getByName("224.255.10.0");// ָ���㲥���ַ
            socket=new MulticastSocket(port);// ʵ�����ಥ���ݰ��׽���
            socket.joinGroup(group);// ����㲥��
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ince.addActionListener(this);// ע�����
        stop.addActionListener(this);// ע�����
    }
    @Override
    public void actionPerformed(ActionEvent e) {// �����ť����
        if (e.getSource()==ince){// �ж��¼�������
            ince.setBackground(Color.red);// ���ð�ťΪ��ɫ
            stop.setBackground(Color.yellow);// ���ð�ťΪ��ɫ
            if (!thread.isAlive()){// �ж��߳��Ƿ��ڻ�Ծ״̬
                thread=new Thread(this);// ʵ�����߳�
                getMessage=true;// ���ý��չ㲥Ϊtrue
            }
            thread.start();// �����߳�
        }
        if (e.getSource()==stop){// �ж��¼�������
            ince.setBackground(Color.yellow);// ���ð�ťΪ��ɫ
            stop.setBackground(Color.red);// ���ð�ťΪ��ɫ
            getMessage=false;// ���ý��չ㲥Ϊfalse
        }
    }

    @Override
    public void run() {// �̷߳���
        while (getMessage){// �ж�
            DatagramPacket packet;// �������ݰ�
            byte data[]=new byte[1024];// �������ݰ���ֵ
            packet=new DatagramPacket(data,data.length,group,port);// ʵ�������ݰ�
            try {
                socket.receive(packet);// ��ȡ���ݰ�
                String message=new String(packet.getData(),0,packet.getLength());// �����ݰ��ж�ȡ���ݱ�Ϊ�ַ���
                inceAr.setText("���ڽ���:"+message);// �������
                inced.append(message+"\n");// ���б�ĩβ׷���µĶ���
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {// ������
        UDP���ն� rec=new UDP���ն�();// ʵ��������
        rec.setSize(460,200);// ���ô�С
    }
}

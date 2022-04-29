package 网络程序.UDP程序;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class UDP接收端 extends JFrame implements Runnable, ActionListener {
    JButton ince=new JButton("开始接收");// 实例化开始接收按钮
    JButton stop=new JButton("停止接收");// 实例化停止接收按钮
    JTextArea inceAr=new JTextArea(10,10);// 创建文本域,指定行列
    JTextArea inced=new JTextArea(10,10);// 创建文本域,指定行列
    Thread thread;// 创建线程
    boolean getMessage=true;// 是否接收广播
    int port=9000;// 创建端口
    InetAddress group;// 创建广播组地址
    MulticastSocket socket;// 创建多播数据包套接字
    public UDP接收端(){// 构造方法
        setTitle("接收广播");// 设置窗体标题
        setDefaultCloseOperation(EXIT_ON_CLOSE);// 设置窗体关闭即结束运行
        inceAr.setForeground(Color.blue);// 设置文本域字体颜色
        JPanel north=new JPanel();// 实例化容器
        north.add(ince);// 添加开始接收按钮
        north.add(stop);// 添加停止接收按钮
        add(north,BorderLayout.NORTH);// 添加容器,设置容器在窗体顶部(边界布局)
        JPanel center=new JPanel();// 实例化容器
        center.setLayout(new GridLayout(1,2));// 设置容器网格布局
        center.add(inceAr);// 添加文本
        JScrollPane scrollPane=new JScrollPane();// 设置滚动条
        center.add(scrollPane);// 容器添加滚动条
        scrollPane.setViewportView(inced);// 给文本域添加滚动条
        add(center,BorderLayout.CENTER);// 添加容器,设置容器在窗体中心(边界布局)
        validate();// 刷新窗体中的组件
        setSize(360,260);// 设置窗体大小
        setLocationRelativeTo(null);// 设置窗体居中
        setVisible(true);// 显示窗体
        thread=new Thread(this);// 实例化线程
        try {
            group=InetAddress.getByName("224.255.10.0");// 指定广播组地址
            socket=new MulticastSocket(port);// 实例化多播数据包套接字
            socket.joinGroup(group);// 加入广播组
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ince.addActionListener(this);// 注册组件
        stop.addActionListener(this);// 注册组件
    }
    @Override
    public void actionPerformed(ActionEvent e) {// 点击按钮触发
        if (e.getSource()==ince){// 判断事件发起者
            ince.setBackground(Color.red);// 设置按钮为红色
            stop.setBackground(Color.yellow);// 设置按钮为黄色
            if (!thread.isAlive()){// 判断线程是否处于活跃状态
                thread=new Thread(this);// 实例化线程
                getMessage=true;// 设置接收广播为true
            }
            thread.start();// 启动线程
        }
        if (e.getSource()==stop){// 判断事件发起者
            ince.setBackground(Color.yellow);// 设置按钮为黄色
            stop.setBackground(Color.red);// 设置按钮为红色
            getMessage=false;// 设置接收广播为false
        }
    }

    @Override
    public void run() {// 线程方法
        while (getMessage){// 判断
            DatagramPacket packet;// 创建数据包
            byte data[]=new byte[1024];// 保存数据包的值
            packet=new DatagramPacket(data,data.length,group,port);// 实例化数据包
            try {
                socket.receive(packet);// 读取数据包
                String message=new String(packet.getData(),0,packet.getLength());// 从数据包中读取数据变为字符串
                inceAr.setText("正在接收:"+message);// 输出数据
                inced.append(message+"\n");// 在列表末尾追加新的对象
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {// 主方法
        UDP接收端 rec=new UDP接收端();// 实例化主类
        rec.setSize(460,200);// 设置大小
    }
}

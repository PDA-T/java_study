package 网络程序.UDP多点广播;

import javax.swing.*;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class ComUtil {
    // 定义本程序通信所使用的字符集
    public static final String CHARSET = "utf-8";
    // 使用常量作为本程序的多点广播IP地址
    private static final String BROADCAST_IP = "230.0.0.1";
    // 使用常量作为本程序的多点广播目的的端口
    // DatagramSocket所用的的端口为该端口+1。
    public static final int BROADCAST_PORT = 30000;
    // 定义每个数据报的最大大小为4K
    private static final int DATA_LEN = 4096;
    // 定义本程序的MulticastSocket实例
    private MulticastSocket socket = null;
    // 定义本程序私聊的Socket实例
    private DatagramSocket singleSocket = null;
    // 定义广播的IP地址
    private InetAddress broadcastAddress = null;
    // 定义接收网络数据的字节数组
    byte[] inBuff = new byte[DATA_LEN];
    // 以指定字节数组创建准备接受数据的DatagramPacket对象
    private DatagramPacket inPacket = new DatagramPacket(inBuff, inBuff.length);
    // 定义一个用于发送的DatagramPacket对象
    private DatagramPacket outPacket = null;
    // 聊天的主界面程序
    private LanTalk lanTalk;

    // 构造器，初始化资源
    public ComUtil(LanTalk lanTalk) throws Exception {
        this.lanTalk = lanTalk;
        // 创建用于发送、接收数据的MulticastSocket对象
        // 因为该MulticastSocket对象需要接收，所以有指定端口
        socket = new MulticastSocket(BROADCAST_PORT);
        // 创建私聊用的DatagramSocket对象
        singleSocket = new DatagramSocket(BROADCAST_PORT + 1);
        broadcastAddress = InetAddress.getByName(BROADCAST_IP);
        // 将该socket加入指定的多点广播地址
        socket.joinGroup(broadcastAddress);
        // 设置本MulticastSocket发送的数据报被回送到自身
        socket.setLoopbackMode(false);
        // 初始化发送用的DatagramSocket，它包含一个长度为0的字节数组
        outPacket = new DatagramPacket(new byte[0]
                , 0, broadcastAddress, BROADCAST_PORT);
        // 启动两个读取网络数据的线程
        new ReadBroad().start();
        Thread.sleep(1);
        new ReadSingle().start();
    }

    //实现广播信息
    // 广播消息的工具方法
    public void broadCast(String msg) {
        try {
            // 将msg字符串转换字节数组
            byte[] buff = msg.getBytes(CHARSET);
            // 设置发送用的DatagramPacket里的字节数据
            outPacket.setData(buff);
            // 发送数据报
            socket.send(outPacket);
        }
        // 捕捉异常
        catch (IOException ex) {
            ex.printStackTrace();
            if (socket != null) {
                // 关闭该Socket对象
                socket.close();
            }
            JOptionPane.showMessageDialog(null
                    , "发送信息异常，请确认30000端口空闲，且网络连接正常！"
                    , "网络异常", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    //发送私聊信息
    public void sendSingle(String msg, SocketAddress dest) {
        try {
            // 将msg字符串转换字节数组
            byte[] buff = msg.getBytes(CHARSET);
            DatagramPacket packet = new DatagramPacket(buff
                    , buff.length, dest);
            singleSocket.send(packet);
        }
        // 捕捉异常
        catch (IOException ex) {
            ex.printStackTrace();
            if (singleSocket != null) {
                // 关闭该Socket对象
                singleSocket.close();
            }
            JOptionPane.showMessageDialog(null
                    , "发送信息异常，请确认30001端口空闲，且网络连接正常！"
                    , "网络异常", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    //接收广播信息的线程类
    class ReadBroad extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    // 读取Socket中的数据。
                    socket.receive(inPacket);
                    //从socket中读取的内容
                    String msg = new String(inBuff, 0, inPacket.getLength(), CHARSET);
                    //msg有类型的，用户信息，公聊聊天信息
                    if (msg.startsWith(ChatProtocol.PRESENCE)
                            && msg.endsWith(ChatProtocol.PRESENCE)) {
                        //用户信息
                        String userMsg = msg.substring(2, msg.length() - 2);
                        String[] userInfo = userMsg.split(ChatProtocol.SPLITTER);
                        UserInfo user = new UserInfo(userInfo[1]
                                , userInfo[0], inPacket.getSocketAddress(), 0);
                        // 控制是否需要添加该用户的标记
                        boolean addFlag = true;
                        //下面的集合里要放的数据是，后面马上要从列表中删除的用户数据
                        ArrayList<Integer> delList = new ArrayList<>();
                        //循环遍历所有在列表中的用户
                        for (int i = 1; i < lanTalk.getUserNum(); i++) {
                            UserInfo current = lanTalk.getUser(i);
                            // 将所有用户失去联系的次数加1
                            current.setLost(current.getLost() + 1);
                            if (current.equals(user)) {
                                //收到的广播信息对应的用户已经在我的列表中
                                current.setLost(0);
                                addFlag = false;
                            }
                            if (current.getLost() > 2) {
                                delList.add(i);
                            }
                        }
                        // 删除delList中的所有索引对应的用户
                        for (int i = 0; i < delList.size(); i++) {
                            lanTalk.removeUser(delList.get(i));
                        }
                        if (addFlag) {
                            // 添加新用户
                            lanTalk.addUser(user);
                        }
                    } else {
                        //公聊聊天信息
                        lanTalk.processMsg(inPacket, false);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    //接收私聊信息的线程类
    class ReadSingle extends Thread {
        // 定义接收网络数据的字节数组
        byte[] singleBuff = new byte[DATA_LEN];
        private DatagramPacket singlePacket =
                new DatagramPacket(singleBuff, singleBuff.length);

        @Override
        public void run() {
            while (true) {
                try {
                    // 读取Socket中的数据。
                    singleSocket.receive(singlePacket);
                    // 处理读到的信息
                    lanTalk.processMsg(singlePacket, true);
                }
                // 捕捉异常
                catch (IOException ex) {
                    ex.printStackTrace();
                    if (singleSocket != null) {
                        // 关闭该Socket对象
                        singleSocket.close();
                    }
                    JOptionPane.showMessageDialog(null
                            , "接收信息异常，请确认30001端口空闲，且网络连接正常！"
                            , "网络异常", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                }
            }
        }
    }


}

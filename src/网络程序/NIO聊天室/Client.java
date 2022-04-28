package 网络程序.NIO聊天室;

import javax.swing.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.Set;

/**
 * 客户端
 */
public class Client {
    // 服务器端口
    private static final int SERVER_PORT = 8888;
    // 字符集编码器
    private Charset charset = Charset.forName("GBK");
    // 读缓冲区
    private ByteBuffer rbuffer = ByteBuffer.allocate(1024);
    // 写缓冲区
    private ByteBuffer wbuffer = ByteBuffer.allocate(1024);
    // 选择器
    private Selector selector;
    // 第一次登陆失败后的提示
    private String tip = "";
    /**
     * 初始化客户端
     */
    private void init(){
        try {
            // 获取套接字通道
            SocketChannel clientChannel = SocketChannel.open();
            // 设置非阻塞
            clientChannel.configureBlocking(false);
            // 获取选择器
            selector = Selector.open();
            // 注册进选择器,设置兴趣事件为连接
            clientChannel.register(selector, SelectionKey.OP_CONNECT);
            // 连接服务器
            clientChannel.connect(new InetSocketAddress("127.0.0.1",SERVER_PORT));
            // 循环监听事件
            while (true){
                // 查看注册到选择器的通道是否准备好
                int count = selector.select(5000);
                // 如果等于0表示没有通道准备好
                if (count == 0){
                    // 重新循环
                    continue;
                }
                // 拿到准备好通道的集合
                Set<SelectionKey> keys = selector.selectedKeys();
                // 遍历准备好的通道
                for (SelectionKey key:keys){
                    // 处理准备就绪的通道
                    handler(key);
                    // 从集合中移除处理完成的通道
                    keys.remove(key);
                }
            }
        } catch (IOException e) {
            System.out.println("连接服务器失败,请查看网络是否连接");
        }
    }
    /**
     * 处理事件
     */
    private void handler(SelectionKey key){
        // 如果是连接事件准备就绪
        if (key.isConnectable()){
            try {
                // 拿到通道
                SocketChannel client = (SocketChannel) key.channel();
                // 判断客户端是否正在连接
                if (client.isConnectionPending()){
                    // 如果已经连接上服务器返回true,没有连接上会报错
                    client.finishConnect();
                    System.out.println("连接成功");
                    // 登陆
                    login(client,tip);
                    // 创建子线程,负责获取键盘信息发送给服务器
                    new Thread(new ClientThread(client)).start();
                    // 改变选择器对通道的兴趣事件为读
                    client.register(selector,SelectionKey.OP_READ);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
            // 如果是读事件就绪(服务器发送信息过来)
        }else if (key.isReadable()){
            try {
                // 拿到通道
                SocketChannel client = (SocketChannel) key.channel();
                // 清空读缓冲区
                rbuffer.clear();
                // 读取缓冲区
                int count = client.read(rbuffer);
                // 如果大于0表示有东西读取进来
                if (count>0){
                    // 取出数据
//                    String str = String.valueOf(charset.decode(rbuffer));// 编码问题报错
                    String str = new String(rbuffer.array(),0,count,"GBK");
                    // 如果消息内包含登陆协议字符
                    if (str.startsWith(ChatRoomProtocol.USER_ROUND) &&
                        str.endsWith(ChatRoomProtocol.USER_ROUND)){
                        // 去除前后缀
                        String loginRes = getRealMsg(str);
                        // 如果等于-1则表示重名
                        if (loginRes.equals(ChatRoomProtocol.NAME_REP)){
                            tip = "用户名重复,请重新输入";
                            // 登陆
                            login(client,tip);
                            // 如果是登陆信息
                        }else if (loginRes.equals(ChatRoomProtocol.LOGIN_SUCCESS)){
                            System.out.println("客户端登陆成功");
                        }
                        // 发送的是聊天信息
                    }else {
                        // 输出信息
                        System.out.println(str);
                    }
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    /**
     * 登陆
     */
    private void login(SocketChannel client,String tip){
        try {
            // 弹出提示框,获取输入的值
            String userName = JOptionPane.showInputDialog(tip+"请输入用户名:");
            // 清空写缓冲区
            wbuffer.clear();
            // 写入缓冲区
            wbuffer.put(charset.encode(ChatRoomProtocol.USER_ROUND+userName+ ChatRoomProtocol.USER_ROUND));
            // 反转缓冲区
            wbuffer.flip();
            // 判断缓冲区是否有数据
            while (wbuffer.hasRemaining()){
                // 把名字发送到服务器
                client.write(wbuffer);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    /**
     * 创建内部线程类
     */
    private class ClientThread implements Runnable{
        // 拿到套接字通道
        private SocketChannel client;
        // 构造器
        public ClientThread(SocketChannel client){
            // 初始化属性
            this.client = client;
        }

        @Override
        public void run() {
            // 循环获取输入的内容
            while (true){
                try {
                    // 创建扫描器
                    Scanner scanner = new Scanner(System.in);
                    // 保存输入的内容
                    String line = scanner.nextLine();
                    /*
                     * 判断是私聊信息还是公共信息
                     * 发送的信息如果有冒号,并且是//开头,表示私聊信息
                     */
                    if (line.indexOf(":")>0 && line.startsWith("//")){
                        // 去除斜杠
                        line = line.substring(2);
                        // 清空写缓冲区
                        wbuffer.clear();
                        // 写入缓冲区
                        wbuffer.put(charset.encode(ChatRoomProtocol.PRIVATEMSG_ROUND+
                                line.split(":")[0]+ChatRoomProtocol.SPLIT_SIGN+
                                line.split(":")[1]+ChatRoomProtocol.PRIVATEMSG_ROUND));
                        // 反转写缓冲区
                        wbuffer.flip();
                        // 如果缓冲区有数据
                        while (wbuffer.hasRemaining()){
                            // 写入通道
                            client.write(wbuffer);
                        }
                        // 公共信息
                    }else {
                        // 清空写缓冲区
                        wbuffer.clear();
                        // 写入缓冲区
                        wbuffer.put(charset.encode(ChatRoomProtocol.PRIVATEMSG_ROUND+
                                line+ChatRoomProtocol.PRIVATEMSG_ROUND));
                        // 反转写缓冲区
                        wbuffer.flip();
                        // 如果缓冲区有数据
                        while (wbuffer.hasRemaining()){
                            // 写入通道
                            client.write(wbuffer);
                        }
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 去除协议前后缀
     */
    private String getRealMsg(String lines){
        // 从协议长度(2)开始截取,截取到总字符串长度减去协议长度(2)
        return lines.substring(ChatRoomProtocol.PROTOCOL_LEN,lines.length()-ChatRoomProtocol.PROTOCOL_LEN);
    }

    public static void main(String[] args) {
        // 创建客户端
        Client client = new Client();
        // 初始化客户端
        client.init();
    }
}

package 网络程序.AIO聊天室;

import 网络程序.NIO聊天室.ChatRoomProtocol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 客户端
 */
public class Client {
    // 服务器端口
    private static final int SERVER_PORT = 31000;
    // 字符集编码
    private static final String CHARSET = "UTF-8";
    // 字符集
    private Charset charset = Charset.forName(CHARSET);
    // 与服务器连接的通道
    AsynchronousSocketChannel clientChannel;
    // 主窗体
    JFrame mainWin = new JFrame("聊天室");
    // 文本域,16行48列
    JTextArea jta = new JTextArea(16,48);
    // 文本框
    JTextField jtf = new JTextField(40);
    // 按钮
    JButton sendBtn = new JButton("发送");
    // 登陆的提示
    String tip = "";
    // 写缓冲区
    ByteBuffer wbuffer = ByteBuffer.allocate(1024);
    // 读缓冲区
    ByteBuffer rbuffer = ByteBuffer.allocate(1024);
    // 初始化客户端
    public void init(){
        // 设置布局为线性布局
        mainWin.setLayout(new BorderLayout());
        // 关闭文本域的编辑权限
        jta.setEditable(false);
        // 给文本域添加滚动条,居中对齐
        mainWin.add(new JScrollPane(jta),BorderLayout.CENTER);
        // 创建一个容器,放文本框和按钮
        JPanel jp = new JPanel();
        // 放入文本框
        jp.add(jtf);
        // 放入按钮
        jp.add(sendBtn);
        // 按钮监听事件
        Action sendAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取文本框写入的内容
                String content = jtf.getText();
                // 判断文本框前后空格去掉之后大于0(说明写了内容)
                if (content.trim().length()>0){
                    // 去除空格
                    content = content.trim();
                    try {
                        /*
                         * 判断是私聊信息还是公共信息
                         * 发送的信息如果有冒号,并且是//开头,表示私聊信息
                         */
                        if (content.indexOf(":")>0 && content.startsWith("//")){
                            // 去除斜杠
                            content = content.substring(2);
                            // 清空写缓冲区
                            wbuffer.clear();
                            // 写入缓冲区
                            wbuffer.put(charset.encode(ChatRoomProtocol.PRIVATEMSG_ROUND+
                                    content.split(":")[0]+ChatRoomProtocol.SPLIT_SIGN+
                                    content.split(":")[1]+ChatRoomProtocol.PRIVATEMSG_ROUND));
                            // 反转写缓冲区
                            wbuffer.flip();
                            // 写入通道
                            clientChannel.write(wbuffer).get();
                            // 公共信息
                        }else {
                            // 清空写缓冲区
                            wbuffer.clear();
                            // 写入缓冲区
                            wbuffer.put(charset.encode(ChatRoomProtocol.PRIVATEMSG_ROUND+
                                    content+ChatRoomProtocol.PRIVATEMSG_ROUND));
                            // 反转写缓冲区
                            wbuffer.flip();
                            // 写入通道
                            clientChannel.write(wbuffer).get();
                        }
                    }catch (Exception ex){
                        System.out.println("发送出错");
                    }
                }
                // 清空文本框内容
                jtf.setText("");
            }
        };
        // 添加按钮事件
        sendBtn.addActionListener(sendAction);
        // 添加快捷键回车
        jtf.getInputMap().put(KeyStroke.getKeyStroke('\n'),"send");
        // 绑定事件
        jtf.getActionMap().put("send",sendAction);
        // 将容器放入主窗体,放入主窗体南边(下边)
        mainWin.add(jp,BorderLayout.SOUTH);
        // 设置点击关闭
        mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 自动调整窗体大小到包裹组件
        mainWin.pack();
        // 窗体居中
        mainWin.setLocationRelativeTo(null);
        // 设置窗体可见
        mainWin.setVisible(true);
    }
    // 连接服务器
    public void connect(){
        try {
            // 线程池
            ExecutorService executorService =
                    Executors.newFixedThreadPool(80);
            // 通道组
            AsynchronousChannelGroup channelGroup =
                    AsynchronousChannelGroup.withThreadPool(executorService);
            // 获取客户端套接字通道
            clientChannel = AsynchronousSocketChannel.open(channelGroup);
            // 连接服务器,阻塞到连接上为止
            clientChannel.connect(new InetSocketAddress("127.0.0.1",SERVER_PORT)).get();
            System.out.println("连接成功");
            // 登陆
            login(clientChannel,tip);
            // 清空读缓冲区
            rbuffer.clear();
            // 读取缓冲区
            clientChannel.read(rbuffer, null, new CompletionHandler<Integer, Object>() {
                @Override
                public void completed(Integer result, Object attachment) {
                    // 反转缓冲区
                    rbuffer.flip();
                    // 拿到缓冲区的数据
                    String content = charset.decode(rbuffer).toString();
                    // 如果消息内包含登陆协议字符
                    if (content.startsWith(ChatRoomProtocol.USER_ROUND) &&
                            content.endsWith(ChatRoomProtocol.USER_ROUND)){
                        // 去除前后缀
                        String loginRes = getRealMsg(content);
                        // 如果等于-1则表示重名
                        if (loginRes.equals(ChatRoomProtocol.NAME_REP)){
                            tip = "用户名重复,请重新输入";
                            // 登陆
                            login(clientChannel,tip);
                            // 如果是登陆信息
                        }else if (loginRes.equals(ChatRoomProtocol.LOGIN_SUCCESS)){
                            System.out.println("客户端登陆成功");
                        }
                        // 发送的是聊天信息
                    }else {
                        // 输出信息
                        jta.append(content + "\n");
                    }
                    // 清空读缓冲区
                    rbuffer.clear();
                    // 递归,重新读取
                    clientChannel.read(rbuffer,null,this);
                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    System.out.println("读取失败:" + exc);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 登陆方法
    private void login(AsynchronousSocketChannel client,String tip){
        try {
            // 弹出提示框,获取输入的值
            String userName = JOptionPane.showInputDialog(tip+"请输入用户名:");
            // 主窗体加上用户名
            mainWin.setTitle("聊天室:" + userName);
            // 清空写缓冲区
            wbuffer.clear();
            // 写入缓冲区
            wbuffer.put(charset.encode(ChatRoomProtocol.USER_ROUND+userName+ChatRoomProtocol.USER_ROUND));
            // 反转缓冲区
            wbuffer.flip();
            // 把名字发送到服务器
            client.write(wbuffer).get();
        }catch (Exception e){
            e.printStackTrace();
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
        // 初始化方法
        client.init();
        // 连接服务器
        client.connect();
    }
}

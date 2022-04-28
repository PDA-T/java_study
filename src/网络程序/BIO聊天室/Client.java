package 网络程序.BIO聊天室;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 客户端
 */
public class Client {
    // 连接服务器端口号
    private static final int SERVER_PORT = 40000;
    // 打印流
    private PrintStream ps = null;
    // 服务器输入流
    private BufferedReader inServer = null;
    // 键盘输入流
    private BufferedReader inKey = null;
    // 连接服务器的套接字
    private Socket socket = null;

    /**
     * 初始化方法
     * 连接服务器
     */
    public void init(){
        try {
            // 初始化键盘输入流
            inKey = new BufferedReader(new InputStreamReader(System.in,"GBK"));
            // 连接到服务器
            socket = new Socket("127.0.0.1",SERVER_PORT);
            // 获取对应的输出流
            ps = new PrintStream(socket.getOutputStream(),true,"GBK");
            // 获取对应的输入流
            inServer = new BufferedReader(new InputStreamReader(socket.getInputStream(),"GBK"));
            // 提示标签
            String tip = "";
            // 循环服务器登陆
            while (true){
                // 弹出提示框,获取输入的值
                String userName = JOptionPane.showInputDialog(tip+"请输入用户名:");
                // 把用户名发送给服务器(加上用户名协议字符)
                ps.println(ChatRoomProtocol.USER_ROUND+userName+ChatRoomProtocol.USER_ROUND);
                // 获取服务器响应的值是否是-1
                String res = inServer.readLine();
                // 如果等于-1
                if (res.equals(ChatRoomProtocol.NAME_REP)){
                    // 名字重复
                    tip = "用户名重复,";
                    // 结束当前循环
                    continue;
                }
                // 如果等于1
                if (res.equals(ChatRoomProtocol.LOGIN_SUCCESS)){
                    // 结束循环
                    break;
                }
            }
        }catch (UnknownHostException e){
            // 服务器没有启动
            System.out.println("无法找到服务器,请查看服务器是否启动");
            // 关闭资源
            closeRes();
            // 退出系统
            System.exit(1);
        }catch (IOException e){
            // 网络异常
            System.out.println("请查看是否连接网络");
            // 关闭资源
            closeRes();
            // 退出系统
            System.exit(1);
        }
        // 启动线程,获取服务器的响应信息
        new Thread(new ClientThread(inServer)).start();
    }

    /**
     * 客户端获取键盘上的信息并且发送给服务器
     */
    private void readAndSend(){
        try {
            // 输入的信息
            String line = null;
            // 循环获取键盘上的信息
            while ((line=inKey.readLine())!=null){
                /*
                 * 判断是私聊信息还是公共信息
                 * 发送的信息如果有冒号,并且是//开头,表示私聊信息
                 */
                if (line.indexOf(":")>0 && line.startsWith("//")){
                    // 去除斜杠
                    line = line.substring(2);
                    // 拼接发送用户名和消息(服务端会自动拆分)
                    ps.println(ChatRoomProtocol.PRIVATEMSG_ROUND+line.split(":")[0]+
                            ChatRoomProtocol.SPLIT_SIGN+line.split(":")[1]+
                            ChatRoomProtocol.PRIVATEMSG_ROUND);
                }else {
                    // 发送公共信息
                    ps.println(ChatRoomProtocol.PUBLICMSG_ROUND+line+ChatRoomProtocol.PUBLICMSG_ROUND);
                }
            }
        }catch (IOException e){
            // 网络异常
            System.out.println("网络通信异常,检查网络是否连接");
            // 关闭资源
            closeRes();
            // 退出系统
            System.exit(1);
        }
    }

    /**
     * 关闭资源
     */
    private void closeRes(){
        try {
            if (inKey!=null){
                inKey.close();
            }
            if (inServer!=null){
                inServer.close();
            }
            if(ps!=null){
                ps.close();
            }
            if (socket!=null){
                socket.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 创建客户端类
        Client client = new Client();
        // 初始化连接服务端
        client.init();
        // 开启输入信息
        client.readAndSend();
    }
}

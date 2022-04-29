package 网络程序.BIO聊天室;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * 服务端线程类
 * 处理与客户端直接的数据交换
 */
public class ServerThread implements Runnable{
    // 与客户端对应的那个套接字
    private Socket socket = null;
    // 创建缓冲字符流
    BufferedReader br = null;
    // 创建打印输出流
    PrintStream ps = null;
    public ServerThread(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            // 获取客户端对应的输入流
            br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"GBK"));
            // 获取客户端对应的输出流
            ps = new PrintStream(socket.getOutputStream(),true,"GBK");
            // 通过输入流接收的数据
            String lines = null;
            // 循环读取客户端发送的信息
            while ((lines=br.readLine())!=null){
                // 读取客户端发来的用户名(判断协议里面的前后缀是否是用户名)
                if (lines.startsWith(ChatRoomProtocol.USER_ROUND) && lines.endsWith(ChatRoomProtocol.USER_ROUND)){
                    // 去掉前后缀,拿到真正的名称
                    String userName = getRealMsg(lines);
                    // 判断用户名是否重复
                    if (Server.clients.map.containsKey(userName)){
                        System.out.println("用户名重复");
                        // 返回-1
                        ps.println(ChatRoomProtocol.NAME_REP);
                    }else {
                        System.out.println("用户登陆成功");
                        // 返回1
                        ps.println(ChatRoomProtocol.LOGIN_SUCCESS);
                        // 保存用户名和对应的输出流
                        Server.clients.put(userName,ps);
                    }
                    // 如果是私聊前后缀
                }else if (lines.startsWith(ChatRoomProtocol.PRIVATEMSG_ROUND) && lines.endsWith(ChatRoomProtocol.PRIVATEMSG_ROUND)){
                    // 拿到信息(包含目标用户和消息),去掉前后缀
                    String userAndMsg = getRealMsg(lines);
                    // 使用分割字符隔开,用户名
                    String targetUser = userAndMsg.split(ChatRoomProtocol.SPLIT_SIGN)[0];
                    // 消息内容
                    String privatemsg = userAndMsg.split(ChatRoomProtocol.SPLIT_SIGN)[1];
                    // 通过用户名获取到对应的输出流,显示发送对象
                    Server.clients.map.get(targetUser).println(Server.clients.getKeyByValue(ps)+"私聊:"+privatemsg);
                // 公共信息
                }else {
                    // 拿到信息,去除前后缀
                    String publicmsg = getRealMsg(lines);
                    // 进行广播(所有人可见)
                    for (PrintStream clintsPs:Server.clients.getValueSet()){
                        // 发送
                        clintsPs.println(Server.clients.getKeyByValue(ps)+"说:"+publicmsg);
                    }
                }
            }
        }catch (Exception e){
            // 服务器和客户端的套接字发生数据交换异常,从集合删除此用户
            Server.clients.removeByValue(ps);
            // 输出服务器用户集合个数
            System.out.println(Server.clients.map.size());
            try {
                // 关闭资源
                if (br!=null){
                    br.close();
                }
                if (ps!=null){
                    ps.close();
                }
                if (socket!=null){
                    socket.close();
                }
            }catch (Exception ex){
                ex.printStackTrace();
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
}

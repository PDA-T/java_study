package 网络程序.NIO聊天室;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Set;

/**
 * 服务端
 */
public class Server {
    // 服务端端口号
    public static final int SERVER_PORT = 8888;
    // 字符编码器
    private Charset charset = Charset.forName("GBK");
    // 读缓冲区
    private ByteBuffer readBuffer = ByteBuffer.allocate(1024);
    // 写缓冲区
    private ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
    // 用户集合,存放连接进来的用户端
    private static ChatRoomMap<String, SocketChannel> clients = new ChatRoomMap<>();
    // 选择器
    private Selector selector;
    /**
     * 服务器初始化
     */
    private void init(){
        try {
            // 拿到服务器套接字通道
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            // 设置非阻塞模式
            serverSocketChannel.configureBlocking(false);
            // 通过通道拿到服务器套接字
            ServerSocket serverSocket = serverSocketChannel.socket();
            // 绑定地址和端口
            serverSocket.bind(new InetSocketAddress("127.0.0.1",SERVER_PORT));
            // 初始化选择器
            selector = Selector.open();
            // 设置感兴趣的事件(等待客户端接入事件)
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            System.out.println("服务器启动失败,请检查端口号");
        }
    }
    /**
     * 循环监听通道
     */
    public void listen(){
        while (true){
            try {
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 处理准备就绪的通道
     */
    private void handler(SelectionKey key){
        // 如果接入事件准备就绪
        if (key.isAcceptable()){
            try {
                // 拿到对应的通道
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                // 接入客户端,获取和客户端一一对应的客户端套接字通道
                SocketChannel clientSocketChannel = serverSocketChannel.accept();
                // 设置为非阻塞
                clientSocketChannel.configureBlocking(false);
                // 注册进选择器,设置兴趣为读
                clientSocketChannel.register(selector,SelectionKey.OP_READ);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 如果为读就绪准备好(客户端发信息来)
        }else if (key.isReadable()){
            // 拿到通道
            SocketChannel clientSocketChannel = (SocketChannel) key.channel();
            try {
                // 清空读缓冲区
                readBuffer.clear();
                // 把数据写入缓冲区
                int hasRead = clientSocketChannel.read(readBuffer);
                // 如果大于0表示有数据进入缓冲区
                if (hasRead>0){
                    // 反转缓冲区
                    readBuffer.flip();
                    // 获取内容
                    String str = new String(readBuffer.array(),0,hasRead,"GBK");
                    // 输出内容
                    System.out.println(str);
                    // 读取客户端发来的用户名(判断协议里面的前后缀是否是用户名)
                    if (str.startsWith(ChatRoomProtocol.USER_ROUND) && str.endsWith(ChatRoomProtocol.USER_ROUND)){
                        // 用户登陆
                        login(clientSocketChannel,str);
                        // 如果是私聊前后缀
                    }else if (str.startsWith(ChatRoomProtocol.PRIVATEMSG_ROUND) && str.endsWith(ChatRoomProtocol.PRIVATEMSG_ROUND)){
                        // 发送私聊消息
                        sendMsyToUser(clientSocketChannel,str);
                        // 如果是公共信息
                    }else if (str.startsWith(ChatRoomProtocol.PUBLICMSG_ROUND) && str.endsWith(ChatRoomProtocol.PUBLICMSG_ROUND)){
                        // 广播给所有人
                        dispatch(clientSocketChannel,str);
                    }
                }
            } catch (IOException e) {
                // 移除客户端
                Server.clients.removeByValue(clientSocketChannel);
            }
        }
    }
    /**
     * 实现客户端登陆
     */
    private void login(SocketChannel client,String str){
        try {
            // 去掉前后缀,拿到真正的名称
            String userName = getRealMsg(str);
            // 判断用户名是否重复
            if (Server.clients.map.containsKey(userName)){
                System.out.println("用户名重复");
                // 清空写缓冲区
                writeBuffer.clear();
                // 把要响应客户端的信息编码后放入写缓冲区(-1)
                writeBuffer.put(charset.encode(ChatRoomProtocol.USER_ROUND+
                        ChatRoomProtocol.NAME_REP+ChatRoomProtocol.USER_ROUND));
                // 反转写缓冲区
                writeBuffer.flip();
                // 判断缓冲区是否有数据
                while (writeBuffer.hasRemaining()){
                    // 把缓冲区数据写入通道(发送缓冲区的内容)
                    client.write(writeBuffer);
                }
            }else {
                System.out.println("用户登陆成功");
                // 清空写缓冲区
                writeBuffer.clear();
                // 把要响应客户端的信息编码后放入写缓冲区(1)
                writeBuffer.put(charset.encode(ChatRoomProtocol.USER_ROUND+
                        ChatRoomProtocol.LOGIN_SUCCESS+ChatRoomProtocol.USER_ROUND));
                // 反转写缓冲区
                writeBuffer.flip();
                // 判断缓冲区是否有数据
                while (writeBuffer.hasRemaining()){
                    // 把缓冲区数据写入通道(发送缓冲区的内容)
                    client.write(writeBuffer);
                }
                // 保存映射关系
                Server.clients.put(userName,client);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    /**
     * 发送私聊消息
     */
    private void sendMsyToUser(SocketChannel client,String str){
        try {
            // 拿到信息(包含目标用户和消息),去掉前后缀
            String userAndMsg = getRealMsg(str);
            // 使用分割字符隔开,用户名
            String targetUser = userAndMsg.split(ChatRoomProtocol.SPLIT_SIGN)[0];
            // 消息内容
            String privatemsg = userAndMsg.split(ChatRoomProtocol.SPLIT_SIGN)[1];
            // 清空写缓冲区
            writeBuffer.clear();
            // 把要说的信息编码后放入写缓冲区
            writeBuffer.put(charset.encode(Server.clients.getKeyByValue(client)+"私聊:"+privatemsg));
            // 反转缓冲区
            writeBuffer.flip();
            // 判断缓冲区是否有数据
            while (writeBuffer.hasRemaining()){
                // 把缓冲区内容(要说的话)发送给目标客户端
                Server.clients.map.get(targetUser).write(writeBuffer);
            }
        }catch (IOException e){
            // 移除客户端
            Server.clients.removeByValue(client);
        }
    }
    /**
     * 发送给所有人
     */
    private void dispatch(SocketChannel client,String str){
        // 拿到信息(包含目标用户和消息),去掉前后缀
        String publicMsg = getRealMsg(str);
        try {
            Set<SocketChannel> valueSet = Server.clients.getValueSet();
            // 遍历集合
            for (SocketChannel cli:valueSet){
                // 清空写缓冲区
                writeBuffer.clear();
                // 把要说的信息编码后放入写缓冲区
                writeBuffer.put(charset.encode(Server.clients.getKeyByValue(client)+"说:"+publicMsg));
                // 反转写缓冲区
                writeBuffer.flip();
                // 判断缓冲区是否有数据
                while (writeBuffer.hasRemaining()){
                    // 把缓冲区内容(要说的话)发送给所有人
                    cli.write(writeBuffer);
                }
            }
        }catch (IOException e){
            // 移除客户端
            Server.clients.removeByValue(client);
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
        // 创建服务端对象
        Server server = new Server();
        // 启动服务端
        server.init();
        // 服务端监听
        server.listen();
    }
}

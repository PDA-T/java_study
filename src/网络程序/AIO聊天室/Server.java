package 网络程序.AIO聊天室;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 服务端
 */
public class Server {
    // 服务器端口
    private static final int SERVER_PORT = 31000;
    // 字符集编码
    private static final String CHARSET = "UTF-8";
    // 字符集
    private Charset charset = Charset.forName(CHARSET);
    // 连接进来的客户端集合
    public static ChatRoomMap<String,AsynchronousSocketChannel> clients = new ChatRoomMap<>();
    // 初始化服务器
    public void init(){
        try {
            // 创建线程池
            ExecutorService executorService = Executors.newFixedThreadPool(20);
            // 创建通道组
            AsynchronousChannelGroup channelGroup
                    = AsynchronousChannelGroup.withThreadPool(executorService);
            // 服务器套接字通道
            AsynchronousServerSocketChannel serverSocketChannel
                    = AsynchronousServerSocketChannel.open(channelGroup);
            // 绑定IP和端口
            serverSocketChannel.bind(new InetSocketAddress("127.0.0.1",SERVER_PORT));
            // 循环接收客户端连接
            serverSocketChannel.accept(null,new AcceptHandler(serverSocketChannel));
        }catch (Exception e){
            System.out.println("服务器启动失败,请检查端口号");
        }
    }

    /**
     * 匿名内部类
     */
    private class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel,Object>{
        // 拿到服务端套接字通道
        private AsynchronousServerSocketChannel serverSocketChannel;
        // 构造器
        public AcceptHandler(AsynchronousServerSocketChannel serverSocketChannel){
            this.serverSocketChannel = serverSocketChannel;
        }

        // 读缓冲区
        private ByteBuffer rbuffer = ByteBuffer.allocate(1024);
        // 写缓冲区
        private ByteBuffer wbeffer = ByteBuffer.allocate(1024);
        /**
         * 完成IO操作之后调用
         * @param attachment
         */
        @Override
        public void completed(AsynchronousSocketChannel clientsocketChannel, Object attachment) {
            // 递归,让操作系统去接另外一个客户端,自己继续处理
            serverSocketChannel.accept(null,this);
            // 把通道数据写入缓冲区
            clientsocketChannel.read(rbuffer, null, new CompletionHandler<Integer, Object>() {
                @Override
                public void completed(Integer result, Object attachment) {
                    // 反转缓冲区
                    rbuffer.flip();
                    // 获取数据
                    String content = charset.decode(rbuffer).toString();
                    // 如果开头为用户名协议字符(说明传进来的是用户名)
                    if (content.startsWith(ChatRoomProtocol.USER_ROUND) &&
                        content.endsWith(ChatRoomProtocol.USER_ROUND)){
                        // 登陆
                        login(clientsocketChannel,content);
                        // 聊天信息
                    }else if (content.startsWith(ChatRoomProtocol.PRIVATEMSG_ROUND) && content.endsWith(ChatRoomProtocol.PRIVATEMSG_ROUND)){
                        // 发送私聊消息
                        sendMsyToUser(clientsocketChannel,content);
                        // 如果是公共信息
                    }else if (content.startsWith(ChatRoomProtocol.PUBLICMSG_ROUND) && content.endsWith(ChatRoomProtocol.PUBLICMSG_ROUND)){
                        // 广播给所有人
                        dispatch(clientsocketChannel,content);
                    }
                    // 清空读缓冲区
                    rbuffer.clear();
                    // 再次使用递归实现重复循环读取数据
                    clientsocketChannel.read(rbuffer,null,this);
                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    System.out.println("数据读取失败:" + exc);
                    // 从集合移除客户端
                    Server.clients.removeByValue(clientsocketChannel);
                }
            });
        }

        /**
         * 失败之后调用
         * @param exc
         * @param attachment
         */
        @Override
        public void failed(Throwable exc, Object attachment) {
            System.out.println("连接失败:" + exc);
        }

        /**
         * 实现客户端登陆
         * @param client
         * @param content
         */
        private void login(AsynchronousSocketChannel client,String content){
            try {
                // 去掉前后缀,拿到真正的名称
                String userName = getRealMsg(content);
                // 判断用户名是否重复
                if (Server.clients.map.containsKey(userName)){
                    System.out.println("用户名重复");
                    // 清空缓冲区
                    wbeffer.clear();
                    // 写入协议字符和是否成功
                    wbeffer.put(charset.encode(ChatRoomProtocol.USER_ROUND+
                            ChatRoomProtocol.NAME_REP+ChatRoomProtocol.USER_ROUND));
                    // 反转写缓冲区
                    wbeffer.flip();
                    // 写入通道
                    client.write(wbeffer).get();
                }else {
                    System.out.println("用户登陆成功");
                    // 清空缓冲区
                    wbeffer.clear();
                    // 写入协议字符和是否成功
                    wbeffer.put(charset.encode(ChatRoomProtocol.USER_ROUND+
                            ChatRoomProtocol.LOGIN_SUCCESS+ChatRoomProtocol.USER_ROUND));
                    // 反转写缓冲区
                    wbeffer.flip();
                    // 写入通道
                    client.write(wbeffer).get();
                    // 把客户端放入集合
                    Server.clients.put(userName,client);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        /**
         * 发送私聊消息
         */
        private void sendMsyToUser(AsynchronousSocketChannel client,String str){
            try {
                // 拿到信息(包含目标用户和消息),去掉前后缀
                String userAndMsg = getRealMsg(str);
                // 使用分割字符隔开,用户名
                String targetUser = userAndMsg.split(ChatRoomProtocol.SPLIT_SIGN)[0];
                // 消息内容
                String privatemsg = userAndMsg.split(ChatRoomProtocol.SPLIT_SIGN)[1];
                // 清空写缓冲区
                wbeffer.clear();
                // 把要说的信息编码后放入写缓冲区
                wbeffer.put(charset.encode(Server.clients.getKeyByValue(client)+"私聊:"+privatemsg));
                // 反转缓冲区
                wbeffer.flip();
                // 把缓冲区内容(要说的话)发送给目标客户端
                Server.clients.map.get(targetUser).write(wbeffer).get();
            }catch (Exception e){
                // 移除客户端
                Server.clients.removeByValue(client);
            }
        }
        /**
         * 发送给所有人
         */
        private void dispatch(AsynchronousSocketChannel client, String str){
            // 拿到信息(包含目标用户和消息),去掉前后缀
            String publicMsg = getRealMsg(str);
            try {
                Set<AsynchronousSocketChannel> valueSet = Server.clients.getValueSet();
                // 遍历集合
                for (AsynchronousSocketChannel cli:valueSet){
                    // 清空写缓冲区
                    wbeffer.clear();
                    // 把要说的信息编码后放入写缓冲区
                    wbeffer.put(charset.encode(Server.clients.getKeyByValue(client)+"说:"+publicMsg));
                    // 反转写缓冲区
                    wbeffer.flip();
                    // 把缓冲区内容(要说的话)发送给所有人
                    cli.write(wbeffer).get();
                }
            }catch (Exception e){
                // 移除客户端
                Server.clients.removeByValue(client);
            }
        }
        /**
         * 去除协议前后缀
         */
        private String getRealMsg(String lines){
            // 从协议长度(2)开始截取,截取到总字符串长度减去协议长度(2)
            return lines.substring(网络程序.NIO聊天室.ChatRoomProtocol.PROTOCOL_LEN,lines.length()- 网络程序.NIO聊天室.ChatRoomProtocol.PROTOCOL_LEN);
        }
    }

    public static void main(String[] args) {
        // 创建服务端
        Server server = new Server();
        // 初始化服务端
        server.init();
        try {
            // 线程休眠,等待客户端连接(因为是异步所以要手动阻塞)
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

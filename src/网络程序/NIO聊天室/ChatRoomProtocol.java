package 网络程序.NIO聊天室;

/**
 * 聊天室协议
 */
public interface ChatRoomProtocol {
    // 协议字符串的长度
    int PROTOCOL_LEN = 2;
    // 公共消息的前后缀(使用聊天中用的少的字符)
    String PUBLICMSG_ROUND = "~~";
    // 用户名称的前后缀(使用聊天中用的少的字符)
    String USER_ROUND = "||";
    // 登陆成功的前后缀(使用聊天中用的少的字符)
    String LOGIN_SUCCESS = "1";
    // 在客户端发送信息前,要求输入用户名,名字重复返回标记(使用聊天中用的少的字符)
    String NAME_REP = "-1";
    // 私聊消息的前后缀(使用聊天中用的少的字符)
    String PRIVATEMSG_ROUND = "*-";
    // 信息分割标记
    String SPLIT_SIGN = "/*";
}

package �������.NIO������;

/**
 * ������Э��
 */
public interface ChatRoomProtocol {
    // Э���ַ����ĳ���
    int PROTOCOL_LEN = 2;
    // ������Ϣ��ǰ��׺(ʹ���������õ��ٵ��ַ�)
    String PUBLICMSG_ROUND = "~~";
    // �û����Ƶ�ǰ��׺(ʹ���������õ��ٵ��ַ�)
    String USER_ROUND = "||";
    // ��½�ɹ���ǰ��׺(ʹ���������õ��ٵ��ַ�)
    String LOGIN_SUCCESS = "1";
    // �ڿͻ��˷�����Ϣǰ,Ҫ�������û���,�����ظ����ر��(ʹ���������õ��ٵ��ַ�)
    String NAME_REP = "-1";
    // ˽����Ϣ��ǰ��׺(ʹ���������õ��ٵ��ַ�)
    String PRIVATEMSG_ROUND = "*-";
    // ��Ϣ�ָ���
    String SPLIT_SIGN = "/*";
}

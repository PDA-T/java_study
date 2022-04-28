package �������.BIO������;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * ������߳���
 * ������ͻ���ֱ�ӵ����ݽ���
 */
public class ServerThread implements Runnable{
    // ��ͻ��˶�Ӧ���Ǹ��׽���
    private Socket socket = null;
    // ���������ַ���
    BufferedReader br = null;
    // ������ӡ�����
    PrintStream ps = null;
    public ServerThread(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            // ��ȡ�ͻ��˶�Ӧ��������
            br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"GBK"));
            // ��ȡ�ͻ��˶�Ӧ�������
            ps = new PrintStream(socket.getOutputStream(),true,"GBK");
            // ͨ�����������յ�����
            String lines = null;
            // ѭ����ȡ�ͻ��˷��͵���Ϣ
            while ((lines=br.readLine())!=null){
                // ��ȡ�ͻ��˷������û���(�ж�Э�������ǰ��׺�Ƿ����û���)
                if (lines.startsWith(ChatRoomProtocol.USER_ROUND) && lines.endsWith(ChatRoomProtocol.USER_ROUND)){
                    // ȥ��ǰ��׺,�õ�����������
                    String userName = getRealMsg(lines);
                    // �ж��û����Ƿ��ظ�
                    if (Server.clients.map.containsKey(userName)){
                        System.out.println("�û����ظ�");
                        // ����-1
                        ps.println(ChatRoomProtocol.NAME_REP);
                    }else {
                        System.out.println("�û���½�ɹ�");
                        // ����1
                        ps.println(ChatRoomProtocol.LOGIN_SUCCESS);
                        // �����û����Ͷ�Ӧ�������
                        Server.clients.put(userName,ps);
                    }
                    // �����˽��ǰ��׺
                }else if (lines.startsWith(ChatRoomProtocol.PRIVATEMSG_ROUND) && lines.endsWith(ChatRoomProtocol.PRIVATEMSG_ROUND)){
                    // �õ���Ϣ(����Ŀ���û�����Ϣ),ȥ��ǰ��׺
                    String userAndMsg = getRealMsg(lines);
                    // ʹ�÷ָ��ַ�����,�û���
                    String targetUser = userAndMsg.split(ChatRoomProtocol.SPLIT_SIGN)[0];
                    // ��Ϣ����
                    String privatemsg = userAndMsg.split(ChatRoomProtocol.SPLIT_SIGN)[1];
                    // ͨ���û�����ȡ����Ӧ�������,��ʾ���Ͷ���
                    Server.clients.map.get(targetUser).println(Server.clients.getKeyByValue(ps)+"˽��:"+privatemsg);
                // ������Ϣ
                }else {
                    // �õ���Ϣ,ȥ��ǰ��׺
                    String publicmsg = getRealMsg(lines);
                    // ���й㲥(�����˿ɼ�)
                    for (PrintStream clintsPs:Server.clients.getValueSet()){
                        // ����
                        clintsPs.println(Server.clients.getKeyByValue(ps)+"˵:"+publicmsg);
                    }
                }
            }
        }catch (Exception e){
            // �������Ϳͻ��˵��׽��ַ������ݽ����쳣,�Ӽ���ɾ�����û�
            Server.clients.removeByValue(ps);
            // ����������û����ϸ���
            System.out.println(Server.clients.map.size());
            try {
                // �ر���Դ
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
     * ȥ��Э��ǰ��׺
     */
    private String getRealMsg(String lines){
        // ��Э�鳤��(2)��ʼ��ȡ,��ȡ�����ַ������ȼ�ȥЭ�鳤��(2)
        return lines.substring(ChatRoomProtocol.PROTOCOL_LEN,lines.length()-ChatRoomProtocol.PROTOCOL_LEN);
    }
}

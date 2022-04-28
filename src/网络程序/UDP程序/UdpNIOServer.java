package �������.UDP����;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * ����UDP��NIO�����
 * UDPû��AIO
 */
public class UdpNIOServer {
    // �����ݺ������ݵĻ�����
    private ByteBuffer inbuffer = ByteBuffer.allocate(1024);
    private ByteBuffer outbuffer = ByteBuffer.allocate(1024);

    /**
     * ��ʼ������
     */
    public void init(){
        try {
            // ��ȡ���ݰ�ͨ��
            DatagramChannel datagramChannel = DatagramChannel.open();
            // ������ģʽ
            datagramChannel.configureBlocking(false);
            // �󶨹̶�IP�Ͷ˿�(����������Ҫ��)
            datagramChannel.bind(new InetSocketAddress("127.0.0.1",40000));
            // ѡ����
            Selector selector = Selector.open();
            // ��ͨ��ע�ᵽѡ����,��ȤΪ��
            datagramChannel.register(selector,SelectionKey.OP_READ);
            // ѭ�����շ�������
            while (true){
                // ��������,��ʱ5��
                int count = selector.select(5000);
                // �������0˵��û�пͻ�������
                if (count == 0){
                    // ����ѭ��
                    continue;
                }
                // ������
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                // �жϵ�������û������
                while (it.hasNext()){
                    // ȡ������
                    SelectionKey key = it.next();
                    // �Ƿ��׼������
                    if(key.isReadable()){
                        // ��ȡ
                        handlerRead(key);
                    }
                    // �Ƴ������������
                    selector.selectedKeys().remove(key);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * ���������
     */
    public void handlerRead(SelectionKey key){
        try {
            // �õ�ͨ��
            DatagramChannel datagramChannel = (DatagramChannel) key.channel();
            // ��ջ�����
            inbuffer.clear();
            // ������д�뵽����������һ��IP��ַ
            InetSocketAddress sendAddr = (InetSocketAddress) datagramChannel.receive(inbuffer);
            // ��ת������
            inbuffer.flip();
            // ����յ�����Ϣ
            System.out.println("�ͻ�����Ϣ:"+
                    StandardCharsets.UTF_8.decode(inbuffer).toString());
            // �ظ���Ϣ
            String content = "�������յ�";
            // ��ջ�����
            outbuffer.clear();
            // д�뻺����
            outbuffer.put(content.getBytes("UTF-8"));
            // ��ת������
            outbuffer.flip();
            // ��������
            datagramChannel.send(outbuffer,
                    new InetSocketAddress(sendAddr.getHostName(),sendAddr.getPort()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        // �����������
        UdpNIOServer server = new UdpNIOServer();
        // ��ʼ�������
        server.init();
    }
}

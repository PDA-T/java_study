package ������.�߼�������.ѡ����;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * ѡ����
 * һ������������ʹ��
 */
public class SelectorTest {
    public static void main(String[] args) throws IOException {
        // ��ȡ�������׽���ͨ������
        ServerSocketChannel channel = ServerSocketChannel.open();
        // ��ȡѡ����ʵ������
        Selector selector = Selector.open();
        // ��ѡ����ע��ͨ��֮ǰ,��������ͨ��Ϊ������ģʽ
        channel.configureBlocking(false);
        // ��ͨ��ע�ᵽѡ����,OP_ACCEPT(���վ���)��ʾ׼�������µ�����(��Ȥ����,������ʲô�¸���Ȥ)
        channel.register(selector, SelectionKey.OP_ACCEPT);
    }
}

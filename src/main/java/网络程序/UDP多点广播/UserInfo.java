package 网络程序.UDP多点广播;

import java.net.SocketAddress;
import java.util.Objects;

public class UserInfo {
    // 该用户的图标
    private String icon;
    // 该用户的名字
    private String name;
    // 该用户的MulitcastSocket所在的IP和端口
    private SocketAddress address;
    // 该用户失去联系的次数
    private int lost;
    //关联的聊天对话框
    private ChatFrame chatFrame;

    // 该用户对应的交谈窗口
    //private ChatFrame chatFrame;
    public UserInfo() {
    }

    // 有参数的构造器
    public UserInfo(String icon, String name
            , SocketAddress address, int lost) {
        this.icon = icon;
        this.name = name;
        this.address = address;
        this.lost = lost;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SocketAddress getAddress() {
        return address;
    }

    public void setAddress(SocketAddress address) {
        this.address = address;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public ChatFrame getChatFrame() {
        return chatFrame;
    }

    public void setChatFrame(ChatFrame chatFrame) {
        this.chatFrame = chatFrame;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserInfo)) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(getAddress(), userInfo.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAddress());
    }
}

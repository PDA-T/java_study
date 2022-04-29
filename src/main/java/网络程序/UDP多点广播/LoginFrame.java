package 网络程序.UDP多点广播;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JDialog {
    public JLabel tip;
    public JTextField userField = new JTextField(20);
    public JComboBox<Integer> iconList = new JComboBox<>(
            new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
    private JButton loginBn = new JButton("登录");
    // 聊天的主界面
    private LanTalk lanTalkFrame;
    // 聊天通信的工具实例
    public static ComUtil comUtil;

    public LoginFrame(LanTalk parent, String msg) {
        super(parent, "输入名字后登录", true);
        this.lanTalkFrame = parent;
        setLayout(new GridLayout(5, 1));
        JPanel jp = new JPanel();
        tip = new JLabel(msg);
        tip.setFont(new Font("Serif", Font.BOLD, 16));
        jp.add(tip);
        add(jp);

        add(getPanel("用户名", userField));
        iconList.setPreferredSize(new Dimension(224, 20));
        add(getPanel("图    标" , iconList));
        JPanel bp = new JPanel();
        bp.add(loginBn);
        loginBn.addActionListener(new MyActionListener(this));
        add(bp);
        pack();

        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) screensize.getWidth() / 2 - this.getWidth() / 2;
        int y = (int) screensize.getHeight() / 2 - this.getHeight() / 2;
        this.setLocation(x, y);
        this.setVisible(true);
    }

    // 工具方法，该方法将一个字符串和组件组合成JPanel对象
    private JPanel getPanel(String name, JComponent jf) {
        JPanel jp = new JPanel();
        jp.add(new JLabel(name + "："));
        jp.add(jf);
        return jp;
    }

    public void setTipMsg(String msg){
        this.tip.setText(msg);
    }

    /**
     * 点击按钮后，程序要做的功能，实现登录在这里
     */
    private class MyActionListener implements ActionListener {
        private LoginFrame loginFrame;
        public MyActionListener(LoginFrame loginFrame){
            this.loginFrame = loginFrame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                //点击了登录按钮，程序会执行到这里
                //此列子中的登录：广播新用户上线了。。。。。。
                // 初始化聊天通信类
                comUtil = new ComUtil(lanTalkFrame);
                final String loginMsg = ChatProtocol.PRESENCE + userField.getText()
                        + ChatProtocol.SPLITTER + iconList.getSelectedObjects()[0]
                        + ChatProtocol.PRESENCE;
                comUtil.broadCast(loginMsg);
                //做一个定时器，每隔10秒钟，重新广播一次
                javax.swing.Timer timer = new javax.swing.Timer(1000 * 10
                        , new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        comUtil.broadCast(loginMsg);
                    }
                });
                timer.start();
                loginFrame.setVisible(false);
                lanTalkFrame.setVisible(true);
            }catch (Exception ex){
                loginFrame.setTipMsg("确认30001端口空闲，且网络正常！");
            }
        }
    }


}

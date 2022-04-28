package 网络程序.BIO聊天室;

import java.io.BufferedReader;

/**
 * 客户端线程类
 * 负责获取和显示从服务器发来的信息
 */
public class ClientThread implements Runnable{
    // 创建输入流
    private BufferedReader in = null;
    // 构造器
    public ClientThread(BufferedReader in) {
        // 初始化属性
        this.in = in;
    }
    @Override
    public void run() {
        try {
            // 服务器发来的信息
            String content = null;
            // 不断的接收服务器的信息
            while (true) {
                // 判断服务器发来的信息是否为空
                if (!((content = in.readLine())!=null)){
                    // 输出
                    System.out.println(content);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                // 关闭资源
                if (in!=null){
                    in.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

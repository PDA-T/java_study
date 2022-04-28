package 网络程序.多线程下载文件;

import java.io.IOException;

public class DownMain {
    public static void main(String[] args) throws IOException {
        // 创建工具类
        DownUtil downUtil = new DownUtil("https://wallpaperaccess.com/full/508751.jpg","D:/aaa.jpg",5);
        // 开始下载
        downUtil.download();
        // 获取下载的进度
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 如果进度小于1
                while (downUtil.getCompleteRate()<=1){
                    // 输出进度
                    System.out.println("已下载:"+downUtil.getCompleteRate()*100);
                    try {
                        // 每0.1秒获取一下进度
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}

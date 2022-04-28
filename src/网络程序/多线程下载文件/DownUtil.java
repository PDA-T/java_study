package 网络程序.多线程下载文件;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 下载工具类
 */
public class DownUtil {
    // 下载目标文件的URL地址
    private String urlPath;
    // 保存文件的位置
    private String targetFile;
    // 使用几条线程下载
    private int threadNum;
    // 定义一个数组,存放下载的线程类
    private DownThread[] threads;
    // 把用于下载的线程类定义为工具里的内部类
    private class DownThread extends Thread{
        // 每个线程下载的文件块的开始位置
        private int startPos;
        // 每个线程负责下载的文件块的大小
        private int currentPartSize;
        // 每个线程下载的文件块
        private RandomAccessFile currentPart;
        // 记录每条线程已经下载下来的文件字节数
        private int length;
        // 创建构造器
        public DownThread(int startPos,int currentPartSize,RandomAccessFile currentPart){
            // 初始化属性
            this.startPos = startPos;
            this.currentPartSize = currentPartSize;
            this.currentPart = currentPart;
        }

        @Override
        public void run() {
            try {
                // 创建URL
                URL url = new URL(urlPath);
                // 拿到链接
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                // 设置超时时间,连接超时抛出异常
                conn.setConnectTimeout(5000);
                // 设置连接目标的请求方法
                conn.setRequestMethod("GET");
                // 设置链接头部信息,Accept表示客户端可以处理哪些文件,*/*表示允许客户端处理所有文件(键值对)
                conn.setRequestProperty("Accept","*/*");
                // 设置接受的语言类型
                conn.setRequestProperty("Accept-Language","zh-CN");
                // 设置字符集
                conn.setRequestProperty("Charset","UTF-8");
                // 设置连接模式,Keep-Alive表示服务器响应后tcp连接不立即中断
                conn.setRequestProperty("Connection","Keep-Alive");
                // 连接目标(可以省略)
                conn.connect();
                // 获取目标文件的数据
                InputStream in = conn.getInputStream();
                // 把输入流的指针,跳到该线程负责下载的位置
                in.skip(this.startPos);
                // 创建缓冲区
                byte[] buffer = new byte[1024];
                // 创建标记
                int hasRead = 0;
                // 循环判断下载的长度要小于当前线程负责下载的长度,并且文件已经读完
                while (length<currentPartSize && (hasRead=in.read(buffer))!=-1){
                    // 把读取的数据写入当前文件块
                    currentPart.write(buffer,0,hasRead);
                    // 记录长度
                    length += hasRead;
                }
                // 关闭流
                currentPart.close();
                in.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    /*
     * 连接远程的文件,划分文件,让线程分别实现下载
     * 目标文件的大小
     */
    private int fileSize;
    // 创建构造方法
    public DownUtil(String urlPath,String targetFile,int threadNum){
        // 初始化属性
        this.urlPath = urlPath;
        this.targetFile = targetFile;
        this.threadNum = threadNum;
        // 实例化线程类数组,长度为线程个数
        this.threads = new DownThread[threadNum];
    }
    // 实现下载文件方法
    public void download() throws IOException {
        // 创建URL
        URL url = new URL(urlPath);
        // 拿到链接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 设置超时时间,连接超时抛出异常
        conn.setConnectTimeout(5000);
        // 设置连接目标的请求方法
        conn.setRequestMethod("GET");
        // 设置链接头部信息,Accept表示客户端可以处理哪些文件,*/*表示允许客户端处理所有文件(键值对)
        conn.setRequestProperty("Accept","*/*");
        // 设置接受的语言类型
        conn.setRequestProperty("Accept-Language","zh-CN");
        // 设置字符集
        conn.setRequestProperty("Charset","UTF-8");
        // 设置连接模式,Keep-Alive表示服务器响应后tcp连接不立即中断
        conn.setRequestProperty("Connection","Keep-Alive");
        // 连接目标(可以省略)
        conn.connect();
        // 获取目标文件大小
        fileSize = conn.getContentLength();
        // 关闭连接
        conn.disconnect();

        // 创建随机访问文件类
        RandomAccessFile file = new RandomAccessFile(targetFile,"rw");
        // 设置文件大小(在本地创建一个和目标文件同大小的文件)
        file.setLength(fileSize);
        // 关闭资源
        file.close();

        // 每个线程负责下载的文件块的大小
        int currentPartSize = fileSize / threadNum + 1;
        // 根据文件大小和下载的线程数量,对目标文件进行切块
        for (int i=0;i<threadNum;i++){
            // 设置每个线程下载的文件块的开始位置
            int startPos = i*currentPartSize;
            // 让每个线程使用一个RandomAccessFile的对象来进行下载
            RandomAccessFile currentPart = new RandomAccessFile(targetFile,"rw");
            // 移动文件指针到下载位置的地方(每个线程负责下载的文件块)
            currentPart.seek(startPos);
            // 创建线程,负责下载
            threads[i] = new DownThread(startPos,currentPartSize,currentPart);
            // 启动线程,开始下载
            threads[i].start();
        }
    }

    /**
     * 获取下载完成的百分比
     * @return
     */
    public double getCompleteRate(){
        // 下载的总长度
        int sumSize = 0;
        for (int i=0;i<threadNum;i++){
            sumSize += threads[i].length;
        }
        // 返回已经下载下来的文件百分比
        return sumSize*1.0/fileSize;
    }
}

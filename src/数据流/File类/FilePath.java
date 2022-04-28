package ������.File��;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * ��ȡ��Ŀ��·���ķ���
 */
public class FilePath {
    public static void main(String[] args) throws IOException {
        // ʵ�����ļ���
        FilePath ft = new FilePath();
        // ���û�ȡ·���ķ���
        ft.getUrl();
    }

    /**
     * ��ȡ��Ŀ��·��д��
     */
    public void getUrl() throws IOException {
        String path = "";
        // ʹ�������·��
        path = this.getClass().getResource("/").getPath();
        System.out.println("1:"+path);
        // û��д/�õ���·���ǵ�ǰ�����ڵ�·��
        path = this.getClass().getResource("").getPath();
        System.out.println("1.1:"+path);
        // �ڶ�������
        File file = new File("");
        // ��ȡ������Ŀ��·��
        path = file.getCanonicalPath();
        System.out.println("2:"+path);
        // ��ȡ������Ŀ��·��
        path = file.getAbsolutePath();
        System.out.println("2.1:"+path);
        // ����������,��1.1һ��
        URL path2 = this.getClass().getResource("");
        System.out.println("3:"+path2);
        // ���ĸ�����
        path = System.getProperty("user.dir");
        System.out.println("4:"+path);
        // ���������,�����߳�,�͵�һ������
        path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println("5:"+path);
        // ����������,��web��Ŀ��ʹ��,�����޷�ʹ��
//        request.getSession().getServletContext().getRealPath("/");
    }
}

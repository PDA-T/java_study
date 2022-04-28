package 数据流.File类;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 获取项目根路径的方法
 */
public class FilePath {
    public static void main(String[] args) throws IOException {
        // 实例化文件类
        FilePath ft = new FilePath();
        // 调用获取路径的方法
        ft.getUrl();
    }

    /**
     * 获取项目跟路径写法
     */
    public void getUrl() throws IOException {
        String path = "";
        // 使用类加载路径
        path = this.getClass().getResource("/").getPath();
        System.out.println("1:"+path);
        // 没有写/拿到的路径是当前类所在的路径
        path = this.getClass().getResource("").getPath();
        System.out.println("1.1:"+path);
        // 第二个方法
        File file = new File("");
        // 获取开发项目的路径
        path = file.getCanonicalPath();
        System.out.println("2:"+path);
        // 获取开发项目的路径
        path = file.getAbsolutePath();
        System.out.println("2.1:"+path);
        // 第三个方法,和1.1一样
        URL path2 = this.getClass().getResource("");
        System.out.println("3:"+path2);
        // 第四个方法
        path = System.getProperty("user.dir");
        System.out.println("4:"+path);
        // 第五个方法,利用线程,和第一种类似
        path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println("5:"+path);
        // 第六个方法,在web项目中使用,这里无法使用
//        request.getSession().getServletContext().getRealPath("/");
    }
}

package 基础类;

import java.io.IOException;

/**
 * 项目中通常用Runtime的exec方法启动其他进程
 */
public class Runtime_exec {
    public static void main(String[] args) {
        // 创建对象
        java.lang.Runtime rt= java.lang.Runtime.getRuntime();
        Process p=null;// 创建空对象
        // 启动有可能不成功,需要try
        try {
            p=rt.exec("notepad");// 启动进程
            p.waitFor();// 等待打开的新的程序,执行完之后,才继续后面的Java程序
            p.destroy();// 结束进程进程
        } catch (IOException e) {
            System.out.println("启动失败");// 输出提示
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 如果没有waitFor方法将会直接执行
        System.out.println("结束");
    }
}

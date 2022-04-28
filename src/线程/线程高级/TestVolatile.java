package 线程.线程高级;

public class TestVolatile {
    public static void main(String[] args) {
        VolatileThread vt = new VolatileThread();
        new Thread(vt,"线程").start();
        while (true){
            // 如果flag是true
            if (vt.isFlag()){
                System.out.println("线程中flag为true");
                // 结束循环
                break;
            }
        }
    }
}
class VolatileThread implements Runnable{
    // 在线程类的属性位置,定义个属性,这个属性变量就是所有线程共享的变量,要加内存可见关键字
    private volatile boolean flag = false;

    public boolean isFlag() {
        return flag;
    }

    public VolatileThread setFlag(boolean flag) {
        this.flag = flag;
        return this;
    }

    @Override
    public void run() {
        try {
            // 放大线程安全问题
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.flag = true;
        System.out.println("线程修改flag的值,true");
    }
}

package 基础类;

/**
 * 查看计算机有几个核心
 */
public class Runtime_class {
    public static void main(String[] args) {
        java.lang.Runtime rt= java.lang.Runtime.getRuntime();// 创建Runtime对象
        System.out.println("电脑核心数:"+rt.availableProcessors());// 输出核心数
        System.out.println("总内存大小:"+rt.totalMemory());// 输出总内存
        // 输出初始化状态下,可以用的总内存
        System.out.println("初始化状态下,可用的总内存:"+rt.freeMemory());
        rt.gc();// 建议jvm执行垃圾回收
        // 输出第一次建议垃圾回收后可用的内存
        System.out.println("第一次gc后可用的内存:"+rt.freeMemory());
        // 用掉一部分内存
        Integer[] arr=new Integer[1000000];
        // 循环创建数组对象
        for (int i=0;i<1000000;i++){
            arr[i]=new Integer(i);// 赋值数组
        }
        // 输出使用一部分内存后可用的内存
        System.out.println("使用掉部分内存后可用的内存:"+rt.freeMemory());
        // 吧数组里的对象变成垃圾
        for (int i=0;i<1000000;i++){
            arr[i]=null;// 吧数组引用变成空
        }
        rt.gc();// 建议jvm执行垃圾回收
        // 输出第二次建议垃圾回收后可用的内存
        System.out.println("gc后可用的内存:"+rt.freeMemory());
    }
}

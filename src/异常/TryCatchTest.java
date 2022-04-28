package 异常;

public class TryCatchTest {
    public static void main(String[] args) {
        //System.out.println(new TryCatchTest().test());// 运行第一个方法
        //System.out.println(new TryCatchTest().test2());// 运行第二个方法
        System.out.println(new TryCatchTest().test3());// 运行第三个方法
    }
    public int test(){// 第一个方法
        int divider=10;// 除数
        int result=100;// 结果
        try {// 捕捉异常
            while (divider>-1){// 如果除数大于-1开始循环
                divider--;// 除数减1
                result=result+100/divider;// 结果等于结果加100除以除数
            }
            return result;// 返回结果
        }catch (Exception e){// 抛出异常
            e.printStackTrace();// 抛出红色异常
            System.out.println("Exc");// 打印异常
            return -1;// 返回-1
        }
    }
    public int test2(){// 第二个方法
        int divider=10;// 除数
        int result=100;// 结果
        try {// 捕捉异常
            while (divider>-1){// 如果除数大于-1开始循环
                divider--;// 除数减1
                result=result+100/divider;// 结果等于结果加100除以除数
            }
            return result;// 返回结果
        }catch (Exception e){// 抛出异常
            e.printStackTrace();// 抛出红色异常
            System.out.println("Exc");// 打印异常
            return 999;// 返回999
        }finally {// 后续处理
            System.out.println("finally");// 打印处理
        }
    }
    public int test3(){// 第三个方法
        int divider=10;// 除数
        int result=100;// 结果
        try {// 捕捉异常
            while (divider>-1){// 如果除数大于-1开始循环
                divider--;// 除数减1
                result=result+100/divider;// 结果等于结果加100除以除数
            }
        }catch (Exception e){// 抛出异常
            e.printStackTrace();// 抛出红色异常
            System.out.println("Exc");// 打印异常
        }finally {// 后续处理
            System.out.println("finally");// 打印处理
        }
        System.out.println("test3");// 输出
        return 1111;// 返回1111
    }
}
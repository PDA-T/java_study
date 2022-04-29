package 异常;

public class DrunkException extends Exception{// 创建自定义异常类继承异常类
    public DrunkException(){// 创建构造方法
    }
    public DrunkException(String message){// 创建有参构造方法
        super(message);// 调用父类构造方法，参数传入父类
    }
}

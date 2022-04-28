package 多态.商城秒杀模拟;

/**
 * 商城系统类
 * 设计时多态:项目需要多个同名方法但参数不同使用设计时多态
 */
public class Shopping {
    public void seckill(String name,int leixing,String xinxi){// 创建秒杀方法,传入商品名称,终端类型，终端信息
        System.out.println("正在秒杀商品:"+name);// 打印提示
        if (leixing==1){// 如果终端类型等于1
            int shouji=Integer.parseInt(xinxi);// 转换int型,使用移动端方法
            record(name,shouji);// 调用移动端方法
        }else if (leixing==2){// 如果终端类型等于2
            record(name,xinxi);// 调用PC端方法
        }
    }
    private void record(String name,int shouji){// 创建记录移动端秒杀信息方法,传入商品名字,手机号码
        System.out.println("记录--秒杀商品:"+name+";移动端手机号:"+shouji);// 打印提示
    }
    private void record(String name,String url){// 创建记录PC端秒杀信息方法,传入商品名字,PC终端信息
        System.out.println("记录--秒杀商品:"+name+";PC端IP地址:"+url);// 打印提示
    }
    public static void main(String[] args) {// 主方法
        Shopping shopping=new Shopping();// 实例化商城类
        shopping.seckill("笔记本",1,"1581103605");// 调用秒杀方法
        shopping.seckill("手机",2,"125.365.124.120");// 调用秒杀方法
    }
}

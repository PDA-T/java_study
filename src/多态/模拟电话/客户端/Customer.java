package 多态.模拟电话.客户端;

import 多态.模拟电话.系统.CustomerService;
import 多态.模拟电话.系统.Language;

public class Customer {
    public static void main(String[] args) {
        CustomerService customerService=new CustomerService();// 实例化客户服务类
        Language language=customerService.contact(66);// 通过区号方法返回接口实现类赋值给接口对象
        language.voice();// 输出接口实现类的实现方法
    }
}

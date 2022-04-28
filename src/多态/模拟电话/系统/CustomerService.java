package 多态.模拟电话.系统;

public class CustomerService {// 创建客户服务类
    public Language contact(int areaCode){// 创建区号方法
        if (areaCode==86){// 如果区号等于86
            return new Chinese();// 返回实例化汉语类
        }else if (areaCode==33){// 如果区号等于33
            return new French();// 返回实例化法语类
        }else {// 否则
            return new English();// 返回实例化英语类
        }
    }
}

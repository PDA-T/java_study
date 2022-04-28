package 多态.模拟电话.系统;

public class English implements Language{// 实现接口
    @Override
    public void voice() {// 实现方法
        System.out.println("Holle");// 输出
    }
}

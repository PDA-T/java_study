package 多态.模拟电话.系统;

public class French implements Language{// 实现接口
    @Override
    public void voice() {// 实现方法
        System.out.println("French");// 输出
    }
}

package 枚举;

public class TestMain {
    public static void main(String[] args) {
        Day days[]=Day.values();// 调用values方法返回枚举数
        // 遍历数组
        for (Day day:days){
            System.out.println(day);// 输出枚举名称
        }
        Day monday=Day.valueOf("MONDAY");// 返回的值为monday对象
//        System.out.println(monday);// 控制台输出的MONDAY为文字描述
        System.out.println(monday.ordinal());// 获取枚举类型的索引值
        Day tuesday=Day.valueOf("TUESDAY");// 效果和下面一样
        Day tuesday1=Day.valueOf(Day.class,"TUESDAY");// 效果和上面一样
        System.out.println(tuesday.ordinal());// 获取枚举类型的索引值
        System.out.println(tuesday.compareTo(monday));// 比大小,返回-1或1
        System.out.println(monday.toString());// 取出文字描述
        System.out.println(monday.name());// 取出文字描述
    }
}

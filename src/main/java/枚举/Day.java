package 枚举;

/**
 * 项目中枚举类型一般使用此方法创建
 */
public enum Day {
    MONDAY("星期一",1),
    TUESDAY("星期二",2),
    WEDNESDAY("星期三",3),
    THURSDAY("星期四",4),
    FRIDAY("星期五",5),
    SATURDAY("星期六",6),
    SUNDAY("星期天",7);
    // 自定义两个枚举属性
    private String desc;// 枚举对象文字描述
    private int code;// 枚举对象对应的下标

    public String getDesc() {
        return desc;
    }

    public int getCode() {
        return code;
    }

    /**
     * 私有化构造器,防止外部调用
     */
    private Day(String desc,int code){
        this.desc=desc;
        this.code=code;
    }

    /**
     * 通过自定义的code属性,来获取整合枚举对象,静态方法
     * @return
     */
    public static Day getDayByCode(int code){
        Day day=null;
        switch (code){
            case 1:
                day = Day.MONDAY;
                break;
            case 2:
                day = Day.TUESDAY;
                break;
            case 3:
                day = Day.WEDNESDAY;
                break;
            case 4:
                day = Day.THURSDAY;
                break;
            case 5:
                day = Day.FRIDAY;
                break;
            case 6:
                day = Day.SATURDAY;
                break;
            case 7:
                day = Day.SUNDAY;
                break;
        }
        return day;
    }
}

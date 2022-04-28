package ö��;

/**
 * ��Ŀ��ö������һ��ʹ�ô˷�������
 */
public enum Day {
    MONDAY("����һ",1),
    TUESDAY("���ڶ�",2),
    WEDNESDAY("������",3),
    THURSDAY("������",4),
    FRIDAY("������",5),
    SATURDAY("������",6),
    SUNDAY("������",7);
    // �Զ�������ö������
    private String desc;// ö�ٶ�����������
    private int code;// ö�ٶ����Ӧ���±�

    public String getDesc() {
        return desc;
    }

    public int getCode() {
        return code;
    }

    /**
     * ˽�л�������,��ֹ�ⲿ����
     */
    private Day(String desc,int code){
        this.desc=desc;
        this.code=code;
    }

    /**
     * ͨ���Զ����code����,����ȡ����ö�ٶ���,��̬����
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

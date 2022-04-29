package 基础类;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 淘汰的日期时间类
 */
public class Date_class {
    public static void main(String[] args) {
        Date date=new Date();
        // 输出当前时间
        System.out.println(date);
        // Date类已经淘汰,因机制原因年份需要-1900月份-1
        Date date2=new Date(2021-1900,12-1,11);
        System.out.println(date2);
        // 调用其他类
        new Calendar_class().Calendar_class();
        new SimpleDateFormat_class().SimpleDateFormat_class();
        // 输出距离今天57天后的日期
        System.out.println(DateUtils.getDistanceDay(57));
    }
}

/**
 * 日历类,功能多,使用困难
 */
class Calendar_class{
    public void Calendar_class(){
        // 获取日历类对象
        Calendar calendar=Calendar.getInstance();
        // 输出时间
        System.out.println(calendar);
        // 设置时间,月份需要-1
        calendar.set(2021,12-1,11);
        // 设置单个时间
        calendar.set(Calendar.DATE,10);
        // 单个时间增加,负数为减
        calendar.add(Calendar.DATE,5);
        // 输出时间
        System.out.println(calendar);
        // 获取单个值,获取月份要+1
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        // Calendar的子类
        GregorianCalendar gCalendar=new GregorianCalendar();
        // 判断是否为闰年
        System.out.println(gCalendar.isLeapYear(2021));
    }
}

/**
 * 日期时间工具类,可以互相转换
 */
class SimpleDateFormat_class{
    public void SimpleDateFormat_class(){
        Date date = new Date();
        // 转换为长整型
        Long time=date.getTime();
        // 转换为Date
        System.out.println(new Date(time));
        // Calendar类互转
        Calendar calendar = Calendar.getInstance();
        // 转化为长整型毫秒
        Long time2 = calendar.getTimeInMillis();
        // 输出转换后的值
        System.out.println(time2);
        // 转换为Calendar
        calendar.setTimeInMillis(time2);
        // 输出转换后的值
        System.out.println(calendar);
        Date date2 = new Date();
        // 创建工具类
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // Date转换格式
        System.out.println(sdf.format(date2));
        String d = "2021-12-11 19:36:11";
        try {
            //字符串转化为Date
            System.out.println((sdf).parse(d));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 使用自己写的工具类判断是否闰年
        System.out.println(DateUtils.DateUtils(2031));
        // 使用自己写的工具类获取时间
        System.out.println(DateUtils.getCurrentDate());
        // 使用自己写的工具类获取任意一天是星期几
        String date3 = "2021-5-8";
        System.out.println(DateUtils.getWeek(date3));
    }
}
class DateUtils{
    /**
     * 判断是否为闰年
     * @param year
     * @return
     */
    public static boolean DateUtils(int year){
        GregorianCalendar calendar = new GregorianCalendar();
        return calendar.isLeapYear(year);
    }

    /**
     * 获取方便查看的时间
     * @return
     */
    public static String getCurrentDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(System.currentTimeMillis());
    }

    /**
     * 给出任意一个年月日得到该天是星期几
     * @param date
     * @return
     */
    public static int getWeek(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // 创建Calendar类,包含当前日期时间
        Calendar c = Calendar.getInstance();
        try {
            // 格式化成新的Date
            Date d = dateFormat.parse(date);
            // 吧转换后的Date放到Calendar
            c.setTime(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 返回Calendar的星期几,星期天是0,所以需要-1
        return c.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * 获得距离今天n天的那一天的日期
     * @param day
     * @return
     */
    public static String getDistanceDay(int day){
        // 获取当前时间
        Calendar calendar = Calendar.getInstance();
        // 加上传入的day时间
        calendar.add(Calendar.DAY_OF_MONTH,day);
        // 转换为Date
        Date date = calendar.getTime();
        // 格式化为方便查看的时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // 输出String
        return dateFormat.format(date);
    }

    /**
     * 获得给定两个日期相差的天数
     * @param date1
     * @param date2
     * @return
     */
    public static long getGapDays(String date1,String date2){
        // 通过-吧传入的值拆分为字符串数组
        String[] d1 = date1.split("-");
        String[] d2 = date2.split("-");
        // 创建Calendar对象
        Calendar c = Calendar.getInstance();
        // 传入年月日,时分秒都为0
        c.set(Integer.parseInt(d1[0]),Integer.parseInt(d1[1]),Integer.parseInt(d1[2]),
                0,0,0);
        // 拿到日期时间的长整型
        long l1 = c.getTimeInMillis();
        // 传入年月日,时分秒都为0
        c.set(Integer.parseInt(d2[0]),Integer.parseInt(d2[1]),Integer.parseInt(d2[2]),
                0,0,0);
        // 拿到日期时间的长整型
        long l2 = c.getTimeInMillis();
        // 通过日期相减做差之后求绝对值在除以(天,小时,分钟,毫秒)的积得出相隔的时间
        return (Math.abs(l1-l2)/(24*60*60*1000));
    }
}
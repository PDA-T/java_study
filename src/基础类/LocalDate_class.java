package 基础类;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.TimeZone;

/**
 * Java8新的时间类
 */
public class LocalDate_class {
    public static void main(String[] args) {
        // 获取当前日期,不包括时间
        LocalDate date = LocalDate.now();
        // 输出当前日期
        System.out.println(date);
        // 获取当前时间,不包括日期
        LocalTime time = LocalTime.now();
        // 输出当前时间
        System.out.println(time);
        // 输出当前年,月,日(不需要和老类一样需要+1900)
        System.out.println(date.getYear());
        System.out.println(date.getMonthValue());
        System.out.println(date.getDayOfMonth());
        // 自定义日期
        LocalDate date2 = LocalDate.of(2021,12,10);
        System.out.println(date2);
        // 比较是否相等
        System.out.println(date.equals(date2));
        /*
         * 判断是否是生日
         */
        LocalDate date3 = LocalDate.of(2002,4,19);
        // 会自动提取生日的月和日保存到MonthDay
        MonthDay birthday = MonthDay.from(date3);
        // 提取当前日期的月和日
        MonthDay toDay = MonthDay.from(date);
        // 判断日期的月和日是否一致
        if (birthday.equals(toDay)){
            System.out.println("生日");
        }else {
            System.out.println("NO");
        }
        // 增加小时,必须使用LocalTime保存,因为会获取一个新对象
        LocalTime time2 = time.plusHours(2);
        // 通用增加时间,第一个参数为增加的数量,第二个为指定增加的时分秒,使用ChronoUnit.枚举
        LocalTime time3 = time.plus(1, ChronoUnit.HOURS);
        // Clock时钟类,获取时间戳
        System.out.println(System.currentTimeMillis());
        System.out.println(Clock.systemUTC().millis());
        // 获取默认时区
        System.out.println(TimeZone.getDefault());
        System.out.println(Clock.systemDefaultZone());
        // 判断某一个日期是在另一个日期的前面还是后面
        LocalDateTime d1 = LocalDateTime.of(2021,12,12,10,10);
        LocalDateTime d2 = LocalDateTime.of(2021,12,12,10,11);
        // 如果调用方法的日期比给定的日期早的话返回true
        System.out.println(d1.isBefore(d2));
        // 和上面相反
        System.out.println(d2.isAfter(d1));
        // 创建时区类
        ZoneId zone = ZoneId.of("UTC");
        // 获取不同时区的时间,使用带有时区的时间类ZonedDateTime
        ZonedDateTime zdt = ZonedDateTime.now(zone);
        // 输出时区的时间
        System.out.println(zdt);
        // 创建时间戳类
        Instant instant = Instant.now();
        // 输出时间戳
        System.out.println(instant);
        // 转换成带有本地化的时间
        LocalDateTime ct = zdt.toLocalDateTime();
        // 在UTC时间上获取不同时区的时间,修改后面的CTT即可改为任意时区
        ZonedDateTime chinatime = ZonedDateTime.of(ct,ZoneId.of(ZoneId.SHORT_IDS.get("CTT")));
        // 输出国内时间
        System.out.println(chinatime);
        // 计算两个日期之间对应差多少天,月,年
        LocalDate birthday2 = LocalDate.of(2000,10,9);
        LocalDate date4 = LocalDate.of(2021,1,1);
        // 使用Period类判断
        Period period = Period.between(birthday2,date4);
        // 使用Period类的方法判断相差多少年月日
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
        // String转日期
        String sdate = "20211212";
        // 使用预设格式
        LocalDate date5 = LocalDate.parse(sdate, DateTimeFormatter.BASIC_ISO_DATE);
        // 自定义格式
        String sdate2 = "12 12 2021";
        LocalDate date6 = LocalDate.parse(sdate2, DateTimeFormatter.ofPattern("MM dd yyyy"));
        // 输出转换后的值
        System.out.println(date5);
        // 日期转换字符串
        LocalDate date7 = LocalDate.now();// 获取当前日期
        // 自定义格式
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        String sdate3 = date7.format(dtf);// 转换为字符串
        // 输出
        System.out.println(sdate3);
    }
}

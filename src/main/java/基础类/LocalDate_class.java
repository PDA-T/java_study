package ������;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.TimeZone;

/**
 * Java8�µ�ʱ����
 */
public class LocalDate_class {
    public static void main(String[] args) {
        // ��ȡ��ǰ����,������ʱ��
        LocalDate date = LocalDate.now();
        // �����ǰ����
        System.out.println(date);
        // ��ȡ��ǰʱ��,����������
        LocalTime time = LocalTime.now();
        // �����ǰʱ��
        System.out.println(time);
        // �����ǰ��,��,��(����Ҫ������һ����Ҫ+1900)
        System.out.println(date.getYear());
        System.out.println(date.getMonthValue());
        System.out.println(date.getDayOfMonth());
        // �Զ�������
        LocalDate date2 = LocalDate.of(2021,12,10);
        System.out.println(date2);
        // �Ƚ��Ƿ����
        System.out.println(date.equals(date2));
        /*
         * �ж��Ƿ�������
         */
        LocalDate date3 = LocalDate.of(2002,4,19);
        // ���Զ���ȡ���յ��º��ձ��浽MonthDay
        MonthDay birthday = MonthDay.from(date3);
        // ��ȡ��ǰ���ڵ��º���
        MonthDay toDay = MonthDay.from(date);
        // �ж����ڵ��º����Ƿ�һ��
        if (birthday.equals(toDay)){
            System.out.println("����");
        }else {
            System.out.println("NO");
        }
        // ����Сʱ,����ʹ��LocalTime����,��Ϊ���ȡһ���¶���
        LocalTime time2 = time.plusHours(2);
        // ͨ������ʱ��,��һ������Ϊ���ӵ�����,�ڶ���Ϊָ�����ӵ�ʱ����,ʹ��ChronoUnit.ö��
        LocalTime time3 = time.plus(1, ChronoUnit.HOURS);
        // Clockʱ����,��ȡʱ���
        System.out.println(System.currentTimeMillis());
        System.out.println(Clock.systemUTC().millis());
        // ��ȡĬ��ʱ��
        System.out.println(TimeZone.getDefault());
        System.out.println(Clock.systemDefaultZone());
        // �ж�ĳһ������������һ�����ڵ�ǰ�滹�Ǻ���
        LocalDateTime d1 = LocalDateTime.of(2021,12,12,10,10);
        LocalDateTime d2 = LocalDateTime.of(2021,12,12,10,11);
        // ������÷��������ڱȸ�����������Ļ�����true
        System.out.println(d1.isBefore(d2));
        // �������෴
        System.out.println(d2.isAfter(d1));
        // ����ʱ����
        ZoneId zone = ZoneId.of("UTC");
        // ��ȡ��ͬʱ����ʱ��,ʹ�ô���ʱ����ʱ����ZonedDateTime
        ZonedDateTime zdt = ZonedDateTime.now(zone);
        // ���ʱ����ʱ��
        System.out.println(zdt);
        // ����ʱ�����
        Instant instant = Instant.now();
        // ���ʱ���
        System.out.println(instant);
        // ת���ɴ��б��ػ���ʱ��
        LocalDateTime ct = zdt.toLocalDateTime();
        // ��UTCʱ���ϻ�ȡ��ͬʱ����ʱ��,�޸ĺ����CTT���ɸ�Ϊ����ʱ��
        ZonedDateTime chinatime = ZonedDateTime.of(ct,ZoneId.of(ZoneId.SHORT_IDS.get("CTT")));
        // �������ʱ��
        System.out.println(chinatime);
        // ������������֮���Ӧ�������,��,��
        LocalDate birthday2 = LocalDate.of(2000,10,9);
        LocalDate date4 = LocalDate.of(2021,1,1);
        // ʹ��Period���ж�
        Period period = Period.between(birthday2,date4);
        // ʹ��Period��ķ����ж�������������
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
        // Stringת����
        String sdate = "20211212";
        // ʹ��Ԥ���ʽ
        LocalDate date5 = LocalDate.parse(sdate, DateTimeFormatter.BASIC_ISO_DATE);
        // �Զ����ʽ
        String sdate2 = "12 12 2021";
        LocalDate date6 = LocalDate.parse(sdate2, DateTimeFormatter.ofPattern("MM dd yyyy"));
        // ���ת�����ֵ
        System.out.println(date5);
        // ����ת���ַ���
        LocalDate date7 = LocalDate.now();// ��ȡ��ǰ����
        // �Զ����ʽ
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy��MM��dd��");
        String sdate3 = date7.format(dtf);// ת��Ϊ�ַ���
        // ���
        System.out.println(sdate3);
    }
}

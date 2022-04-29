package ������;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * ��̭������ʱ����
 */
public class Date_class {
    public static void main(String[] args) {
        Date date=new Date();
        // �����ǰʱ��
        System.out.println(date);
        // Date���Ѿ���̭,�����ԭ�������Ҫ-1900�·�-1
        Date date2=new Date(2021-1900,12-1,11);
        System.out.println(date2);
        // ����������
        new Calendar_class().Calendar_class();
        new SimpleDateFormat_class().SimpleDateFormat_class();
        // ����������57��������
        System.out.println(DateUtils.getDistanceDay(57));
    }
}

/**
 * ������,���ܶ�,ʹ������
 */
class Calendar_class{
    public void Calendar_class(){
        // ��ȡ���������
        Calendar calendar=Calendar.getInstance();
        // ���ʱ��
        System.out.println(calendar);
        // ����ʱ��,�·���Ҫ-1
        calendar.set(2021,12-1,11);
        // ���õ���ʱ��
        calendar.set(Calendar.DATE,10);
        // ����ʱ������,����Ϊ��
        calendar.add(Calendar.DATE,5);
        // ���ʱ��
        System.out.println(calendar);
        // ��ȡ����ֵ,��ȡ�·�Ҫ+1
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        // Calendar������
        GregorianCalendar gCalendar=new GregorianCalendar();
        // �ж��Ƿ�Ϊ����
        System.out.println(gCalendar.isLeapYear(2021));
    }
}

/**
 * ����ʱ�乤����,���Ի���ת��
 */
class SimpleDateFormat_class{
    public void SimpleDateFormat_class(){
        Date date = new Date();
        // ת��Ϊ������
        Long time=date.getTime();
        // ת��ΪDate
        System.out.println(new Date(time));
        // Calendar�໥ת
        Calendar calendar = Calendar.getInstance();
        // ת��Ϊ�����ͺ���
        Long time2 = calendar.getTimeInMillis();
        // ���ת�����ֵ
        System.out.println(time2);
        // ת��ΪCalendar
        calendar.setTimeInMillis(time2);
        // ���ת�����ֵ
        System.out.println(calendar);
        Date date2 = new Date();
        // ����������
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // Dateת����ʽ
        System.out.println(sdf.format(date2));
        String d = "2021-12-11 19:36:11";
        try {
            //�ַ���ת��ΪDate
            System.out.println((sdf).parse(d));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // ʹ���Լ�д�Ĺ������ж��Ƿ�����
        System.out.println(DateUtils.DateUtils(2031));
        // ʹ���Լ�д�Ĺ������ȡʱ��
        System.out.println(DateUtils.getCurrentDate());
        // ʹ���Լ�д�Ĺ������ȡ����һ�������ڼ�
        String date3 = "2021-5-8";
        System.out.println(DateUtils.getWeek(date3));
    }
}
class DateUtils{
    /**
     * �ж��Ƿ�Ϊ����
     * @param year
     * @return
     */
    public static boolean DateUtils(int year){
        GregorianCalendar calendar = new GregorianCalendar();
        return calendar.isLeapYear(year);
    }

    /**
     * ��ȡ����鿴��ʱ��
     * @return
     */
    public static String getCurrentDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(System.currentTimeMillis());
    }

    /**
     * ��������һ�������յõ����������ڼ�
     * @param date
     * @return
     */
    public static int getWeek(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // ����Calendar��,������ǰ����ʱ��
        Calendar c = Calendar.getInstance();
        try {
            // ��ʽ�����µ�Date
            Date d = dateFormat.parse(date);
            // ��ת�����Date�ŵ�Calendar
            c.setTime(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // ����Calendar�����ڼ�,��������0,������Ҫ-1
        return c.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * ��þ������n�����һ�������
     * @param day
     * @return
     */
    public static String getDistanceDay(int day){
        // ��ȡ��ǰʱ��
        Calendar calendar = Calendar.getInstance();
        // ���ϴ����dayʱ��
        calendar.add(Calendar.DAY_OF_MONTH,day);
        // ת��ΪDate
        Date date = calendar.getTime();
        // ��ʽ��Ϊ����鿴��ʱ��
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // ���String
        return dateFormat.format(date);
    }

    /**
     * ��ø�������������������
     * @param date1
     * @param date2
     * @return
     */
    public static long getGapDays(String date1,String date2){
        // ͨ��-�ɴ����ֵ���Ϊ�ַ�������
        String[] d1 = date1.split("-");
        String[] d2 = date2.split("-");
        // ����Calendar����
        Calendar c = Calendar.getInstance();
        // ����������,ʱ���붼Ϊ0
        c.set(Integer.parseInt(d1[0]),Integer.parseInt(d1[1]),Integer.parseInt(d1[2]),
                0,0,0);
        // �õ�����ʱ��ĳ�����
        long l1 = c.getTimeInMillis();
        // ����������,ʱ���붼Ϊ0
        c.set(Integer.parseInt(d2[0]),Integer.parseInt(d2[1]),Integer.parseInt(d2[2]),
                0,0,0);
        // �õ�����ʱ��ĳ�����
        long l2 = c.getTimeInMillis();
        // ͨ�������������֮�������ֵ�ڳ���(��,Сʱ,����,����)�Ļ��ó������ʱ��
        return (Math.abs(l1-l2)/(24*60*60*1000));
    }
}
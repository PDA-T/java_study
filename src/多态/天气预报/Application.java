package ��̬.����Ԥ��;

import com.imooc.weather.DayWeather;
import com.imooc.weather.HourWeather;
import com.imooc.weather.WeatherUtils;
import com.imooc.weather.impl.WeatherUtilsImpl;

import java.util.List;
import java.util.Scanner;

public class Application {// ������ѯ��
    public static void main(String[] args) {// ����
        System.out.println("��ѯ�������Ԥ��");// ��ӡ��ʾ
        System.out.println("����1:��ѯδ��24Сʱ����Ԥ��");// ��ӡ��ʾ
        System.out.println("����2:��ѯδ��3������Ԥ��");// ��ӡ��ʾ
        System.out.println("����3:��ѯδ��7������Ԥ��");// ��ӡ��ʾ
        System.out.print("������:");// ��ӡ��ʾ
        Scanner scanner=new Scanner(System.in);// ����ɨ����
        int i=scanner.nextInt();// ��ȡ�����ֵ
        if (i==1){// ���ֵ����һ
            System.out.print("�������ѯ����:");// ��ӡ��ʾ
            String city=scanner.next();// ��ȡ����ĳ���ֵ
            WeatherUtils weatherUtils= new WeatherUtilsImpl();// ʵ����������ѯ�ӿ�
            // ����24Сʱ������ѯ���������б�
            List<HourWeather> weatherlist=weatherUtils.w24h("24da2819121e4b129d1fec38dd09470f",city);
            if (weatherlist.size()==0){// ����б��ȵ�����
                System.out.println("δ��¼");// ��ӡ��ʾ
            }else {// ����
                for (HourWeather hourWeather:weatherlist){// �����б�ֵ
                    String template="%s��%s��%sʱ|%-3s|%-20s|%-2s|%s��C";// �������ģ��
                    String[] s=new String[]{// �����ַ�������
                            hourWeather.getMonth(),// ��
                            hourWeather.getDay(),// ��
                            hourWeather.getHour(),// ʱ
                            hourWeather.getWindDirection(),// ����
                            hourWeather.getWindPower(),// ����
                            hourWeather.getWeather(),// ����
                            hourWeather.getTemperature()// ��
                    };
                    String row=String.format(template,s);// ����ָ����ʽ������ֵ�����ַ���ģ��
                    System.out.println(row);// ��ӡ������
                }
            }
        }else if (i==2){// ���ֵ���ڶ�
            System.out.print("�������ѯ����:");// ��ӡ���
            String city=scanner.next();// ��ȡ����ĳ���ֵ
            WeatherUtils weatherUtils=new WeatherUtilsImpl();// ʵ����������ѯ�ӿ�
            // ����3��������ѯ���������б�
            List<DayWeather> weatherList=weatherUtils.w3d("24da2819121e4b129d1fec38dd09470f",city);
            if (weatherList.size()==0){// ����б��ȵ�����
                System.out.println("δ¼��");// ��ӡ��ʾ
            }else {// ����
                for (DayWeather dayWeather:weatherList){// �����б�ֵ
                    // �������ģ��
                    String template="%s��%s��|����:%s��C(��) %s��C(ҹ)|����:%s(��) %s(ҹ)|����:%s(��) %s(ҹ)";
                    String[] s=new String[]{// �����ַ�������
                            dayWeather.getMonth(),// ��
                            dayWeather.getDay(),// ��
                            dayWeather.getDayAirTemperature(),// ��������
                            dayWeather.getNightAirTemperature(),// ҹ������
                            dayWeather.getDayWeather(),// ��������
                            dayWeather.getNightWeather(),// ҹ������
                            dayWeather.getDayWindPower(),// �������
                            dayWeather.getNightWindPower()// ҹ�����
                    };
                    String row=String.format(template,s);// ����ָ����ʽ������ֵ�����ַ���ģ��
                    System.out.println(row);// ��ӡ������
                }
            }
        }else if (i==3){// ���ֵ������
            System.out.print("�������ѯ����:");// ��ӡ���
            String city=scanner.next();// ��ȡ����ĳ���ֵ
            WeatherUtils weatherUtils=new WeatherUtilsImpl();// ʵ����������ѯ�ӿ�
            // ����7��������ѯ���������б�
            List<DayWeather> weatherList=weatherUtils.w7d("24da2819121e4b129d1fec38dd09470f",city);
            if (weatherList.size()==0){// ����б��ȵ�����
                System.out.println("δ¼��");// ��ӡ��ʾ
            }else {// ����
                for (DayWeather dayWeather:weatherList){// �����б�ֵ
                    // �������ģ��
                    String template="%s��%s��%s��|����:%s��C(��) %s��C(ҹ)|����:%s(��) %s(ҹ)|����:%s(��) %s(ҹ)";
                    String[] s=new String[]{// �����ַ�������
                            dayWeather.getYear(),// ��
                            dayWeather.getMonth(),// ��
                            dayWeather.getDay(),// ��
                            dayWeather.getDayAirTemperature(),// ��������
                            dayWeather.getNightAirTemperature(),// ҹ������
                            dayWeather.getDayWeather(),// ��������
                            dayWeather.getNightWeather(),// ҹ������
                            dayWeather.getDayWindPower(),// �������
                            dayWeather.getNightWindPower()// ҹ�����
                    };
                    String row=String.format(template,s);// ����ָ����ʽ������ֵ�����ַ���ģ��
                    System.out.println(row);// ��ӡ������
                }
            }
        }else {// ����
            System.out.println("����");// ��ӡ��ʾ
        }
    }
}
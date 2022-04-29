package 多态.天气预报;

import com.imooc.weather.DayWeather;
import com.imooc.weather.HourWeather;
import com.imooc.weather.WeatherUtils;
import com.imooc.weather.impl.WeatherUtilsImpl;

import java.util.List;
import java.util.Scanner;

public class Application {// 天气查询类
    public static void main(String[] args) {// 主类
        System.out.println("查询最近天气预报");// 打印提示
        System.out.println("输入1:查询未来24小时天气预报");// 打印提示
        System.out.println("输入2:查询未来3天天气预报");// 打印提示
        System.out.println("输入3:查询未来7天天气预报");// 打印提示
        System.out.print("请输入:");// 打印提示
        Scanner scanner=new Scanner(System.in);// 创建扫描器
        int i=scanner.nextInt();// 获取输入的值
        if (i==1){// 如果值等于一
            System.out.print("请输入查询城市:");// 打印提示
            String city=scanner.next();// 获取输入的城市值
            WeatherUtils weatherUtils= new WeatherUtilsImpl();// 实例化天气查询接口
            // 调用24小时天气查询方法存入列表
            List<HourWeather> weatherlist=weatherUtils.w24h("24da2819121e4b129d1fec38dd09470f",city);
            if (weatherlist.size()==0){// 如果列表长度等于零
                System.out.println("未收录");// 打印提示
            }else {// 否则
                for (HourWeather hourWeather:weatherlist){// 遍历列表值
                    String template="%s月%s日%s时|%-3s|%-20s|%-2s|%s°C";// 创建输出模板
                    String[] s=new String[]{// 创建字符串数组
                            hourWeather.getMonth(),// 月
                            hourWeather.getDay(),// 日
                            hourWeather.getHour(),// 时
                            hourWeather.getWindDirection(),// 风向
                            hourWeather.getWindPower(),// 风速
                            hourWeather.getWeather(),// 天气
                            hourWeather.getTemperature()// 度
                    };
                    String row=String.format(template,s);// 按照指定格式将数组值存入字符串模板
                    System.out.println(row);// 打印输出结果
                }
            }
        }else if (i==2){// 如果值等于二
            System.out.print("请输入查询城市:");// 打印输出
            String city=scanner.next();// 获取输入的城市值
            WeatherUtils weatherUtils=new WeatherUtilsImpl();// 实例化天气查询接口
            // 调用3天天气查询方法存入列表
            List<DayWeather> weatherList=weatherUtils.w3d("24da2819121e4b129d1fec38dd09470f",city);
            if (weatherList.size()==0){// 如果列表长度等于零
                System.out.println("未录入");// 打印提示
            }else {// 否则
                for (DayWeather dayWeather:weatherList){// 遍历列表值
                    // 创建输出模板
                    String template="%s月%s日|气温:%s°C(日) %s°C(夜)|天气:%s(日) %s(夜)|风力:%s(日) %s(夜)";
                    String[] s=new String[]{// 创建字符串数组
                            dayWeather.getMonth(),// 月
                            dayWeather.getDay(),// 日
                            dayWeather.getDayAirTemperature(),// 白天气温
                            dayWeather.getNightAirTemperature(),// 夜晚气温
                            dayWeather.getDayWeather(),// 白天天气
                            dayWeather.getNightWeather(),// 夜晚天气
                            dayWeather.getDayWindPower(),// 白天风力
                            dayWeather.getNightWindPower()// 夜晚风力
                    };
                    String row=String.format(template,s);// 按照指定格式将数组值存入字符串模板
                    System.out.println(row);// 打印输出结果
                }
            }
        }else if (i==3){// 如果值等于三
            System.out.print("请输入查询城市:");// 打印输出
            String city=scanner.next();// 获取输入的城市值
            WeatherUtils weatherUtils=new WeatherUtilsImpl();// 实例化天气查询接口
            // 调用7天天气查询方法存入列表
            List<DayWeather> weatherList=weatherUtils.w7d("24da2819121e4b129d1fec38dd09470f",city);
            if (weatherList.size()==0){// 如果列表长度等于零
                System.out.println("未录入");// 打印提示
            }else {// 否则
                for (DayWeather dayWeather:weatherList){// 遍历列表值
                    // 创建输出模板
                    String template="%s年%s月%s日|气温:%s°C(日) %s°C(夜)|天气:%s(日) %s(夜)|风力:%s(日) %s(夜)";
                    String[] s=new String[]{// 创建字符串数组
                            dayWeather.getYear(),// 年
                            dayWeather.getMonth(),// 月
                            dayWeather.getDay(),// 日
                            dayWeather.getDayAirTemperature(),// 白天气温
                            dayWeather.getNightAirTemperature(),// 夜晚气温
                            dayWeather.getDayWeather(),// 白天天气
                            dayWeather.getNightWeather(),// 夜晚天气
                            dayWeather.getDayWindPower(),// 白天风力
                            dayWeather.getNightWindPower()// 夜晚风力
                    };
                    String row=String.format(template,s);// 按照指定格式将数组值存入字符串模板
                    System.out.println(row);// 打印输出结果
                }
            }
        }else {// 否则
            System.out.println("错误");// 打印提示
        }
    }
}
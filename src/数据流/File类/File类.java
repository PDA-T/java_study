package 数据流.File类;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class File类 {
    public static void main(String[] args) {
        // 项目下默认路径直接写文件名即可
        // 包中文件路径:src/路径即可
        File f1=new File("C:/Users/15811/Desktop/abc.txt");// 创建文件对象
        /*File f2=new File("C:/Users/15811/Desktop","abc.txt");// 第二种方法
        File wjj=new File("C:/Users/15811/Desktop");// 文件夹
        File f3=new File(wjj,"abc.txt");// 第三种方法
        System.out.println(f1.getAbsolutePath());// 输出路径
        System.out.println(f2.getAbsolutePath());// 输出路径
        System.out.println(f3.getAbsolutePath());// 输出路径
        System.out.println(f1==f2);// 假
        System.out.println(f1.equals(f2));// 真*/
        System.out.println("文件是否存在:"+f1.exists());// 判断文件是否存在
        System.out.println("文件名:"+f1.getName());// 输出文件名
        System.out.println("文件绝对路径:"+f1.getAbsolutePath());// 输出绝对路径
        System.out.println("是否隐藏文件:"+f1.isHidden());// 是否隐藏文件
        System.out.println("文件大小:"+f1.length());// 输出文件字节数
        //System.out.println("文件最后的修改时间:"+f1.lastModified());// 输出文件创建时间(毫秒)
        Date d=new Date(f1.lastModified());// 通过毫秒值创建日期类
        SimpleDateFormat riqi=new SimpleDateFormat("yyyy/MM/dd HH/mm/ss");// 日期格式化类
        System.out.println("文件最后修改日期:"+riqi.format(d));// 文件最后的修改日期
        //System.out.println("删除成功:"+f1.delete());// 删除文件
        if (f1.delete()==false){// 判断如果文件不存在就创建
            try {
                System.out.println("创建文件是否成功:"+f1.createNewFile());// 创建文件,如果文件存在不能覆盖
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 文件夹操作
        File dir=new File("C:/Users/15811/Desktop/dir");// 创建文件夹对象
        System.out.println("文件夹创建成功:"+dir.mkdir());// 创建文件夹
        File dir2=new File("C:/Users/15811/Desktop/dir/dir2/dir3/dir4");// 创建文件夹对象
        //System.out.println("多层文件夹创建成功:"+dir2.mkdirs());// 可创建多层文件夹
        System.out.println("文件夹是否删除:"+dir.delete());// 删除文件夹(删除文件路径最后一个文件夹)
        File win=new File("C:/Windows");// 创建文件夹对象
        File file[]=win.listFiles();// 返回文件夹内全部的文件(返回文件数组)
        for (File f:file){// 遍历返回的文件数组
            if (f.isFile()){// 判断是否为文件
                System.out.println("文件名:"+f.getName());// 获取文件名
            }else if (f.isDirectory()){// 判断是否为文件夹
                System.out.println("文件夹名:"+f.getName());// 获取文件夹名
            }
        }
    }
}
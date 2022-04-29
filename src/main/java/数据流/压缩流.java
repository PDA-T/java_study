package 数据流;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class 压缩流 {
    static void compress(){// 压缩方法
        File source=new File("C:/Users/15811/Desktop/steamapps");// 源文件
        File target=new File("C:/Users/15811/Desktop/mr.zip");// 压缩包
        FileOutputStream fis=null;// 创建文件输出流
        ZipOutputStream zos=null;// 创建压缩输出流
        try{
            fis=new FileOutputStream(target);// 实例化文件输出流
            zos=new ZipOutputStream(fis);// 实例化压缩输出流
            if (source.isDirectory()){// 判断源文件是否是文件夹
                for (File f:source.listFiles()){// 循环判断返回文件和目录
                    addEntry(zos,"",f);// 调用添加条目
                }
            }else{// 如果不是文件夹则压缩
                addEntry(zos,"",source);// 调用递归
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * @param zos-压缩流
     * @param base-文件在压缩包中的路径
     * @param source-被压缩的文件
     */
    static void addEntry(ZipOutputStream zos,String base,File source){
        FileInputStream fis=null;// 创建文件输入流
        if (source.isDirectory()){// 判断如果传入的文件是文件夹
            for (File file:source.listFiles()){// 取出文件夹中所有的文件
                addEntry(zos,base+source.getName()+File.separator,file);// 递归
            }
        }else{
            byte buf[]=new byte[1024];// 缓冲区
            try{
                fis=new FileInputStream(source);// 实例化文件输入流
                int count=-1;// 读出字节数
                zos.putNextEntry(new ZipEntry(base+source.getName()));// 在压缩包添加条目
                while ((count=fis.read(buf))!=-1){// 读取
                    zos.write(buf,0,count);// 写入
                    zos.flush();// 刷新
                }
                zos.closeEntry();// 关闭条目
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    static void decompression(){// 解压缩
        File dir=new File("C:/Users/15811/Desktop");// 源文件
        File source=new File("C:/Users/15811/Desktop/mr.zip");// 压缩包
        FileInputStream fis=null;// 创建文件输入流
        ZipInputStream zis=null;// 创建压缩输入流
        byte buf[]=new byte[1024];// 缓冲区
        ZipEntry entry=null;// 创建条目
        try{
            fis=new FileInputStream(source);// 实例化文件输入流
            zis=new ZipInputStream(fis);// 实例化压缩输入流
            while (true){
                entry=zis.getNextEntry();// 获取一个条目
                if (entry==null){// 如果为空
                    break;// 结束循环
                }
                if (entry.isDirectory()){// 如果是一个文件夹
                    continue;// 跳过此次循环
                }
                File f=new File(dir,entry.getName());// 创建文件对象
                if (!f.getParentFile().exists()){// 如果解压的文件不存在
                    f.getParentFile().mkdirs();// 创建
                }
                int count=-1;// 计数器
                FileOutputStream fos=new FileOutputStream(f);// 创建文件输出流
                while ((count=zis.read(buf))!=-1){// 读取
                    fos.write(buf,0,count);// 写入
                    fos.flush();// 刷新
                }
                fos.close();// 关闭
                zis.closeEntry();// 关闭
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        compress();// 压缩
        decompression();// 解压缩
    }
}

package ������;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ѹ���� {
    static void compress(){// ѹ������
        File source=new File("C:/Users/15811/Desktop/steamapps");// Դ�ļ�
        File target=new File("C:/Users/15811/Desktop/mr.zip");// ѹ����
        FileOutputStream fis=null;// �����ļ������
        ZipOutputStream zos=null;// ����ѹ�������
        try{
            fis=new FileOutputStream(target);// ʵ�����ļ������
            zos=new ZipOutputStream(fis);// ʵ����ѹ�������
            if (source.isDirectory()){// �ж�Դ�ļ��Ƿ����ļ���
                for (File f:source.listFiles()){// ѭ���жϷ����ļ���Ŀ¼
                    addEntry(zos,"",f);// ���������Ŀ
                }
            }else{// ��������ļ�����ѹ��
                addEntry(zos,"",source);// ���õݹ�
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * @param zos-ѹ����
     * @param base-�ļ���ѹ�����е�·��
     * @param source-��ѹ�����ļ�
     */
    static void addEntry(ZipOutputStream zos,String base,File source){
        FileInputStream fis=null;// �����ļ�������
        if (source.isDirectory()){// �ж����������ļ����ļ���
            for (File file:source.listFiles()){// ȡ���ļ��������е��ļ�
                addEntry(zos,base+source.getName()+File.separator,file);// �ݹ�
            }
        }else{
            byte buf[]=new byte[1024];// ������
            try{
                fis=new FileInputStream(source);// ʵ�����ļ�������
                int count=-1;// �����ֽ���
                zos.putNextEntry(new ZipEntry(base+source.getName()));// ��ѹ���������Ŀ
                while ((count=fis.read(buf))!=-1){// ��ȡ
                    zos.write(buf,0,count);// д��
                    zos.flush();// ˢ��
                }
                zos.closeEntry();// �ر���Ŀ
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    static void decompression(){// ��ѹ��
        File dir=new File("C:/Users/15811/Desktop");// Դ�ļ�
        File source=new File("C:/Users/15811/Desktop/mr.zip");// ѹ����
        FileInputStream fis=null;// �����ļ�������
        ZipInputStream zis=null;// ����ѹ��������
        byte buf[]=new byte[1024];// ������
        ZipEntry entry=null;// ������Ŀ
        try{
            fis=new FileInputStream(source);// ʵ�����ļ�������
            zis=new ZipInputStream(fis);// ʵ����ѹ��������
            while (true){
                entry=zis.getNextEntry();// ��ȡһ����Ŀ
                if (entry==null){// ���Ϊ��
                    break;// ����ѭ��
                }
                if (entry.isDirectory()){// �����һ���ļ���
                    continue;// �����˴�ѭ��
                }
                File f=new File(dir,entry.getName());// �����ļ�����
                if (!f.getParentFile().exists()){// �����ѹ���ļ�������
                    f.getParentFile().mkdirs();// ����
                }
                int count=-1;// ������
                FileOutputStream fos=new FileOutputStream(f);// �����ļ������
                while ((count=zis.read(buf))!=-1){// ��ȡ
                    fos.write(buf,0,count);// д��
                    fos.flush();// ˢ��
                }
                fos.close();// �ر�
                zis.closeEntry();// �ر�
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        compress();// ѹ��
        decompression();// ��ѹ��
    }
}

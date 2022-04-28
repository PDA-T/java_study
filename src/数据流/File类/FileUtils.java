package ������.File��;

import java.io.File;
import java.io.IOException;

/**
 * �г�File��һЩ���ò���������ˡ�������
 */
public class FileUtils {
    /**
     * �г�ָ��Ŀ¼��(������Ŀ¼)�������ļ�
     * @param dir
     * @throws IOException
     */
    public static void listDirectory(File dir)throws IOException{
        if (!dir.exists()){// �ж��ļ��Ƿ����
            throw new IllegalArgumentException("Ŀ¼:"+dir+"������");// �׳��쳣
        }
        if (!dir.isDirectory()){// �ж�File��Ķ����Ƿ���Ŀ¼
            throw new IllegalArgumentException(dir+"����Ŀ¼");// �׳��쳣
        }
        /*String[] filenames=dir.list();// �����ַ�������
        for (String string:filenames){// ��������
            System.out.println(dir+"\\"+string);// ��ӡ���
        }*/
        File[] files=dir.listFiles();// ���ص���ֱ����Ŀ¼(�ļ�)�ĳ���
        if (files!=null&&files.length>0){// �ж��ļ��Ƿ�Ϊ�ղ������鳤�ȴ���0
            for (File file:files){// ��������
                if (file.isDirectory()){// ����ļ���Ŀ¼
                    listDirectory(file);// �ݹ�
                }else {// ����
                    System.out.println(file);// ��ӡ���
                }
            }
        }
    }
}

/**
 * ������
 */
class FileUtilTest1{
    public static void main(String[] args) throws IOException{
        FileUtils.listDirectory(new File("C:/"));// �����г�Ŀ¼����
    }
}

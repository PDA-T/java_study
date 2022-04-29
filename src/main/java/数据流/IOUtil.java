package ������;

import java.io.*;

public class IOUtil {
    /**
     * ��ȡָ���ļ�����,����16�������������̨
     * ÿ���10��byte����
     * @param fileName �ļ�����
     */
    public static void printHex(String fileName)throws IOException {
        FileInputStream in=new FileInputStream(fileName);// �����ļ�������
        int b;// ������ȡ�ֽڱ���
        int i=1;// ����ÿ�ζ�ȡ��������
        while ((b=in.read())!=-1){// ����ѭ��
            if (b<=0xf){// �����ȡ���ֽ�С��1λ
                System.out.print("0");// ǰ�油0
            }
            System.out.print(Integer.toHexString(b)+" ");// ��ӡ���16����
            if (i++%10==0){// �ж��Ƿ�Ϊ10��byte
                System.out.println();// ����
            }
        }
        in.close();// �ر��ļ�������
    }
    /**
     * �ļ���������,�ֽ�������ȡ
     * @param srcFile
     * @param destFile
     * @throws IOException
     */
    public static void copyFile(File srcFile,File destFile)throws IOException{
        if (!srcFile.exists()){// �ж��ļ��Ƿ����
            throw new IllegalArgumentException("�ļ�:"+srcFile+"������");// �׳��쳣
        }
        if (!srcFile.isFile()){// �ж��Ƿ����ļ�
            throw new IllegalArgumentException("�ļ�:"+srcFile+"�����ļ�");// �׳��쳣
        }
        FileInputStream in=new FileInputStream(srcFile);// �����ļ�������
        FileOutputStream out=new FileOutputStream(destFile);// �����ļ������
        byte[] buf=new byte[8*1024];// ����������
        int b;// ������ȡ�ֽڱ���
        while ((b=in.read(buf,0,buf.length))!=-1){// ��ȡ�ֽڱ�������-1��ʼѭ��
            out.write(buf,0,b);// д���ļ�
            out.flush();// ˢ�»�����
        }
        in.close();// �ر��ļ�������
        out.close();// �ر��ļ������
    }
    /**
     * ���ô�������ֽ��������ļ�����
     * @param srcFile
     * @param destFile
     * @throws IOException
     */
    public static void copyFileByBuffer(File srcFile,File destFile)throws IOException{
        if (!srcFile.exists()){// �ж��ļ��Ƿ����
            throw new IllegalArgumentException("�ļ�:"+srcFile+"������");// �׳��쳣
        }
        if (!srcFile.isFile()){// �ж��Ƿ����ļ�
            throw new IllegalArgumentException("�ļ�:"+srcFile+"�����ļ�");// �׳��쳣
        }
        BufferedInputStream bis=new BufferedInputStream(new FileInputStream(srcFile));// ��������������
        BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(destFile));// �������������
        int c;// ������ȡ�ֽڱ���
        while ((c=bis.read())!=-1){// ��ȡ�ֽڱ�������-1��ʼѭ��
            bos.write(c);// д���ֽ�
            bos.flush();// ˢ�»�����
        }
        bis.close();// �رջ���������
        bos.close();// �رջ��������
    }

    /**
     * ���ֽ�,������������ļ�����
     * @param srcFile
     * @param destFile
     * @throws IOException
     */
    public static void copyFileByByte(File srcFile,File destFile)throws IOException{
        if (!srcFile.exists()){// �ж��ļ��Ƿ����
            throw new IllegalArgumentException("�ļ�:"+srcFile+"������");// �׳��쳣
        }
        if (!srcFile.isFile()){// �ж��Ƿ����ļ�
            throw new IllegalArgumentException("�ļ�:"+srcFile+"�����ļ�");// �׳��쳣
        }
        FileInputStream in=new FileInputStream(srcFile);// �����ļ�������
        FileOutputStream out=new FileOutputStream(destFile);// �����ļ������
        int c;// ������ȡ�ֽڱ���
        while ((c=in.read())!=-1){// ��ȡ�ֽڱ�������-1��ʼѭ��
            out.write(c);// д���ֽ�
            out.flush();// ˢ�»�����
        }
        in.close();// �ر��ļ�������
        out.close();// �ر��ļ������
    }
    public static void printHexByByteArray(String fileName)throws IOException{
        FileInputStream in=new FileInputStream(fileName);// �����ļ�������
        byte[] buf=new byte[20*1024];// ����20k��С�Ļ�����
        int bytes=in.read(buf,0,buf.length);// ��in�ж�ȡ�ֽ�,�ӵ�0��ȡ������
        int j=1;// ����ÿ�ζ�ȡ��������
        for (int i=0;i<bytes;i++){// ѭ������
            if (buf[i]<=0xf){// ��������ֵС�ڵ���һλ
                System.out.print("0");// ǰ�油0
            }
            System.out.println(Integer.toHexString(buf[i])+" ");// ���16����
            if (j++%10==0){// �����ȡ10��byte
                System.out.println();// ����
            }
        }
        /**
         * �ڶ��ַ���
         * ����Ҫ����������
         */
        int bytes2=0;// ������ȡ�ֽڱ���
        int j2=1;// ����ÿ�ζ�ȡ��������
        while ((bytes2=in.read(buf,0,buf.length))!=-1){// ��ȡ�ֽڱ�������-1��ʼѭ��
            for (int i=0;i<bytes2;i++){// ÿ�ζ�ȡ��������С�ڶ�ȡ�ֽڱ�����ʼѭ��
                System.out.println(Integer.toHexString(buf[i]&0xff)+" ");// ���16����
                if (j2++%10==0){// �����ȡ10��byte
                    System.out.println();// ����
                }
            }
        }
    }
}
/**
 * ����������
 */
class IOUtilTest1{
    public static void main(String[] args) {
        try {// ��׽�쳣
            IOUtil.printHex("C:/Users/15811/Desktop/abc.txt");// �����ļ�·��
            IOUtil.printHexByByteArray("C:/Users/15811/Desktop/abc.txt");// �����ļ�·��
            // ���÷���
            IOUtil.copyFile(new File("C:/Users/15811/Desktop/abc.txt"),new File("C:/Users/15811/Desktop/abc.txt"));
            // ���û���������
            IOUtil.copyFileByBuffer(new File("C:/Users/15811/Desktop/abc.txt"),new File("C:/Users/15811/Desktop/abc.txt"));
            // ���õ��ֽڷ���
            IOUtil.copyFileByByte(new File("C:/Users/15811/Desktop/abc.txt"),new File("C:/Users/15811/Desktop/abc.txt"));
        } catch (IOException e) {// �����쳣
            e.printStackTrace();// ��ӡ�쳣
        }
    }
}

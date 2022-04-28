package ������.�߼�������.AIO;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * AIOΪNIO�Ľ��װ�(NIO2.0)
 * �첽������
 * ���ַ���
 */
public class PathTest {
    public static void main(String[] args) {
        // ��ȡ·��,����File
        Path path = Paths.get("D:/abc.txt");
        // ��ǰĿ¼
        Path path2 = Paths.get(".","abc.txt");
        // �ϲ�Ŀ¼
        Path path3 = Paths.get("..","abc.txt");
        // �ж�·���Ƿ����,�������Ϊ�̶�д��
        System.out.println(Files.exists(path,new LinkOption[]{LinkOption.NOFOLLOW_LINKS}));
        // �ж�Ŀ¼�Ƿ����
        if (!Files.exists(path,new LinkOption[]{LinkOption.NOFOLLOW_LINKS})){
            try {
                // ����Ŀ¼
                Files.createDirectory(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            // �ļ�����,�Ѳ���һ���Ƶ�������,���Ŀ���ļ��Ѿ����ڻᱨ��
            Files.copy(path,path2);
            // �ļ�����,�Ѳ���һ���Ƶ�������,���Ŀ���ļ��Ѿ����ڻ��滻
            Files.copy(path,path2, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // �ļ��ƶ�,Ҳ������������(�ƶ�����ͬĿ¼��),Ҳ���Ժ�����һ��д����������
            Files.move(path,path2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // �ļ�ɾ��(ɾ��Ŀ¼ʱ����ǿ�)
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * �����ļ�
     */
    public void walkTree(){
        // ����Ҫ�����ĸ�Ŀ¼
        Path rootPath = Paths.get("C:/");
        // ����Ҫ�ҵ�Ŀ���ļ�
        String desFile = File.separator+"auth.ini";
        try {
            // ���ñ�������
            Files.walkFileTree(rootPath, new FileVisitor<Path>() {
                /**
                 * ����ĳ���ļ���֮ǰ�����
                 * @param dir
                 * @param attrs
                 * @return ����ֵFileVisitResultΪö��(Դ��ɿ�),CONTINUE��ʾ����,TERMINATE��ʾ��ֹ
                 *          SKIP_SUBTREE��ʾ�����Ӽ�(ֻ�д˷�������������),SKIP_SIBLINGS��ʾ����ȫ��ͬ��Ŀ¼
                 * @throws IOException
                 */
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    // ���ؼ���ö��
                    return FileVisitResult.CONTINUE;
                }

                /**
                 * ÿ�α���Ŀ��Ŀ¼������ļ�ʱ����
                 * @param file
                 * @param attrs
                 * @return ����ֵFileVisitResultΪö��(Դ��ɿ�),CONTINUE��ʾ����,TERMINATE��ʾ��ֹ
                 *         SKIP_SUBTREE��ʾ�����Ӽ�(�ڴ˷�������û����),SKIP_SIBLINGS��ʾ����ȫ��ͬ��Ŀ¼
                 * @throws IOException
                 */
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    // ��ȡ����·��
                    String stringFile = file.toAbsolutePath().toString();
                    // ����ļ���β���ֵ���Ŀ���ļ��Ľ�β
                    if (stringFile.endsWith(desFile)){
                        // ����ҵ����ļ�·��
                        System.out.println("�ļ�·��:"+file.toAbsolutePath());
                        // ������ֹö��
                        return FileVisitResult.TERMINATE;
                    }
                    // û�ҵ����ؼ���ö��
//                    return FileVisitResult.CONTINUE;

                    /*
                     * ѭ������ɾ��Ŀ¼
                     * ���Ҫɾ�����ļ�
                     */
                    System.out.println("ɾ��:" + file.toString());
                    // ɾ���ļ�
//                    Files.delete(file);
                    // ���ؼ���ö��
                    return FileVisitResult.CONTINUE;
                }

                /**
                 * ����Ŀ��Ŀ¼������ļ�ʧ��ʱ����(�����ļ�ʧ��)
                 * @param file
                 * @param exc
                 * @return ����ֵFileVisitResultΪö��(Դ��ɿ�),CONTINUE��ʾ����,TERMINATE��ʾ��ֹ
                 *         SKIP_SUBTREE��ʾ�����Ӽ�(�ڴ˷�������û����),SKIP_SIBLINGS��ʾ����ȫ��ͬ��Ŀ¼
                 * @throws IOException
                 */
                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    // ���ؼ���ö��
                    return FileVisitResult.CONTINUE;
                }

                /**
                 * ������ĳ��Ŀ¼��ʱ�����
                 * @param dir
                 * @param exc
                 * @return ����ֵFileVisitResultΪö��(Դ��ɿ�),CONTINUE��ʾ����,TERMINATE��ʾ��ֹ
                 *         SKIP_SUBTREE��ʾ�����Ӽ�(�ڴ˷�������û����),SKIP_SIBLINGS��ʾ����ȫ��ͬ��Ŀ¼
                 * @throws IOException
                 */
                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    // ���ɾ����Ϣ
                    System.out.println("ɾ��Ŀ¼:" + dir);
                    // ɾ��Ŀ¼
//                    Files.delete(dir);
                    // ���ؼ���ö��
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package 数据流.高级数据流.AIO;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * AIO为NIO的进阶版(NIO2.0)
 * 异步非阻塞
 * 各种方法
 */
public class PathTest {
    public static void main(String[] args) {
        // 获取路径,类似File
        Path path = Paths.get("D:/abc.txt");
        // 当前目录
        Path path2 = Paths.get(".","abc.txt");
        // 上层目录
        Path path3 = Paths.get("..","abc.txt");
        // 判断路径是否存在,后面参数为固定写法
        System.out.println(Files.exists(path,new LinkOption[]{LinkOption.NOFOLLOW_LINKS}));
        // 判断目录是否存在
        if (!Files.exists(path,new LinkOption[]{LinkOption.NOFOLLOW_LINKS})){
            try {
                // 创建目录
                Files.createDirectory(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            // 文件复制,把参数一复制到参数二,如果目标文件已经存在会报错
            Files.copy(path,path2);
            // 文件复制,把参数一复制到参数二,如果目标文件已经存在会替换
            Files.copy(path,path2, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // 文件移动,也可以用来改名(移动到相同目录下),也可以和上面一样写第三个参数
            Files.move(path,path2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // 文件删除(删除目录时必须非空)
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 遍历文件
     */
    public void walkTree(){
        // 创建要遍历的根目录
        Path rootPath = Paths.get("C:/");
        // 创建要找的目标文件
        String desFile = File.separator+"auth.ini";
        try {
            // 调用遍历方法
            Files.walkFileTree(rootPath, new FileVisitor<Path>() {
                /**
                 * 遍历某个文件夹之前会调用
                 * @param dir
                 * @param attrs
                 * @return 返回值FileVisitResult为枚举(源码可看),CONTINUE表示继续,TERMINATE表示终止
                 *          SKIP_SUBTREE表示跳过子集(只有此方法返回有意义),SKIP_SIBLINGS表示跳过全部同级目录
                 * @throws IOException
                 */
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    // 返回继续枚举
                    return FileVisitResult.CONTINUE;
                }

                /**
                 * 每次遍历目标目录里的子文件时调用
                 * @param file
                 * @param attrs
                 * @return 返回值FileVisitResult为枚举(源码可看),CONTINUE表示继续,TERMINATE表示终止
                 *         SKIP_SUBTREE表示跳过子集(在此方法返回没意义),SKIP_SIBLINGS表示跳过全部同级目录
                 * @throws IOException
                 */
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    // 获取绝对路径
                    String stringFile = file.toAbsolutePath().toString();
                    // 如果文件结尾部分等于目标文件的结尾
                    if (stringFile.endsWith(desFile)){
                        // 输出找到的文件路径
                        System.out.println("文件路径:"+file.toAbsolutePath());
                        // 返回终止枚举
                        return FileVisitResult.TERMINATE;
                    }
                    // 没找到返回继续枚举
//                    return FileVisitResult.CONTINUE;

                    /*
                     * 循环遍历删除目录
                     * 输出要删除的文件
                     */
                    System.out.println("删除:" + file.toString());
                    // 删除文件
//                    Files.delete(file);
                    // 返回继续枚举
                    return FileVisitResult.CONTINUE;
                }

                /**
                 * 遍历目标目录里的子文件失败时调用(访问文件失败)
                 * @param file
                 * @param exc
                 * @return 返回值FileVisitResult为枚举(源码可看),CONTINUE表示继续,TERMINATE表示终止
                 *         SKIP_SUBTREE表示跳过子集(在此方法返回没意义),SKIP_SIBLINGS表示跳过全部同级目录
                 * @throws IOException
                 */
                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    // 返回继续枚举
                    return FileVisitResult.CONTINUE;
                }

                /**
                 * 遍历完某个目录的时候调用
                 * @param dir
                 * @param exc
                 * @return 返回值FileVisitResult为枚举(源码可看),CONTINUE表示继续,TERMINATE表示终止
                 *         SKIP_SUBTREE表示跳过子集(在此方法返回没意义),SKIP_SIBLINGS表示跳过全部同级目录
                 * @throws IOException
                 */
                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    // 输出删除信息
                    System.out.println("删除目录:" + dir);
                    // 删除目录
//                    Files.delete(dir);
                    // 返回继续枚举
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

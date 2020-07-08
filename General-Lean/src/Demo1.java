import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-06-17 23:16
 * @Version: 1.0
 */
public class Demo1 {
    public static void main(String[] args) throws IOException {
        mergeCrazyJavaCourseware();
    }

    /**
     * 合并疯狂java课件
     */
    private static void mergeCrazyJavaCourseware() throws IOException {
        String carzyPath = "F:\\My资料\\文件\\专业\\Java\\疯狂Java讲义\\课件";
        String targetPath = "F:\\My资料\\文件\\专业\\Java\\疯狂Java讲义\\课件\\合并";
        File coursewareFile = new File(carzyPath);
        for (File file : coursewareFile.listFiles()) {
            if (file.isDirectory()) {
                String fileName = file.getName();
                if (fileName.equalsIgnoreCase("合并")) {
                    continue;
                }
                String path = file.getPath();
                copyDir(path, targetPath);
            }
        }
    }


    public static void copyDir(String oldPath, String newPath) throws IOException {

        File file = new File(oldPath);
        String[] filePath = file.list();

        if (!(new File(newPath)).exists()) {
            (new File(newPath)).mkdir();
        }

        for (int i = 0; i < filePath.length; i++) {
            if ((new File(oldPath + File.separator + filePath[i])).isDirectory()) {
                copyDir(oldPath + File.separator + filePath[i], newPath + File.separator + filePath[i]);
            }

            if (new File(oldPath + File.separator + filePath[i]).isFile()) {
                File source = new File(oldPath + File.separator + filePath[i]);
                File dest = new File(newPath + File.separator + filePath[i]);
                if (!(dest.exists())) {
                    Files.copy(source.toPath(), dest.toPath());
                }
            }
        }
    }

    public static void copyFile(String oldPath, String newPath) throws IOException {
        File oldFile = new File(oldPath);
        File file = new File(newPath);
        FileInputStream in = new FileInputStream(oldFile);
        FileOutputStream out = new FileOutputStream(file);
        ;

        byte[] buffer = new byte[2097152];
        int readByte = 0;
        while ((readByte = in.read(buffer)) != -1) {
            out.write(buffer, 0, readByte);
        }

        in.close();
        out.close();
    }


}

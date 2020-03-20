package file;

import java.io.File;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-03-10 18:19
 * @Version: 1.0
 */
public class FileDemo {
    public static void main(String[] args) {
        fileBase();
        testIsDirectory();
    }

    public static void fileBase() {

        //windows为;  linux为:
        System.out.println(File.pathSeparator);
        //windows为反斜杠\，linux为\
        System.out.println(File.separator);
    }

    public static void testIsDirectory() {
        File file = new File("E:\\文件");
        if (file.isDirectory()) {
            System.out.println("是目录");
        }
    }
}

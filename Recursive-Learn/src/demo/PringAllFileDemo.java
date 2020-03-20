package demo;

import java.io.File;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-03-10 19:24
 * @Version: 1.0
 */
public class PringAllFileDemo {
    public static void main(String[] args) {
        printAllFile("E:\\文件\\生活");
    }

    public static void printAllFile(String dirPath) {
        File file = new File(dirPath);
        if (!file.exists()) {

            return;
        }
        if (file.isFile()) {
            return;
        }
        for (File eachFile : file.listFiles()) {
            System.out.println(eachFile.toString());
            if (eachFile.isDirectory()) {
                printAllFile(eachFile.toString());
            }
        }
        System.out.println("-----------------------");
    }
}

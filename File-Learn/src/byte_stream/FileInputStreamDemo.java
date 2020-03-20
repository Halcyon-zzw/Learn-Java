package byte_stream;

import java.io.*;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-03-10 20:52
 * @Version: 1.0
 */
public class FileInputStreamDemo {
    public static void main(String[] args) throws IOException {
        readMulti();
    }

    public static void readOne() throws IOException {
        FileInputStream fis = new FileInputStream("File-Learn\\test.txt");
        byte[] bytes = new byte[2];
        int len = fis.read(bytes);
        System.out.println(len);
        System.out.println(new String(bytes));

        len = fis.read(bytes);
        System.out.println(len);
        System.out.println(new String(bytes));

        len = fis.read(bytes);
        System.out.println(len);
        System.out.println(new String(bytes));
        fis.close();
    }

    public static void readMulti() throws IOException {
        FileInputStream fis = new FileInputStream("File-Learn\\test.txt");
        byte[] bytes = new byte[2];
        int len = 0;
        while ((len = fis.read(bytes)) != -1) {

            System.out.println(len);
            System.out.println(new String(bytes));
        }

        fis.close();
    }
}

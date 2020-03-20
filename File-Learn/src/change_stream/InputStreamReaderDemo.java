package change_stream;

import java.io.*;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-03-18 19:26
 * @Version: 1.0
 */
public class InputStreamReaderDemo {
    public static void main(String[] args) throws IOException {
//        inputUTF8();
//        input_buffered_UTF8();
        buffered_input_UTF8();

    }

    private static void buffered_input_UTF8() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("File-Learn\\input_stream_reader_utf8.txt")));
        String str;
        while ((str = br.readLine()) != null) {
            System.out.println(str);
        }
        br.close();
    }

    private static void input_buffered_UTF8() throws IOException {
        InputStreamReader isr = new InputStreamReader(
                new BufferedInputStream(new FileInputStream("File-Learn\\input_stream_reader_utf8.txt"))
                );
        int length = 0;
        char[] chars = new char[1024];
        while ((length = isr.read(chars)) != -1 ) {
            System.out.println(new String(chars));
        }
        isr.close();
    }

    private static void inputUTF8() throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream("File-Learn\\input_stream_reader_utf8.txt"));
        int length = 0;
        char[] chars = new char[1024];
        while ((length = isr.read(chars)) != -1 ) {
            System.out.println(new String(chars));
        }
        isr.close();
    }

}

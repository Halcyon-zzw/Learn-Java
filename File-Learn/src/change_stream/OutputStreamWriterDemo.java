package change_stream;

import java.io.*;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-03-18 21:06
 * @Version: 1.0
 */
public class OutputStreamWriterDemo {
    public static void main(String[] args) throws IOException {
//        writerUTF8();
//        outputstream_buffered();
        buffered_ourputstream();
    }

    private static void buffered_ourputstream() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("File-Learn\\input_stream_reader_utf8.txt")));
        bw.write("祝志伟_buffered_outputstream");
        bw.flush();
        bw.close();
    }

    private static void outputstream_buffered() throws IOException {
        OutputStreamWriter osw = new OutputStreamWriter(
                new BufferedOutputStream(new FileOutputStream("File-Learn\\input_stream_reader_utf8.txt"))
        );
        osw.write("祝志伟_outputstream_buffered");
        osw.flush();
        osw.close();
    }

    private static void writerUTF8() throws IOException {
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("File-Learn\\input_stream_reader_utf8.txt"));
        osw.write("祝志伟");
        osw.flush();
        osw.close();
    }
}

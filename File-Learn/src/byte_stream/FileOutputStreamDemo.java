package byte_stream;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-03-10 21:01
 * @Version: 1.0
 */
public class FileOutputStreamDemo {
    public static void main(String[] args) throws IOException {
        output();
    }
    public static void output() throws IOException {
        FileOutputStream fos = new FileOutputStream("File-Learn\\output.txt");
        //当字节为负数时，每两个（和后面的组队）输出一个中文
        byte[] bytes = {-45, -53, 45, -67, 23, -34};
        fos.write(bytes);
        fos.close();
    }
}

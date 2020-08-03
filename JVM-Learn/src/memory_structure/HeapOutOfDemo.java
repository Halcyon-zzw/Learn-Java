package memory_structure;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆内存溢出演示
 * 抛出java.lang.OutOfMemoryError: Java heap space
 *
 * @Author: zhuzw
 * @Date: 2020-07-14 19:37
 * @Version: 1.0
 */
public class HeapOutOfDemo {
    public static void main(String[] args) {
        int i = 0;
        long length = 5;
        try {
            List<String> list = new ArrayList<>();
            String a = "hello";
            while (true) {
                list.add(a);
                a = a + a;
                i ++;
                length = length * 2;
            }
        }catch (Throwable e) {
            e.printStackTrace();
            System.out.println(i);
            System.out.println("字符串长度:" + length);
        }
    }
}

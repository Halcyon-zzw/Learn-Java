package garbage_collection;

import config.ConfigLocal;

import java.util.ArrayList;

/**
 * Java8u20 字符串去重演示
 *
 * TODO 未能演示成功：
 *  - 因为字符串重复，新生代回收时G1时会使s1、s2指向同一个char[]
 *  - 理解为 s1 == s2，但演示未能达到预期效果
 *  => 或者不想理解的一样，只是说底层是char[]，但最终的String对象还是不相等
 *
 * @Author: zhuzw
 * @Date: 2020-07-23 12:21
 * @Version: 1.0
 */
public class StrDeduplicationDemo {
    public static void main(String[] args) {
        String s1 = new String("hello");
        String s2 = new String("hello");
        System.out.println("INFO:sq.equals(s2):" + s1.equals(s2));
        System.out.println("INFO:s1 == s2：" + (s1 == s2));
        ArrayList<byte[]> list = new ArrayList<>();
        list.add(new byte[ConfigLocal._1MB * 7]);
        list.add(new byte[ConfigLocal._512KB]);
        list.add(new byte[ConfigLocal._512KB]);
        System.out.println("INFO:sq.equals(s2):" + s1.equals(s2));
        System.out.println("INFO:s1 == s2：" + (s1 == s2));
    }
}

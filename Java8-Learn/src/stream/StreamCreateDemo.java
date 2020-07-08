package stream;

import java.util.*;
import java.util.stream.Stream;

/**
 * 流得创建
 *
 * @Author: zhuzw
 * @Date: 2020-03-24 19:12
 * @Version: 1.0
 */
public class StreamCreateDemo {
    public static void main(String[] args) {
        streamCreate();
    }

    /**
     * Stream的创建方式
     */
    private static void streamCreate() {
        Stream<String> stream1 = new ArrayList<String>().stream();

        Stream<String> stream2 = new HashSet<String>().stream();

        //Map加<String, String>与不加的区别
        Map<String, String> map = new HashMap<>();
        Stream<String> stream3 = map.keySet().stream();

        Collection<String> mapValues = map.values();
        Stream<String> stream4 = mapValues.stream();

        //键值对entrySet
        Set<Map.Entry<String, String>> entries = map.entrySet();
        Stream<Map.Entry<String, String>> stream5 = entries.stream();

        Stream<String> stream6 = Stream.of("1");
        Stream<String> stream7 = Stream.of("1", "2", "3");

        stream7.filter(i -> i == "2").forEach(i -> System.out.println(i));

    }
}

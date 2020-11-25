package stream;

import java.util.stream.Stream;

/**
 * 流方法 concat 演示，
 * 合并两个流
 *
 * @Author: zhuzw
 * @Date: 2020-03-24 19:37
 * @Version: 1.0
 */
public class ConcatDemo {

    public static void main(String[] args) {

        mergeDiffStream();
    }

    /**
     * 合并两个类型不同得流，TEST
     */
    private static void mergeDiffStream() {
        Stream<String> stream1 = Stream.of("1", "2");
        Stream<Integer> stream2 = Stream.of(3, 4);
        Stream stream3 = Stream.concat(stream1, stream2);
        System.out.println(stream1);
        System.out.println(stream2);
        System.out.println(stream3);
        stream3.forEach(i -> System.out.println(i));
    }
}

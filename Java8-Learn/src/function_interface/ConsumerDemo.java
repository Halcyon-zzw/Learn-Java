package function_interface;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 *
 *
 * Consumer接口的默认方法andThen(Consumer<T> consumer)
 * 作用：将两个Consumer接口组合到一起，在一次对数据进行消费
 *
 * 例：
 *      Consumer<String> consumer1
 *      Consumer<String> consumer2
 *
 *      String s = "hello";                     //谁写前边谁先消费
 *      consumer1.accept(s);           ==>      consumer1.andThen(consumer2).accept(s)
 *      consumer2.accept(s);
 *
 * @Author: zhuzw
 * @Date: 2020-03-23 19:31
 * @Version: 1.0
 */
public class ConsumerDemo {

    public static void main(String[] args) {
        String str = "Hello zhuzw";
        consumerTest(str,
                (s) -> System.out.println("先小写:" + s.toLowerCase()),
                (s) -> System.out.println("再大写:" + s.toUpperCase())
                );
    }

    public static void consumerTest(String s, Consumer<String> consumer1, Consumer<String> consumer2) {
        consumer1.andThen(consumer2).accept(s);
    }
}

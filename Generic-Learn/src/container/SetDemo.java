package container;

import java.util.HashSet;
import java.util.Set;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-11-16 13:51
 * @Version: 1.0
 */
public class SetDemo {
    public static void main(String[] args) {
        testSet();
    }
    public static void testSet() {
        Set<String> set = new HashSet<>();
        set.add("zhuzw");
        set.add("luofeng");
        set.add("chenyang");
        testRemove(set);
        set.forEach(System.out::println);
    }

    private static void testRemove(Set<String> set) {
        Set<String> copySet = new HashSet<>(set);

        copySet.remove("zhuzw");
    }
}

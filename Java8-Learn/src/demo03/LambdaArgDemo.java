package demo03;

import java.util.Arrays;
import java.util.Comparator;

/**
 * lambda做为参数
 *      -主要返回参数
 *
 * @Author: zhuzw
 * @Date: 2020-03-20 17:58
 * @Version: 1.0
 */
public class LambdaArgDemo {
    public static void main(String[] args) {
        String[] strArr = {"22", "555", "333", "1", "444"};

        Arrays.sort(strArr, getComparatorDsc());
        System.out.println(Arrays.toString(strArr));
    }
    public static Comparator<String> getComparatorDsc() {
//        return (o1, o2) -> o1.length() - o2.length();
        //默认升序
        return Comparator.comparing(String::length).thenComparing(s -> s.charAt(0)).reversed();
    }


}

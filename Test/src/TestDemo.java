import java.util.ArrayList;
import java.util.Arrays;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-04-02 9:20
 * @Version: 1.0
 */
public class TestDemo {
    public static void main(String[] args) {
        testArrayAsList();
    }

    private static void testArrayAsList() {
        int[] intArr = new int[]{1, 2, 3};
        Integer[] integerArr = new Integer[]{1, 2, 3};

        System.out.println("int值：");
        new ArrayList<>(Arrays.asList(intArr)).forEach(intValue -> System.out.println(intValue));
        System.out.println("Integer值：");
        Arrays.asList(integerArr).forEach(integerValue -> System.out.println(integerValue));
    }
}

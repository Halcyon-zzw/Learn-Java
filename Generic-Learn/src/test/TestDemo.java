package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-04-02 9:20
 * @Version: 1.0
 */
public class TestDemo {
    public static void main(String[] args) {
        testAssignment();
//        testArrayAsList();
    }

    private static void testArrayAsList() {
        int[] intArr = new int[]{1, 2, 3};
        Integer[] integerArr = new Integer[]{1, 2, 3};

        System.out.println("int值：");
        new ArrayList<>(Arrays.asList(intArr)).forEach(intValue -> System.out.println(intValue));
        System.out.println("Integer值：");
        Arrays.asList(integerArr).forEach(integerValue -> System.out.println(integerValue));
    }

    public static void testAssignment() {
        P p = new P();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    p.setMap();
                }

            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(p.getMap());
                }
            }
        }.start();
    }


}
class P {
    Map map = new HashMap();
    public void setMap() {

        map.put(1, 1);
        map.put(2, 2);
    }

    public Map getMap() {
        return map;
    }
}

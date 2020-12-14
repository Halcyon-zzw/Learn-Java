package util;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-12-13 18:05
 * @Version: 1.0
 */
public class ThreadUtils {

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

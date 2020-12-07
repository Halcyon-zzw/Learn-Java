package demo01;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-03-20 16:45
 * @Version: 1.0
 */
public class DemoLogger {
    /**
     * 存在性能浪费的问题：
     *      当调用log的等级不是1时，之前拼接好的字符串就白拼接了，存在浪费
     * @param level
     * @param message
     */
    public static void log(int level, String message) {
        if (level == 1) {
            System.out.println(message);
        }
    }

    public static void main(String[] args) {
        String msg1 = "Hello";
        String msg2 = "word";
        String msg3 = "!";
        log(2, msg1 + " " + msg2 + msg3);
    }
}

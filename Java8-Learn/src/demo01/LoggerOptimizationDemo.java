package demo01;

/**
 *
 * 对demo01中性能浪费的情况优化
 *
 * @Author: zhuzw
 * @Date: 2020-03-20 17:05
 * @Version: 1.0
 */
public class LoggerOptimizationDemo {
    /**
     * 只有满足条件才执行字符串拼接，
     *  -解决性能浪费问题
     * @param level
     * @param messageBuilder
     */
    public static void log(int level, MessageBuilder messageBuilder) {
        if (level == 1) {
            System.out.println(messageBuilder.build());
        }
    }

    public static void main(String[] args) {
        String msg1 = "Hello";
        String msg2 = "word";
        String msg3 = "!";
        log(1, () -> msg1 + " " + msg2 + msg3);
    }
}

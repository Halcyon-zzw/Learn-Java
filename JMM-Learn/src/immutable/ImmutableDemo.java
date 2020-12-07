package immutable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-12-03 12:37
 * @Version: 1.0
 */
public class ImmutableDemo {
    public static void main(String[] args) {

        problemDemo();


        solveDemo();
    }

    private static void solveDemo() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                TemporalAccessor parse = dtf.parse("1996-02-10");
                System.out.println(parse);
            }).start();
        }
    }

    private static void problemDemo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    Date parse = sdf.parse("1996-02-10");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}

package pool;

import lombok.extern.slf4j.Slf4j;
import util.ThreadUtils;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-12-13 18:03
 * @Version: 1.0
 */
@Slf4j
public class ScheduledExecutorServiceDemo {
    public static void main(String[] args) {
        //方法api演示
//        methodDemo();

        //定时执行任务
        scheduleDemo();
    }

    /**
     * 没周四18:00:00定时执行任务
     */
    private static void scheduleDemo() {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        LocalDateTime now = LocalDateTime.now();
        log.info("{}", now);
        LocalDateTime thursdayTime = now.withHour(18).withMinute(0).withSecond(0).withNano(0).with(DayOfWeek.THURSDAY);
        if (now.compareTo(thursdayTime) > 1) {
            //加一周
            thursdayTime = thursdayTime.plusWeeks(1);
        }
        //当前时间和周四的时间差   一周的间隔时间
        long initailDelay = Duration.between(thursdayTime, now).toMillis();
        long period = 24 * 60 * 60 * 1000 * 7;
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            log.info("每周四执行...");
        }, initailDelay, period, TimeUnit.MILLISECONDS);
    }

    private static void methodDemo() {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        log.info("start...");

        //上一次任务开始后，最多等待period时间
        long period = 1;
        pool.scheduleAtFixedRate(() -> {
            log.info("runing...");
            ThreadUtils.sleep(2000);
        }, 1, period, TimeUnit.SECONDS);

        //上一次任务结束后，再等待delay时间后执行
        long delay = 1;
        pool.scheduleWithFixedDelay(() -> {
            log.info("runing...");
            ThreadUtils.sleep(2000);
        }, 1, delay, TimeUnit.SECONDS);
    }
}

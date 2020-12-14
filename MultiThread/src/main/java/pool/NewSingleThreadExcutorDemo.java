package pool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单线程池 与 正常一个线程 的区别
 *
 * @Author: zhuzw
 * @Date: 2020-12-10 12:26
 * @Version: 1.0
 */
@Slf4j
public class NewSingleThreadExcutorDemo {
    public static void main(String[] args) {
        demo1();
    }

    private static void demo1() {
        throwByGeneral();
        throwByPool();
    }

    private static void throwByPool() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> log.info("1"));
        executorService.execute(() -> log.info("{}", 1 / 0));
        log.info("线程池继续运行...");
        executorService.execute(() -> log.info("3"));
    }

    private static void throwByGeneral() {
        new Thread(() -> {
            log.info("11");
            log.info("{}", 1 / 0);
            log.info("常规线程无法运行...");
            log.info("33");
        }).start();
    }
}

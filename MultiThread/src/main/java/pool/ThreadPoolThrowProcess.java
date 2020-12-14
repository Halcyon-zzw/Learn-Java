package pool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 线程池异常处理
 *
 * @Author: zhuzw
 * @Date: 2020-12-13 18:42
 * @Version: 1.0
 */
@Slf4j
public class ThreadPoolThrowProcess {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        //1、使用try-catcher处理
        executorService.submit(() -> {
            log.info("runing...");
            try {
                int i = 1 / 0;
            } catch (Exception e) {
                log.error("{}", e);
            }
        });

        //2、使用Future接收， 当线程异常时，Future接收到的信息会是异常信息
        Future<Boolean> future = executorService.submit(() -> {
            log.info("running");
            int i = 1 / 0;
            return true;
        });
        future.get();
//        log.info("{}", future.get());
        log.info("end");
    }
}

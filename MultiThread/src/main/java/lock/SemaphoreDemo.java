package lock;

import lombok.extern.slf4j.Slf4j;
import util.ThreadUtils;

import java.util.concurrent.Semaphore;

/**
 * 用于限制同时访问共享资源的线程上限
 *
 * @Author: zhuzw
 * @Date: 2020-12-17 19:49
 * @Version: 1.0
 */
@Slf4j
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    log.error("{}", e);
                }
                log.info("running...");
                try {
                    ThreadUtils.sleep(2000);
                } finally {
                    log.info("end...");
                    semaphore.release();
                }
            }).start();
        }
    }
}

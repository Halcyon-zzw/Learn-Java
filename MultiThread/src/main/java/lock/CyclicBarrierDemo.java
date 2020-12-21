package lock;

import lombok.extern.slf4j.Slf4j;
import util.ThreadUtils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-12-19 17:48
 * @Version: 1.0
 */
@Slf4j
public class CyclicBarrierDemo {
    public static void main(String[] args) {
//        demo();
        demoNote();
    }



    /**
     * 对一组线程执行三次
     */
    private static void demo() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
            //用于结束后执行
            log.info("t1 t2 end...");
        });

        for (int i = 0; i < 3; i++) {
            executorService.submit(() -> {
               log.info("t1 start...");
                ThreadUtils.sleep(1000);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
            executorService.submit(() -> {
                log.info("t2 start...");
                ThreadUtils.sleep(3000);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    /**
     * 注意事项演示，线程池线程数需要和CyclicBarrier个数相同，否则将无法得到预期效果
     * 如下列t1执行1s，t2执行3s，所以可能执行完的是可能是两次t1
     */
    private static void demoNote() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
            //用于结束后执行
            log.info("end...");
        });

        for (int i = 0; i < 3; i++) {
            executorService.submit(() -> {
                log.info("t1 start...");
                ThreadUtils.sleep(1000);
                try {
                    cyclicBarrier.await();
                    log.info("t1 end...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
            executorService.submit(() -> {
                log.info("t2 start...");
                ThreadUtils.sleep(3000);
                try {
                    cyclicBarrier.await();
                    log.info("t2 end...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}

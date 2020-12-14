package pool;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 线程饥饿问题
 *      - 固定大小线程池会有饥饿现象
 *      - 业务描述：
 *              工人A处理点餐 -> 将菜单交给工人B做菜 -> 等待工人做完菜
 *      - 当只有两个线程（工人），而同时来两个客人时，将无人做菜，将死锁（饥饿）
 *
 *      解决：
 *          - 不同处理任务使用不同线程池
 *          - 同一个线程池中的线程之间不能有依赖关系
 * @Author: zhuzw
 * @Date: 2020-12-10 19:34
 * @Version: 1.0
 */
@Slf4j
public class HungerDemo {
    static final List<String> MENU = Arrays.asList("地三鲜", "宫保鸡丁");
    static String cooking() {
        log.info("做菜...");
        return MENU.get(new Random().nextInt(MENU.size()));
    }
    public static void main(String[] args) {
        problem();
        solve();
    }

    private static void solve() {
        ExecutorService waitPool = Executors.newFixedThreadPool(1);
        ExecutorService cookPool = Executors.newFixedThreadPool(1);
        waitPool.submit(() -> {
            log.info("处理点餐...");
            Future<String> cookResult = cookPool.submit(() -> cooking());
            try {
                log.info("上菜：{}", cookResult.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        waitPool.submit(() -> {
            log.info("处理点餐...");
            Future<String> cookResult = cookPool.submit(() -> cooking());
            try {
                log.info("上菜：{}", cookResult.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    private static void problem() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(() -> {
            log.info("处理点餐...");
            Future<String> cookResult = executorService.submit(() -> cooking());
            try {
                log.info("上菜：{}", cookResult.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        executorService.submit(() -> {
            log.info("处理点餐...");
            Future<String> cookResult = executorService.submit(() -> cooking());
            try {
                log.info("上菜：{}", cookResult.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}

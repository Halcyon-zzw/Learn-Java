package lock;

import util.ThreadUtils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch演示游戏玩家加载
 *
 * @Author: zhuzw
 * @Date: 2020-12-18 16:47
 * @Version: 1.0
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        int threadSize = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(threadSize);
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        Random random = new Random();
        String[] plays = new String[threadSize];
        for (int i = 0; i < threadSize; i++) {
            int k = i;
            executorService.submit(() -> {
                for (int j = 0; j <= 100; j++) {
                    //睡眠0 - 200 ms
                    ThreadUtils.sleep(random.nextInt(100));
                    plays[k] = j + "";
                    System.out.print("\r" + Arrays.toString(plays));
                }
                countDownLatch.countDown();
            });

        }
        countDownLatch.await();
        System.out.println("\n游戏开始...");
        System.out.println("欢迎来到王者荣耀...");
        System.out.println("敌军还有5s到达战场...");
        executorService.shutdown();
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * 多线程处理集合
 *
 * @Author: zhuzw
 * @Date: 2020-07-09 17:27
 * @Version: 1.0
 */
public class MulThreadPoolListDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("开始执行多线程...");
        long startTime = System.currentTimeMillis();

        List<String> list = new CopyOnWriteArrayList<>();//存放返回结果
        CountDownLatch countDownLatch = new CountDownLatch(10);
        ExecutorService executorService =  Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            String name = "Thread-" + i;
            Runnable runnable =  () -> {
                System.out.println(Thread.currentThread().getName() + " is running[" + name + "]...");
                try {
                    Thread.sleep(1000);
                    list.add(name);
//                    list.add(UUID.randomUUID().toString());
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };

            executorService.execute(runnable);
        }
        countDownLatch.await();
        System.out.println("submit总共cost 时间：" + (System.currentTimeMillis()-startTime) + "毫秒");
        executorService.shutdown();
        System.out.println("数组大小:" + list.size());
    }
}

class MyTask implements Runnable {
    private String name;
    private CountDownLatch countDownLatch;
    MyTask(CountDownLatch countDownLatch, String name) {
        this.countDownLatch = countDownLatch;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running[" + name + "]...");
        try {
            Thread.sleep(3000);
            countDownLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "MyTask-" + this.name;
    }
}
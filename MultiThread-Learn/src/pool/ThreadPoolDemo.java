package pool;

import java.util.concurrent.*;

/**
 * 线程池演示类
 *
 * 线程池使用步骤：
 *      1、使用Executors工厂类的newFixedThreadPool获取指定线程数量的线程池（返回实现了ExecutorService接口的对象）
 *          - 或newSingleThreadPool、newCacheThreadPool、newScheduledThreadPool,
 *          - 不建议使用该类方法，
 *              - 前两个允许的请求队列为Integer.MAX_VALUE，可能堆积大量的请求，从而导致OOM
 *              - 后两个允许创建的线程数量时Integer.MAX_VALUE，可能创建大量线程，从而导致OOM
 *      2、创建线程类，设置线程任务
 *      3、调用ExecutorService中的sumbit(Thread thread)方法，开启线程
 *      4、不建议使用：调用ExecutorService的shutdown()方法消耗线程池
 * @Author: zhuzw
 * @Date: 2020-03-06 9:25
 * @Version: 1.0
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        /* 不建议使用
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new RunnableImpl());
        executorService.submit(new RunnableImpl());
        executorService.submit(new RunnableImpl());
        executorService.submit(new RunnableImpl());
        */

        ExecutorService executorService = newThreadPoolExtractor();

        for (int i = 0; i < 10; i++) {
            executorService.execute(new MyTask(String.valueOf(i)));
        }


    }

    static class MyTask implements Runnable {
        private String name;
        MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " is running[" + name + "]...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "MyTask-" + this.name;
        }
    }

    private static ExecutorService newThreadPoolExtractor() {
        int corePoolSize = 1;
        int maxinumPoolSize = 4;
        long keppAliceTime = 10L;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> blockingDeque = new ArrayBlockingQueue<>(7);

        ThreadFactory threadFactory = ((r) -> {
            System.out.println("create new thread");
            return new Thread(r);
        });

        RejectedExecutionHandler rejectedExecutionHandler = ((r, executor) -> System.out.println(r.toString() + " rejected!"));

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
                maxinumPoolSize,
                keppAliceTime,
                unit,
                blockingDeque,
                threadFactory,
                rejectedExecutionHandler);
        threadPoolExecutor.prestartCoreThread();
        return threadPoolExecutor;
    }
}

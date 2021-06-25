package pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * 线程池演示类
 *
 * 线程池使用步骤：
 *      1、使用Executors工厂类的newFixedThreadPool获取固定线程数量的线程池（返回实现了ExecutorService接口的对象）
 *          - 核心线程数=最大线程数， 队列为无界队列
 *          - 或newSingleThreadPool、newCacheThreadPool、newScheduledThreadPool,
 *          - 不建议使用
 *          此类方法，
 *              - 前两个允许的请求队列为Integer.MAX_VALUE，可能堆积大量的请求，从而导致OOM
 *              - 后两个允许创建的线程数量为Integer.MAX_VALUE，可能创建大量线程，从而导致OOM
 *      2、创建线程类，设置线程任务
 *      3、调用ExecutorService中的sumbit(Thread thread)方法，开启线程
 *      4、不建议使用：调用ExecutorService的shutdown()方法消耗线程池
 *
 *      newCachedThreadPool(0, Integer.MAX_VALUE, 60L, TimeUnit.SENCONDS, new SynchronousQueue<Runnable>()) 带缓存线程池
 *          - 核心线程数0，全部都是救急线程（60s后被回收）
 *          - 救急线程可无限创建
 *          - SynchronousQueue 没有容量，没有线程来去放不进去
 *
 *      newSingleThreadPool(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>) 单线程线程池
 *          - 特点：线程数固定为1；任务执行完毕后唯一的线程不会被释放
 *          - LinkedBlockingQueue 阻塞无界限队列
 *          场景： 希望多个任务排队执行
 *          和自身创建一个线程的区别：
 *              - 自己创建的线程如果任务执行失败将终止；而线程池会创建新的线程保证池的正常工作
 *              - 返回的FinalizableDelegatedExecutorService对象，只对外暴露ExecutorService有的方法，不能调用ThreadPoolExecutor中特有的方法；如设置线程数方法
 *              - Executors.newFixedThreadPool(1)实现单线程池；
 *                  - 对外暴露的是ThreadPoolExecutor对象，可以强转后调用setCorePoolSize修改核心线程数
 *
 *      newScheduledThreadPool(corePoolSize);
 * @Author: zhuzw
 * @Date: 2020-03-06 9:25
 * @Version: 1.0
 */
public class ThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException {
        /* 不建议使用
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new RunnableImpl());
        executorService.submit(new RunnableImpl());
        executorService.submit(new RunnableImpl());
        executorService.submit(new RunnableImpl());
        */
        ExecutorService executorService = newThreadPoolExtractor();
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String name = "Thread-" + i;
            executorService.execute(new MyTask(name));
        }
        System.out.println("数组：" + result);

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
        long keepAliveTime = 10L;
        int capacity = 7;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> blockingDeque = new ArrayBlockingQueue<>(capacity);

//        ThreadFactory threadFactory1 = new ThreadFactory() {
//            private AtomicInteger i = new AtomicInteger(1);
//            @Override
//            public Thread newThread(Runnable r) {
//                return new Thread(r, "mypool_t" + i);
//            }
//        };
        AtomicInteger i = new AtomicInteger(1);
        ThreadFactory threadFactory = ((r) ->        {
            System.out.println("create new thread");
            return new Thread(r, "mypool_t" + i.getAndIncrement());
        });

        RejectedExecutionHandler rejectedExecutionHandler = ((r, executor) -> System.out.println(r.toString() + " rejected!"));

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
                maxinumPoolSize,
                keepAliveTime,
                unit,
                blockingDeque,
                threadFactory,
                rejectedExecutionHandler);
        threadPoolExecutor.prestartCoreThread();
        return threadPoolExecutor;
    }
}

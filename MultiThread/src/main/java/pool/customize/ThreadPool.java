package pool.customize;


import javafx.concurrent.Worker;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池类
 *
 * @Author: zhuzw
 * @Date: 2020-12-07 18:31
 * @Version: 1.0
 */
@Slf4j
public class ThreadPool {
    //任务队列
    private BlockingQueue<Runnable> queue;

    //线程集合
    private Set<Worker> workers = new HashSet<>();

    //核心线程数
    private int coreSize;

    private long timeout;

    private TimeUnit timeUnit;

    private int capacity;
    /**
     * 超时策略
     * 1、死等
     * 2、超时等待
     * 3、让调用者放弃任务执行
     * 4、让调用者抛出异常
     * 5、让调用者自己执行任务
     */
    private RejectPolicy<Runnable> rejectPolicy;

    public ThreadPool(int capacity, int coreSize, long timeout, TimeUnit timeUnit,
                      RejectPolicy<Runnable> rejectPolicy) {
        this.capacity = capacity;
        this.queue = new BlockingQueue<>(capacity);
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.rejectPolicy = rejectPolicy;
    }

    public void execute(Runnable task) {
        //保护works的线程安全
        synchronized (workers) {
            if (workers.size() < coreSize) {
                //线程数少于核心数，创建线程，否则进入任务队列等待
                Worker worker = new Worker(task);
                workers.add(worker);
                worker.start();
                log.info("创建线程：{}", worker);
            }else {
                queue.tryPush(rejectPolicy, task);
                //使用超时策略


                /**
                 * 超时策略
                 * 1、死等
                 * 2、超时等待
                 * 3、让调用者放弃任务执行
                 * 4、让调用者抛出异常
                 * 5、让调用者自己执行任务
                 */
            }
        }
    }




    class Worker extends Thread {
        Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            //执行任务
            //1) 当task不为空，执行任务
            //2) 当task执行完毕，从任务队列中获取任务并执行
//            while (task != null || (task = queue.pool()) != null) {
            while (task != null || (task = queue.pool(timeout, timeUnit)) != null) {
                try {
                    log.info("执行任务：{},{}", this, task);
                    task.run();
                }catch (Exception e) {

                }finally {
                    task = null;
                }
            }
            synchronized (workers) {
                log.info("释放线程：{}", this);
                workers.remove(this);
            }
        }
    }
}

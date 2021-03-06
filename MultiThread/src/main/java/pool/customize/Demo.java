package pool.customize;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-12-07 20:00
 * @Version: 1.0
 */
@Slf4j
public class Demo {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(10, 2, 1000, TimeUnit.MILLISECONDS,
                (queue, task) -> {
                    //1、死等
//                    queue.push(task);
                    //2、超时等待
//                    queue.offer(task, 100, TimeUnit.MILLISECONDS);
                    //3、放弃任务执行
//                    log.info("放弃任务:{}", task);
                    //4、抛出异常
//                    throw new RuntimeException("任务阻塞");
                    //5、主线程自己执行
                    task.run();
        });
        for (int i = 0; i < 15; i++) {
            int threadId = i;
            threadPool.execute(() -> {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("线程:{}", threadId);
            });
        }
    }
}

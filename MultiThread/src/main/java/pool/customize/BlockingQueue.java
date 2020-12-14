package pool.customize;

import com.sun.jmx.remote.internal.ArrayQueue;
import javafx.scene.layout.TilePane;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-12-07 17:08
 * @Version: 1.0
 */
@Slf4j
public class BlockingQueue<T> {
    private Deque<T> queue;

    private int capacity;

    //用于锁住队列头尾
    private ReentrantLock lock = new ReentrantLock();
    //队列满时，生成者条件变量
    private Condition fullWaitSet = lock.newCondition();
    //队列空时，消费者条件变量
    private Condition emptyWaitSet = lock.newCondition();

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
        queue = new ArrayDeque<>(capacity);
    }

    /**
     * 获取任务
     * @return
     */
    public T pool(long timeout, TimeUnit timeUnit) {
        long nanoTime = timeUnit.toNanos(timeout);
        lock.lock();
        try {
            while (queue.isEmpty()) {
                try {
                    if (nanoTime <= 0) {
                        return null;
                    }
                    //最长等待时间 - 已经等待的时间 （还需要等待的时间）
                    nanoTime = emptyWaitSet.awaitNanos(nanoTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;
        }finally {
            lock.unlock();
        }
    }

    /**
     * 获取任务
     * @return
     */
    public T pool() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                try {

                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;
        }finally {
            lock.unlock();
        }
    }

    /**
     * 推任务
     * @param task
     */
    public void push(T task) {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                try {
                    log.info("等待加入任务队列:{}", task);
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(task);
            emptyWaitSet.signal();
        }finally {
            lock.unlock();
        }
    }

    public boolean offer(T task, long timeout, TimeUnit timeUnit) {
        lock.lock();
        try {
            long nanoTime = timeUnit.toNanos(timeout);
            while (queue.size() == capacity) {
                try {
                    log.info("等待加入任务队列:{}", task);
                    if (nanoTime <= 0) {
                        return false;
                    }
                    nanoTime = fullWaitSet.awaitNanos(nanoTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(task);
            emptyWaitSet.signal();
            return true;
        }finally {
            lock.unlock();
        }
    }

    public void tryPush(RejectPolicy<T> rejectPolicy, T task) {
        lock.lock();

        try {
            if (queue.size() == capacity) {
                //队列已满
                rejectPolicy.rejuct(this, task);
            }else {
                log.info("进入任务队列等待：{}", task);
                queue.addLast(task);
                emptyWaitSet.signal();
            }
        } finally {
            lock.unlock();
        }

    }

    /**
     * 为什么需要加锁获取
     * @return
     */
    public int size() {
        lock.lock();

        try {
            return queue.size();
        }finally {

        }
    }
}

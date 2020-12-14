package pool.customize;

/**
 * 拒绝策略
 *
 * @Author: zhuzw
 * @Date: 2020-12-08 12:11
 * @Version: 1.0
 */
@FunctionalInterface
public interface RejectPolicy<T> {
    void rejuct(BlockingQueue<T> queue, T task);
}

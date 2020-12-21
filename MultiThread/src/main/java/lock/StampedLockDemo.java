package lock;

import lombok.extern.slf4j.Slf4j;
import util.ThreadUtils;

import java.util.concurrent.locks.StampedLock;

/**
 * stamped:盖章
 *
 * @Author: zhuzw
 * @Date: 2020-12-17 19:17
 * @Version: 1.0
 */
public class StampedLockDemo {
}
@Slf4j
class DataContainerStamped {
    private Object data;

    private final StampedLock lock = new StampedLock();

    public DataContainerStamped(Object data) {
        this.data = data;
    }

    public Object read() {
        long stamp = lock.tryOptimisticRead();
        log.info("optimistic read locking... {}", stamp);
        ThreadUtils.sleep(2000);
        if (lock.validate(stamp)) {
            //TODO 如何保证在验证通过不被修改完值
            log.info("验证通过");
            return data;
        }
        log.info("获取读锁...");
        try {
            stamp = lock.readLock();
            log.info("获取到读锁，stamp:{}", stamp);
            ThreadUtils.sleep(2000);
            log.info("读取结束");
            return data;
        }finally {
            log.info("释放读锁");
            lock.unlock(stamp);
        }
    }

    public void write(Object data) {
        long stamp = lock.writeLock();
        log.info("获取写锁, stamp:{}", stamp);
        try {
            ThreadUtils.sleep(2000);
            this.data = data;
        } finally {
            lock.unlock(stamp);
            log.info("释放写锁");
        }
    }
}

























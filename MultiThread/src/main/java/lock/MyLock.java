package lock;

import lombok.extern.slf4j.Slf4j;
import util.ThreadUtils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自定义锁 - 不可重入锁
 *
 * @Author: zhuzw
 * @Date: 2020-12-15 14:06
 * @Version: 1.0
 */
public class MyLock implements Lock {

    private MySynchronizer sync = new MySynchronizer();

    //同步器类 - 独占锁
    class MySynchronizer extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                //设置 owner 为当前线程
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            //设置线程为null必须在setState之前，因为state被volatite修饰，可保证写屏障前的修改操作对其他线程可见
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        /**
         * 是否持有锁
         * @return
         */
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        public Condition newCondition() {
            return new ConditionObject();
        }

    }

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}

//测试自定义锁
@Slf4j
class Demo {
//    ReentrantLock
    public static void main(String[] args) {
        MyLock lock = new MyLock();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                log.info("尝试获取锁");
                lock.lock();

                try {
                    log.info("获取锁成功");
                    log.info("running...");
                    ThreadUtils.sleep(2000);
                } finally {
                    lock.unlock();
                }
            }).start();
        }
    }
}

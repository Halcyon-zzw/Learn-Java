package lock;

import lombok.extern.slf4j.Slf4j;
import util.ThreadUtils;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-12-16 14:32
 * @Version: 1.0
 */
@Slf4j
public class ReentrantReadWriteLockDemo {
    public static void main(String[] args) {
        ObjDataContainer objDataContainer = new ObjDataContainer();

        //测试并发读
        for (int i = 0; i < 3; i++) {
            new Thread(() -> objDataContainer.read()).start();
        }

        //测试并发写
        for (int i = 0; i < 2; i++) {
            new Thread(() -> objDataContainer.write(null)).start();
        }

        //测试并发读
        for (int i = 0; i < 3; i++) {
            new Thread(() -> objDataContainer.read()).start();
        }

    }
}


@Slf4j
class ObjDataContainer {
    private Object data;

    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    private ReentrantReadWriteLock.ReadLock rLock = rwLock.readLock();
    private ReentrantReadWriteLock.WriteLock wLock = rwLock.writeLock();

    public Object read() {
        rLock.lock();
        log.info("获取读锁...");
        try {
            log.info("读取数据");
            ThreadUtils.sleep(2000);
            return data;
        }finally {
            rLock.unlock();
            log.info("释放读锁...");
        }
    }

    public void write(Object object) {
        wLock.lock();
        log.info("获取写锁...");
        try {
            log.info("写入");
            ThreadUtils.sleep(2000);
            data = object;
        }finally {
            wLock.unlock();
            log.info("释放写锁...");
        }
    }
}



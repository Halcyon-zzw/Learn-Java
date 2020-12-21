package lock;

import fly_weight.FlyWeightWithConnec;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.util.concurrent.Semaphore;

/**
 * 通过Semaphore优化数据库连接池
 *
 * @Author: zhuzw
 * @Date: 2020-12-17 20:02
 * @Version: 1.0
 */
@Slf4j
public class PoolBySemaphore extends FlyWeightWithConnec {

    private final Semaphore semaphore;
    public PoolBySemaphore(int poolSize) {
        super(poolSize);
        //连接数大小与资源数一致
        semaphore = new Semaphore(poolSize);
    }

    @Override
    public Connection getConnection() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
        }
        for (int i = 0; i < poolSize; i++) {
            if (states.get(i) == 0) {
                //连接空闲
                if (states.compareAndSet(i, 0, 1)) {
                    log.info("获取到连接：{}", connections[i]);
                    return connections[i];
                }
            }
        }
        return null;
    }

    @Override
    public void close(Connection connection) {
        for (int i = 0; i < poolSize; i++) {
            if (connections[i] == connection) {
                states.set(i, 0);
                log.info("释放连接");
                semaphore.release();
                break;
            }
        }
    }
}

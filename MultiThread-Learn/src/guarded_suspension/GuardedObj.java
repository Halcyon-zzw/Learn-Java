package guarded_suspension;

import java.util.function.Supplier;

/**
 *
 *
 * @Author: zhuzw
 * @Date: 2020-10-28 19:53
 * @Version: 1.0
 */
public class GuardedObj {
    private Object response;
    public Object get() {
        synchronized (this) {
            while (response == null) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }

    public Object get(long timeout) {
        synchronized (this) {
            long beginTime = System.currentTimeMillis();
            //已经等待的事件
            long waitTime = 0;
            while (response == null) {
                //还需等待的事件
                long waitedTime = timeout - waitTime;
                if (waitedTime <= 0) {
                    break;
                }
                try {
                    this.wait(waitedTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                waitTime = System.currentTimeMillis() - beginTime;
            }
        }
        return response;
    }

    public void accept(Object obj) {
        synchronized (this) {
            response = obj;
            this.notifyAll();
        }
    }
}

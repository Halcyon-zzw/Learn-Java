package pool;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-03-06 9:37
 * @Version: 1.0
 */
public class RunnableImpl implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "执行...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

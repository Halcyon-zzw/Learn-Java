package safe;

/**
 * 线程安全问题演示
 *
 * @Author: zhuzw
 * @Date: 2020-03-03 19:44
 * @Version: 1.0
 */
public class ThreadSafeDemo {
    public static void main(String[] args) {
        SaleThread saleThread = new SaleThread();
        Thread saleThread1 = new Thread(saleThread);
        Thread saleThread2 = new Thread(saleThread);
        Thread saleThread3 = new Thread(saleThread);

        saleThread1.start();
        saleThread2.start();
        saleThread3.start();
    }
}

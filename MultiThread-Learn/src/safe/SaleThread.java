package safe;

/**
 * 卖票线程安全问题
 *
 * @Author: zhuzw
 * @Date: 2020-03-03 19:44
 * @Version: 1.0
 */
public class SaleThread implements Runnable{
    private int num = 1;
    @Override
    public void run() {
        while (num > 0) {
            System.out.println(Thread.currentThread().getName() + "准备卖票...");
            if (num > 0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "卖票，卖出后" + (--num));
            }

        }
        System.out.println(Thread.currentThread().getName() + "没有余票!");
    }
}

package communication.wait_and_notify;

/**
 * 等待唤醒案例：线程之间的通信   （有效利用资源）
 *      创建顾客（消费者）：告诉老板要碗面，调用wait()放弃cpu执行，进入WAITING状态
 *      创建老板（生产者）：花10s做面，做好后，调用notify()，唤醒顾客吃面
 *
 * 注意：
 *      顾客和老板线程需要用同步代码块包裹起来，保证等待和唤醒只能有一个执行
 *      只有锁对象才能调用wait()和notify()
 *
 * Object类中的方法
 *      void wait()
 *          使线程进入等待状态，直到在其他线程调用此对象的notify()或notifyAll()
 *      void notify()
 *          唤醒在此对象监视器上等待的单个线程，
 *              - 如果被唤醒线程能获取到锁，则从WAITING -> RUNNABLE
 *              - 否则，从WAITING -> BLOCKERD，重新抢夺CPU执行权
 *
 * @Author: zhuzw
 * @Date: 2020-03-05 18:42
 * @Version: 1.0
 */
public class WaitAndNotify {

    public static void main(String[] args) {
        Object object = new Object();
        //顾客线程
        new Thread(() -> {
            synchronized (object) {
                System.out.println("告诉老板要碗面，进入WAITING状态。");
                try {
                    //等待老板做面
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("开始吃面。");
            }
        }).start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //老板线程
        new Thread(() -> {
            synchronized (object) {

                System.out.println("收到点餐请求,开始做面。");
                System.out.println("等待5s...");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("面已做好。");
                //唤醒顾客吃面
                object.notify();
            }
        }).start();
    }
}

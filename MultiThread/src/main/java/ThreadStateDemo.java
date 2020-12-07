import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * java六种状态演示
 *
 * @Author: zhuzw
 * @Date: 2020-10-13 19:58
 * @Version: 1.0
 */
public class ThreadStateDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
        });;

        Thread t2 = new Thread(() -> {
            while (true) {

            }
        });
        t2.start();

        Thread t3 = new Thread(() -> {});
        t3.start();

        Thread t4 = new Thread(() -> {
            synchronized (ThreadStateDemo.class) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t4.start();

        Thread t5 = new Thread(() -> {
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t5.start();

        Thread t6 = new Thread(() -> {
            synchronized (ThreadStateDemo.class) {
                System.out.println("t6");
            }
        });
        t6.start();

        System.out.println("thread1状态：" + t1.getState());
        System.out.println("thread2状态：" + t2.getState());
        System.out.println("thread3状态：" + t3.getState());
        System.out.println("thread4状态：" + t4.getState());
        System.out.println("thread5状态：" + t5.getState());
        System.out.println("thread6状态：" + t6.getState());
    }
}

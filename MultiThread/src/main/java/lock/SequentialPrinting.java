package lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * 保证first先打印，sencend再打印
 *
 * @Author: zhuzw
 * @Date: 2021-05-10 18:33
 * @Version: 1.0
 */
@Slf4j
public class SequentialPrinting {
    static boolean firstPrinted = false;

    /**
     * wait-notify实现
     */
    public static void implByWaitNotify() {
        Object obj = new Object();

        new Thread(() -> {
            synchronized (obj) {
                if (!firstPrinted) {
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            log.info("sencend");
        }, "t1").start();

        new Thread(() -> {
            log.info("first");
            synchronized (obj) {
                firstPrinted = true;
                obj.notify();
            }
        }, "t2").start();
    }

    /**
     * park、unpark实现
     */
    public static void implByParkUnpark() {
        Thread t1 = new Thread(() -> {
            LockSupport.park();
            log.info("sencend");
        }, "t1");

        t1.start();

        new Thread(() -> {
            log.info("first");
            LockSupport.unpark(t1);
        }, "t2").start();
    }

    public static void main(String[] args) {
        implByWaitNotify();
//        implByParkUnpark();
    }
}

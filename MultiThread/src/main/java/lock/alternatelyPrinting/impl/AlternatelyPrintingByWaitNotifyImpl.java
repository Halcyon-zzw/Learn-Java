package lock.alternatelyPrinting.impl;

import lock.alternatelyPrinting.AlternatelyPrinting;
import lombok.extern.slf4j.Slf4j;

/**
 * wait-notify实现
 *
 * @Author: zhuzw
 * @Date: 2021-05-10 18:45
 * @Version: 1.0
 */
@Slf4j
public class AlternatelyPrintingByWaitNotifyImpl implements AlternatelyPrinting {
    /**
     * 等待标记，不同的值对应不同的线程打印
     */
    private int flag = 1;
    @Override
    public void apply() {

        applyOpt();
        Object obj = new Object();
        int n = 5;
        new Thread(() -> {
            synchronized (obj) {
                for (int i = 0; i < n; i++) {
                    while (true) {
                        if (flag == 1) {
                            log.info("a");
                            obj.notifyAll();
                            flag++;
                            break;
                        }else {
                            try {
                                obj.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (obj) {
                for (int i = 0; i < n; i++) {
                    while (true) {
                        if (flag == 2) {
                            log.info("b");
                            obj.notifyAll();
                            flag++;
                            break;
                        }else {
                            try {
                                obj.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

            }
        }, "t2").start();

        new Thread(() -> {
            synchronized (obj) {
                for (int i = 0; i < n; i++) {
                    while (true) {
                        if (flag == 3) {
                            log.info("c");
                            obj.notifyAll();
                            flag = 1;
                            break;
                        }else {
                            try {
                                obj.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }


            }
        }, "t3").start();
    }

    /**
     * 优化
     */
    private void applyOpt() {

    }


    public static void main(String[] args) {
        AlternatelyPrintingByWaitNotifyImpl alternatelyPrintingByWaitNotify = new AlternatelyPrintingByWaitNotifyImpl();
        alternatelyPrintingByWaitNotify.apply();
    }
}

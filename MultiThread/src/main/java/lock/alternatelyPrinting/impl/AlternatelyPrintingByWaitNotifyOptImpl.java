package lock.alternatelyPrinting.impl;

import lock.alternatelyPrinting.AlternatelyPrinting;
import lombok.extern.slf4j.Slf4j;

/**
 * wait-notify实现 - 优化
 *
 * @Author: zhuzw
 * @Date: 2021-05-10 18:45
 * @Version: 1.0
 */
@Slf4j
public class AlternatelyPrintingByWaitNotifyOptImpl implements AlternatelyPrinting {
    /**
     *  str     flag    nextFlag
     *   a       1          2
     *   b       2          3
     *   c       3          1
     */


    /**
     * 等待标记，不同的值对应不同的线程打印
     */
    private int flag = 1;
    private int n = 5;

    Object obj = new Object();
    @Override
    public void apply() {

        new Thread(() -> print("a", 1, 2), "t1").start();
        new Thread(() -> print("b", 2, 3), "t2").start();
        new Thread(() -> print("c", 3, 1), "t3").start();


    }

    private void print(String str, int flag, int nextFlag) {
        synchronized (obj) {
            for (int i = 0; i < n; i++) {
                while (this.flag != flag) {
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info(str);
                obj.notifyAll();
                this.flag = nextFlag;
            }
        }
    }


    public static void main(String[] args) {
        AlternatelyPrintingByWaitNotifyOptImpl alternatelyPrintingByWaitNotify = new AlternatelyPrintingByWaitNotifyOptImpl();
        alternatelyPrintingByWaitNotify.apply();
    }
}

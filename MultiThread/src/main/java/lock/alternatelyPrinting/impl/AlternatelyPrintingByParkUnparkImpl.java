package lock.alternatelyPrinting.impl;

import lock.alternatelyPrinting.AlternatelyPrinting;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * park、unpark实现
 *
 * @Author: zhuzw
 * @Date: 2021-05-10 19:29
 * @Version: 1.0
 */
@Slf4j
public class AlternatelyPrintingByParkUnparkImpl implements AlternatelyPrinting {
    private int n = 5;
    private Thread t1 = null;
    private Thread t2 = null;
    private Thread t3 = null;
    @Override
    public void apply() {

        t1 = new Thread(() -> print("a", t2), "t1");
        t2 = new Thread(() -> print("b", t3), "t2");
        t3 = new Thread(() -> print("c", t1), "t3");

        t1.start();
        t2.start();
        t3.start();
        LockSupport.unpark(t1);

    }

    private void print(String str, Thread nextThread) {
        for (int i = 0; i < n; i++) {
            LockSupport.park();
            log.info(str);
            LockSupport.unpark(nextThread);
        }
    }

    public static void main(String[] args) {
        AlternatelyPrintingByParkUnparkImpl alternatelyPrintingByParkUnpark = new AlternatelyPrintingByParkUnparkImpl();
        alternatelyPrintingByParkUnpark.apply();
    }
}

package lock.alternatelyPrinting.impl;

import lock.alternatelyPrinting.AlternatelyPrinting;
import lombok.extern.slf4j.Slf4j;
import sun.security.acl.AllPermissionsImpl;
import util.ThreadUtils;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock的await()、signalALl()实现
 *
 * @Author: zhuzw
 * @Date: 2021-05-10 19:12
 * @Version: 1.0
 */
@Slf4j
public class AlternatelyPrintingByReentrantLockImpl implements AlternatelyPrinting {
    private ReentrantLock lock = new ReentrantLock();
    private int n = 5;
    @Override
    public void apply() {

        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Condition condition3 = lock.newCondition();

        new Thread(() -> print("a", condition1, condition2), "t1").start();
        new Thread(() -> print("b", condition2, condition3), "t2").start();
        new Thread(() -> print("c", condition3, condition1), "t3").start();

        ThreadUtils.sleep(500);
        lock.lock();

        try {
            condition1.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     *
     * @param str
     * @param curCondition 自身的条件变量
     * @param nextCondition 需要唤醒的条件变量
     */
    public void print(String str, Condition curCondition, Condition nextCondition) {
        for (int i = 0; i < n; i++) {
            lock.lock();
            try {
                curCondition.await();
                log.info(str);
                nextCondition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        AlternatelyPrintingByReentrantLockImpl alternatelyPrintingByReentrantLock = new AlternatelyPrintingByReentrantLockImpl();
        alternatelyPrintingByReentrantLock.apply();
    }
}

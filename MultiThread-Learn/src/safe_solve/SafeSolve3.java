package safe_solve;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程安全问题解决方案3：使用Lock锁
 * java.util.concurrent.locks.Lock接口
 *      提供比使用synchronized更广泛的锁定操作
 *          - lock() 获取锁
 *          - unlock() 释放锁
 *      实现类： java.util.concurrent.locks.ReentrantLock
 *
 * 使用步骤：
 *      1、创建ReentrantLock对象
 *      2、在线程安全问题前lock()
 *      3、在线程安全问题后unlock()
 *
 *
 *
 * @Author: zhuzw
 * @Date: 2020-03-05 18:04
 * @Version: 1.0
 */
public class SafeSolve3 implements Runnable{
    private static int num = 3;
    private Lock lock = new ReentrantLock();
    @Override
    public void run() {
        lock.lock();
        try {
            while (num > 0) {
                System.out.println(Thread.currentThread().getName() + "准备卖票...");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "卖票，卖出后" + (--num));
            }
            System.out.println(Thread.currentThread().getName() + "没有余票!");

        }finally {
            //无论如何都释放锁，防止死锁
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        SafeSolve3 safeSolve3 = new SafeSolve3();
        Thread thread1 = new Thread(safeSolve3);
        Thread thread2 = new Thread(safeSolve3);
        Thread thread3 = new Thread(safeSolve3);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

package safe_solve;

import safe.SaleThread;

/**
 * 线程安全问题解决方案1：同步代码块，锁对象，也叫对象锁
 *
 * 格式：
 *      synchronized(锁对象) {
 *          访问共享数据的代码
 *      }
 * 注意：
 *      1、同步代码块中的对象可以是任意对象
 *      2、必须保证多个线程的锁对象是同一个
 *      3、锁对象作用：
 *          只让一个线程在同步代码块中执行
 *
 * 实现原理：
 *      线程抢夺cpu执行权，遇到synchronized代码块，判断是否有对象锁，
 *          - 有对象锁，获取对象锁，进入同步代码块
 *          - 没有对象锁，进入阻塞状态，等待获取对象锁
 *
 *
 *      频繁判断锁，获取锁，释放锁，效率低
 *
 * @Author: zhuzw
 * @Date: 2020-03-03 20:01
 * @Version: 1.0
 */
public class SafeSolve1 implements Runnable{
    private int num = 1;
    Object object = new Object();
    @Override
    public void run() {
        while (num > 0) {
            System.out.println(Thread.currentThread().getName() + "准备卖票...");
            synchronized (object) {
                if (num > 0) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "卖票，卖出后" + (--num));
                }
            }
        }
        System.out.println(Thread.currentThread().getName() + "没有余票!");
    }

    public static void main(String[] args) {
        SafeSolve1 safeSolve1 = new SafeSolve1();
        Thread thread1 = new Thread(safeSolve1);
        Thread thread2 = new Thread(safeSolve1);
        Thread thread3 = new Thread(safeSolve1);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

package safe_solve;

/**
 * 线程安全问题解决方案2.1：同步方法，
 *
 * 使用步骤：
 *      1、把访问了共享数据的代码放到一个方法中
 *      2、在方法上添加synchronizd修饰符
 * 使用格式：
 *  synchronized 返回值类型 方法名(参数列表) {
 *      访问共享数据的代码
 *  }
 *
 *  实现原理：
 *       同步方法将方法内部的代码锁住，只让一个线程执行，
 *       同步方法的锁对象是谁？    ->  就是实现Runnable接口的对象，(this)
 *
 * @Author: zhuzw
 * @Date: 2020-03-03 20:01
 * @Version: 1.0
 */
public class SafeSolve2_1 implements Runnable{
    private int num = 1;
    @Override
    public void run() {
        while (num > 0) {
            System.out.println(Thread.currentThread().getName() + "准备卖票...");
            payTicket();
        }
        System.out.println(Thread.currentThread().getName() + "没有余票!");
    }

    public synchronized void payTicket() {

        if (num > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "卖票，卖出后" + (--num));
        }

    }

    public static void main(String[] args) {
        SafeSolve2_1 safeSolve2 = new SafeSolve2_1();
        Thread thread1 = new Thread(safeSolve2);
        Thread thread2 = new Thread(safeSolve2);
        Thread thread3 = new Thread(safeSolve2);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

package safe_solve;

/**
 * 线程安全问题解决方案2.2：静态同步方法，
 *
 *
 *  实现原理：
 *       见静态方法
 *
 * @Author: zhuzw
 * @Date: 2020-03-03 20:01
 * @Version: 1.0
 */
public class SafeSolve2_2 implements Runnable{
    private static int num = 1;
    @Override
    public void run() {
        while (num > 0) {
            payTicketStatic();
        }
        System.out.println(Thread.currentThread().getName() + "没有余票!");
    }

    /**
     * 静态的同步方法
     * 锁对象是谁？
     *  -> 不能是this了，this是创建对象之后产生的，静态方法优先于对象
     *  -> 静态方法的锁对象是本类的class（class文件对象）
     */
    public static synchronized void payTicketStatic() {
        System.out.println(Thread.currentThread().getName() + "准备卖票...");
        //验证
//        synchronized (Runnable.class) {
            if (num > 0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "卖票，卖出后" + (--num));
            }
//        }
    }

    public static void main(String[] args) {
        SafeSolve2_2 safeSolve2_2 = new SafeSolve2_2();
        Thread thread1 = new Thread(safeSolve2_2);
        Thread thread2 = new Thread(safeSolve2_2);
        Thread thread3 = new Thread(new SafeSolve2_2());
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

package visibility;

/**
 * 线程可见性演示
 *  - 程序无法正常停止
 *  - 原因：线程频繁使用runable，JIT（即时编译器）会将主存中的runable缓存到本线程高速缓存中
 *  - 解决：使用volatile修饰runable
 * @Author: zhuzw
 * @Date: 2020-08-19 12:30
 * @Version: 1.0
 */
public class ThreadVisibilityDemo {
    private static boolean runable = true;
    public static void main(String[] args) throws InterruptedException {
        visibilityProblem();
        visibilitySolve();
        visibilityFalseProblem();

        Thread.sleep(1000);
        runable = false;
    }


    /**
     * 可见性问题演示
     *
     * - 主线程修改后无法正常停止
     * @throws InterruptedException
     */
    private static void visibilityProblem() throws InterruptedException {
        Thread thread = new Thread(() -> {
            /**
             * 线程频繁使用runable，循环一定阈值后，会将主存中的runable缓存到本线程高速缓存中
             */
            while (runable) {

            }
            System.out.println("线程1结束...");
        });
        thread.start();
    }

    /**
     * 伪可见性问题，循环体中加入System.out.println();后可正常退出循环。
     * why?
     * 源代码加锁了
     */
    private static void visibilityFalseProblem() {
        Thread thread = new Thread(() -> {
            /**
             * 线程频繁使用runable，会将主存中的runable缓存到本线程高速缓存中
             */
            while (runable) {
                System.out.println();
            }
            System.out.println("线程2结束...");
        });
        thread.start();
    }


    /**
     * 有序性问题解决  使用volatile（易变）修饰变量
     */
    private static void visibilitySolve() {
    }
}

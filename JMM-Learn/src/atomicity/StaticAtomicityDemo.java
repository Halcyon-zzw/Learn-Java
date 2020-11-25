package atomicity;

/**
 * 静态原子性演示
 * 两个线程对初始值未0的静态变量分别做自增和自减，各做5000次，结果会是0吗?
 *  - 结果不一定是0，因为静态变量自增、自减不是原子操作
 *  如i++:   =》
 *      getstatic i //获取静态变量i的值
 *      iconst_1    //准备常量1
 *      iadd        //执行加法(在操作数栈中执行)
 *      putstatic   //将修改的值存入静态变量i
 *   i--    =》
 *      getstatic i
 *      iconst_1
 *      isub
 *      putstatic
 * 多线程问题情况：
 *      - 两种操作写回内存前都读取了数据，
 *      - 以后写回内存的操作数据为最后结果（覆盖）
 *
 * 解决 synchronized
 * @Author: zhuzw
 * @Date: 2020-08-18 12:23
 * @Version: 1.0
 */
public class StaticAtomicityDemo {
    public static int i = 0;
    public static int j = 0;
    public static void main(String[] args) throws InterruptedException {
        atomicityProblem();

        atomicitySolve();
    }

    private static void atomicitySolve() throws InterruptedException {
        Object object = new Object();
        Thread aThread = new Thread(() ->{
            for (int i1 = 0; i1 < 5000; i1++) {
                synchronized (object) {
                    j++;
                }
            }
        });
        Thread bThread = new Thread(() -> {
            for (int i1 = 0; i1 < 5000; i1++) {
                synchronized (object) {
                    j--;
                }
            }
        });
        aThread.start();
        bThread.start();
        aThread.join();
        bThread.join();
        System.out.println(j);
    }

    private static void atomicityProblem() throws InterruptedException {
        Thread aThread = new Thread(() ->{
            for (int i1 = 0; i1 < 5000; i1++) {
                i++;
            }
        });
        Thread bThread = new Thread(() -> {
            for (int i1 = 0; i1 < 5000; i1++) {
                i--;
            }
        });
        aThread.start();
        bThread.start();
        aThread.join();
        bThread.join();
        System.out.println(i);
    }
}

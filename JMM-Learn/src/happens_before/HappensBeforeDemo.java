package happens_before;

/**
 * happens-before演示类
 * 规定哪些写操作对其他线程的读操作可见
 *
 * @Author: zhuzw
 * @Date: 2020-08-19 20:30
 * @Version: 1.0
 */
public class HappensBeforeDemo {
    /**
     * 1、线程解锁m前对变量的写，对于接下来对m加锁的线程 对该变量的读可见
     * 2、线程对volatile变量的写，对其他线程对该变量的读可见
     * 3、线程start前对变量的写，对线程开始后对该变量的读可见
     * 4、线程结束前对变量的写，对其他线程得知它结束后的读可见（比如其他线程调用t.isAlive()或t.join()等待它结束）
     * 5、线程t1打断t2前对变量的写，对其他线程得知t2被打断对变量的读可见（通过t.interrupted()或t.isInterrupted()判断是否打断【类似结束】）
     * 6、对变量默认值（0，false，null ）的写，对其他线程对该变量的读可见
     * 7、具有传递性，
     */
    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
        demo4();
        demo5();
        demo6();
    }
    private static void demo1() {
    }

    private static void demo2() {

    }

    private static void demo3() {

    }

    private static void demo4() {
    }

    private static void demo5() {
    }

    private static void demo6() {
    }



}

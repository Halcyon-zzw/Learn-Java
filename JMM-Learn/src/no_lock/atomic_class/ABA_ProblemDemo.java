package no_lock.atomic_class;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * ABA问题，主程序无法感知到其他线程对共享变量的修改
 *
 * @Author: zhuzw
 * @Date: 2020-11-24 19:58
 * @Version: 1.0
 */
public class ABA_ProblemDemo {
    static AtomicReference<String> aref = new AtomicReference<>("A");
    public static void main(String[] args) {
        abaProblemDemo();
    }



    private static void abaProblemDemo() {
        String prev = aref.get();
        other();
        sleep(2);
        boolean change = aref.compareAndSet(prev, "C");
        System.out.println("change A -> C:" + change);
    }

    private static void sleep(int i) {
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void other() {

        new Thread(() -> {
            String prev = aref.get();
            boolean changeB = aref.compareAndSet(prev, "B");
            System.out.println("change A -> B:" + changeB);
        }).start();
        sleep(1);
        new Thread(() -> {
            String prev = aref.get();
            boolean changeA = aref.compareAndSet(prev, "A");
            System.out.println("change B -> A:" + changeA);
        }).start();

    }
}

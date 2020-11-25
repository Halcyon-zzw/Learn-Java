package no_lock.atomic_class;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

/**
 * AtomicInteger原子类演示
 *
 * @Author: zhuzw
 * @Date: 2020-11-24 19:21
 * @Version: 1.0
 */
public class AtmoicIntegerDemo {
    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger(10);

        int result = updateAndGet(integer, prev -> prev * 10);
        System.out.println(result);
    }

    /**
     * 手动实现updateAndGet方法
     * @param integer
     * @param intUnaryOperator
     * @return
     */
    private static int updateAndGet(AtomicInteger integer, IntUnaryOperator intUnaryOperator) {
        int prev, next;
        while (true) {
            prev = integer.get();
            next = intUnaryOperator.applyAsInt(prev);
            if (integer.compareAndSet(prev, next)) {
                return next;
            }
        }
    }
}

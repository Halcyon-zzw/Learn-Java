package runtime_optimization;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 字段优化演示
 *
 *
 * 分析：
 *  doSum方法是否内联会影响elements成员变量读取的优化
 *      => 将方法内部实现替换掉方法调用
 * @Author: zhuzw
 * @Date: 2020-08-13 19:21
 * @Version: 1.0
 */
@Warmup(iterations = 2, time = 1)
@Measurement(iterations = 5, time = 1)
@State(Scope.Benchmark)
public class FieldOptimizationDemo {
    volatile int[] elements = randomInts(1_00);

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(Benchmark.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }

    private static int[] randomInts(int size) {
        Random random = ThreadLocalRandom.current();
        int[] values = new int[size];
        for (int i = 0; i < size; i++) {
            values[i] = random.nextInt();
        }
        return values;
    }

    @Benchmark
    public void test1() {

        for (int i = 0; i < elements.length; i++) {
            doSum(elements[i]);
        }

        /**
         //优化后：elements.length 首次读取会将数组缓存起来 -> int[] local
         //后续999次不用求长度，直接从local获取
         for (int i = 0; i < elements.length; i++) {
            sum += elements[i]; //不用再去元空间获取元素，直接从local获取
         }
         可节省1999次Field读取操作
         若dosum没有内联不会进行上述优化
         */
    }

    @Benchmark
    public void test2() {
        //手动将值赋值给本地变量，使用时不用再去元空间内获取
        int[] local = this.elements;
        for (int i = 0; i < elements.length; i++) {
            doSum(local[i]);
        }
    }

    @Benchmark
    public void test3() {
        for (int element : elements) {
            doSum(element);
        }
    }

    static int sum = 0;

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    static void doSum(int x) {
        sum += x;
    }
}

package runtime_optimization;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射优化 演示类
 *
 * 当invoke调用超过16次时，会在运行期间动态生成新的方法访问器，并替换掉原来的方法访问器
 * invoke实现为正常的方法调用
 *
 * @Author: zhuzw
 * @Date: 2020-08-13 20:01
 * @Version: 1.0
 */
public class ReflectOptimizationDemo {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method foo = ReflectOptimizationDemo.class.getMethod("foo");
        long start = 0;
        long end = 0;
        for (int i = 0; i <= 16; i++) {
            start = System.currentTimeMillis();

            foo.invoke(null);
            end = System.currentTimeMillis();
            System.out.printf("%d\t%d", i, (end - start));
            System.out.println();
        }
    }
    public static void foo() {
        System.out.printf("foo...");
    }
}

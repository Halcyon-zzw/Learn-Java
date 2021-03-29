package memory_structure.runtime_optimization;

/**
 * 运行时优化 - “方法内敛”演示
 * 参数：
 *  打印内联信息： -XX:+UnlockDiagnosticVMOptions -XX:+PrintInlining
 *  禁用内联： -XX:CompileCommand=dontinline,*MethodInlineDemo.square
 *  -XX:+PrintCompilation
 * @Author: zhuzw
 * @Date: 2020-08-11 22:25
 * @Version: 1.0
 */
public class MethodInlineDemo {
    public static void main(String[] args) {
        int x = 0;
        long start = 0;
        long end = 0;
        for (int i = 0; i < 500; i++) {
            start = System.nanoTime();
            for (int j = 0; j < 10000; j ++) {
                x = square(9);
            }
            end = System.nanoTime();
            System.out.println(i + "\t" + (end - start));
        }
    }

    private static int square(int x) {
        return x * x;
    }
}

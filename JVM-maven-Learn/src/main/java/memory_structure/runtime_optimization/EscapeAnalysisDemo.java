package memory_structure.runtime_optimization;

/**
 * 运行时优化 - 逃逸分析演示
 *
 * @Author: zhuzw
 * @Date: 2020-08-11 22:13
 * @Version: 1.0
 */
public class EscapeAnalysisDemo {
    /**
     * 一定次数以后花费的时间明显变少
     *  - 因为new Object()调用次数过多后成为热点代码，且外部未引用，进行了“逃逸分析”优化
     *  - 开关 -XX:+DoEscapeAnalysis
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 200; i++) {
            long start = System.nanoTime();
            for (int j = 0; j < 10000; j++) {
                new Object();
            }
            long end = System.nanoTime();
            System.out.println(i + "\t" + (end - start));
        }
    }
}

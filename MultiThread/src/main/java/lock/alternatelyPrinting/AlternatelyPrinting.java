package lock.alternatelyPrinting;

/**
 * 线程交替输出
 *
 * 线程1输出a
 * 线程2输出b
 * 线程3输出c
 * 线程交替输出n次，
 * 如交替输出5次：abcabcabcabcabc
 *
 * @Author: zhuzw
 * @Date: 2021-05-10 18:44
 * @Version: 1.0
 */
public interface AlternatelyPrinting {
    void apply();
}

package demo;

/**
 * 计算前n项的和
 *
 * @Author: zhuzw
 * @Date: 2020-03-10 19:14
 * @Version: 1.0
 */
public class CalculateSum {
    public static void main(String[] args) {
        System.out.println(f(100));
    }

    public static int f(int n) {
        if (n == 1) {
            return 1;
        }
        return f(n-1) + n;
    }
}

package function_interface;

import java.util.function.Supplier;

/**
 * 供应商函数式接口
 *
 * @Author: zhuzw
 * @Date: 2020-03-20 18:52
 * @Version: 1.0
 */
public class SupplierDemo {

    public static void main(String[] args) {
        int[] arr = {1, 4, 9, 3};
        int maxValue = getMax(() ->{
           //获取数据的最大值，并返回
            int max = arr[0];
            for (int i : arr) {
                if (i > max) {
                    max = i;
                }
            }
            return max;
        });
        System.out.println("最大值：" + maxValue);

    }

    public static int getMax(Supplier<Integer> supplier) {
        return supplier.get();
    }
}

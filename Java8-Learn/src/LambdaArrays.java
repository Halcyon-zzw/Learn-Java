import java.util.Arrays;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020/2/7 15:54
 * @Version: 1.0
 */
public class LambdaArrays {
    public static void main(String[] args) {
        //排序
        String[] arr1 = new String[]{"java", "fkava", "fkit", "ios", "android"};
        //Lambda目标类型是Comparator
        Arrays.parallelSort(arr1, (o1, o2) -> o1.length() - o2.length());
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = new int[]{4, 2, 5, 3, 7, 1};
        //last代表前一个索引处的元素，curr代表当前索引元素；计算第一个元素时，last为1
        //Lambda目标类型是IntBinaryOperator，根据前后两个元素计算当前元素的值
        Arrays.parallelPrefix(arr2, (last, curr) -> last * curr);
        System.out.println(Arrays.toString(arr2));

        long[] arr3 = new long[5];
        //operand代表元素所在索引（从0开始）
        //Lambda目标类型是IntToLongFunction，该对象根据元素的索引来计算当前元素的值
        Arrays.parallelSetAll(arr3, operand -> operand * 5);
        System.out.println(Arrays.toString(arr3));

    }
}

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2021-05-22 21:06
 * @Version: 1.0
 */
public class Two {
    public int robMax(int[] nums) {
        //边界情况
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        } else if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        return Math.max(robMaxRange(nums, 0, length - 2), robMaxRange(nums, 1, length - 1));
    }

    public int robMaxRange(int[] nums, int start, int end) {
        int m = nums[start], n = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            int temp = n;
            n = Math.max(m + nums[i], n);
            m = temp;
        }
        return n;
    }
}

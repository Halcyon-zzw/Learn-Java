/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2021-05-22 20:58
 * @Version: 1.0
 */
public class One {
    public int maxDist(int[] seats) {
        int length = seats.length;
        //连续0的个数
        int n = 0;
        //最长0的个数
        int result = 0;

        for (int i = 0; i < length; ++i) {
            if (seats[i] == 1) {
                n = 0;
            } else {
                n++;
                result = Math.max(result, (n + 1) / 2);
            }
        }
        //左边
        for (int i = 0; i < length; ++i) {
            if (seats[i] == 1) {
                result = Math.max(result, i);
                break;
            }
        }
        //右边
        for (int i = length - 1; i >= 0; --i) {
            if (seats[i] == 1) {
                result = Math.max(result, length - 1 - i);
                break;
            }
        }
        return result;
    }


}

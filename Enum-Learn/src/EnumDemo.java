/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020/2/7 17:16
 * @Version: 1.0
 */
public class EnumDemo {
    public static void main(String[] args) {

        System.out.println(SeasonEnum.SSUMMER.ordinal());
        System.out.println(SeasonEnum.SSUMMER.compareTo(SeasonEnum.SPRING));
        System.out.println(SeasonEnum.SSUMMER.toString());
    }
}

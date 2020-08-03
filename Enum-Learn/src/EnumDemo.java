/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020/2/7 17:16
 * @Version: 1.0
 */
public class EnumDemo {
    public static void main(String[] args) {
        System.out.println(SeasonEnum.SUMMER.ordinal());
//        SeasonEnum.valueOf();
        System.out.println(Enum.valueOf(SeasonEnum.class, "SUMMER"));
        System.out.println(SeasonEnum.SUMMER.compareTo(SeasonEnum.SPRING));
        System.out.println(SeasonEnum.SUMMER.toString());
    }
}

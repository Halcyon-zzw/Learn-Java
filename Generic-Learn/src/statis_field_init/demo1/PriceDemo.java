package statis_field_init.demo1;

/**
 * 静态成员初始化演示
 *
 * @Author: zhuzw
 * @Date: 2020-07-31 16:55
 * @Version: 1.0
 */
class Price {
    final static Price INSTANCE = new Price(2);
    static int initPrice = 20;
    double currenPrice;

    public Price(double discount) {
        this.currenPrice = initPrice - discount;
    }
}

public class PriceDemo {
    public static void main(String[] args) {
        //输出-2.0，因为InSTANCE初始化时调用构造方法执行initPrice - discount时，initPrice的值为0
        System.out.println(Price.INSTANCE.currenPrice);

        //正常输出18，因为此时类的所有变量都已初始化完成了
        System.out.println(new Price(2).currenPrice);
    }
}

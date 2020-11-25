package byte_code;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-08-03 12:27
 * @Version: 1.0
 */
public class Demo {
    public static void main(String[] args) {
        demo1();
        System.out.println("======================");
        demo2();
    }

    /**
     * 字节码角度解析
     */
    public static void demo1() {
        int i = 0;
        int x = 0;
        while (i < 10) {
            x = x++;
            i++;
        }
        /**
         * 输出为 0，
         * 因为x++为 iload x 、iinc，先加载x到操作数栈(x = 0),操作数列表再自增(x = 1)，
         * 最后再进行赋值操作，使操作数列表中的x值等于操作数栈中的0
         */
        System.out.println(x);
    }

    public static void demo2() {
        int a = 10;
        int b = 0;
        b = a++ + ++a + a--;
        System.out.println("a:" + a);
        System.out.println("b:" + b);
    }

}



package final_demo;

/**
 * final变量转宏定义演示
 *   只有在初始化时赋值才可能被转为宏定义
 *   在今天初始化和构造方法中进行赋值的不会生效
 *
 * @Author: zhuzw
 * @Date: 2020-08-06 10:23
 * @Version: 1.0
 */
public class FinalMacroDemo {
    //只有初始化时赋值才会进行 宏替换，在代码块及初始化中无效
    private final String a = "zhu";
    private final String b;
    private final String c;

    {
        b = "zhu";
    }

    public FinalMacroDemo() {
        this.c = "zhu";
    }

    public void demo() {
        System.out.println(a + a == "zhuzhu");
        System.out.println(b + b == "zhuzhu");
        System.out.println(c + c == "zhuzhu");
    }

    public static void main(String[] args) {
        FinalMacroDemo test = new FinalMacroDemo();
        test.demo();
    }
}

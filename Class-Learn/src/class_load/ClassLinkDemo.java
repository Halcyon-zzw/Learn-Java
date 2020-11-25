package class_load;

/**
 * 类加载 - 链接演示
 *
 * 反编译演示类的链接 javap -v -c className
 *
 * @Author: zhuzw
 * @Date: 2020-08-06 19:53
 * @Version: 1.0
 */
public class ClassLinkDemo {
    private final static int a = 9;

    private final static String b = "hello";    // 编译阶段知道b的值，在准备阶段可以直接赋值
    private final static String c = String.valueOf(a + 1) ; //应该使用了函数，所有无法在准备阶段赋值，需要到初始化时赋值


}

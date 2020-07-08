/**
 * 模拟类加载过程
 *
 * @Author: zhuzw
 * @Date: 2020-06-22 16:57
 * @Version: 1.0
 */
public class ClassLoadDemo {
    public static void main(String[] args) {
        A a = new A();
        System.out.println(A.m);
    }
}

/**
 *
 *      1、类加载 -> 创建Class对象
 *      2、类链接 -> 将静态变量设置默认值（分配内存）
 *      3、类的初始化 -> 执行类构造器（clinit()方法），执行静态代码块和静态变量初始化
 */
class A {
    static {
        System.out.println("静态代码块初始化");
        m = 300;
    }
    static int m = initM();

    private static int initM() {
        System.out.println("static字段初始化");
        return 100;
    }

    public A() {
        System.out.println("执行无参构造器");
    }
}
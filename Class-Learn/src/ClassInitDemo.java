/**
 * 类的初始化演示
 *
 * @Author: zhuzw
 * @Date: 2020-07-02 15:45
 * @Version: 1.0
 */
public class ClassInitDemo {
    static {
        System.out.println("Main所在类被初始化。");
    }
    public static void main(String[] args) throws ClassNotFoundException {
        // ======== 测试主动引用=========
        //测试new
//        new Son();
//        new Father();
        //测试调用静态成员
//        int m = Son.n;
        //测试反射
//        Class.forName("Son");
        //Son.class只执行类加载过程
//        Class<Son> sonClass = Son.class;


        //=========测试被动引用==========
        //通过子类调用父类静态域 -- 初始化静态域所在类
//        int n = Son.n;
        //定义类的数组 -- 只分配空间
//        Son[] sonArr = new Son[5];
        //引用常量 -- 类链接时已加载进常量池
        int num = Son.NUM;

    }
}

class Father {
    static {
        System.out.println("父类被初始化。");
    }
    static int n = 200;
}

class Son extends Father{
    static {
        System.out.println("子类被初始化。");
    }
    static int m = 100;
    static final int NUM = 1;
}

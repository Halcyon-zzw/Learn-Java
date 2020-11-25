package class_load;

/**
 * 实例懒惰实例化单例模式
 *
 *  能保证线程安全：因为类加载器会保证类初始化的线程安全
 *
 * @Author: zhuzw
 * @Date: 2020-08-06 20:46
 * @Version: 1.0
 */
public class LazyInitDemo {
    public static void main(String[] args) {
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        System.out.println(singleton1 == singleton2);
    }
}

class Singleton {
    private Singleton() {}

    private static class LasyHolder {
        private static final Singleton SINGLETON = new Singleton();
        static {
            System.out.println("初始化Single...");
        }
    }
    public static Singleton getInstance() {
        return LasyHolder.SINGLETON;
    }
}

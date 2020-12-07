package whole_plan;

/**
 * 烧水泡茶演示
 *
 * 泡茶说明：
 * 洗水壶、烧开水、洗茶壶、洗茶杯、拿茶叶、泡茶
 *
 * 缺陷;数据不共享，如需要交烧好的水交给对方；
 * 上述为小王等老王泡茶，如老王等小王泡茶无法适应。
 * @Author: zhuzw
 * @Date: 2020-10-13 20:25
 * @Version: 1.0
 */
public class MakeTeaDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            washKettle();
            boilWater();
        }, "老王");
        t1.start();
        Thread t2 = new Thread(() -> {
            washTeapot();
            washTeacup();
            takeTea();
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            makeTea();
        }, "小王");
        t2.start();
    }

    public static void washKettle() {
        System.out.println("洗水壶");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void boilWater() {
        System.out.println("烧开水");
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void washTeapot() {
        System.out.println("洗茶壶");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void washTeacup() {
        System.out.println("洗茶杯");
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void takeTea() {
        System.out.println("拿茶叶");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void makeTea() {
        System.out.println("泡茶");
    }



}

package final_demo;

/**
 * 演示为何内部类访问外部变量需要final
 *
 *  => 执行方法发现，在main方法结束后，局部变量name仍然被访问（局部变量的作用域被扩大了），
 *      如果没有使用final，变量将可随意改变，那将引起极大混乱（什么混乱，暂不清楚）
 *
 *
 * @Author: zhuzw
 * @Date: 2020-08-07 14:04
 * @Version: 1.0
 */
public class FinalWhyDemo {
    public static void main(String[] args) {
        final String name = "zzw";
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("访问方法局部变量:" + name + ":" + i);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        System.out.println("main方法结束");
    }
}

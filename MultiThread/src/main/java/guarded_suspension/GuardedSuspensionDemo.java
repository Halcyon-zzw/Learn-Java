package guarded_suspension;

/**
 * 同步模式（因为需要等待其他线程的结果）之保护性暂停模式演示类
 *
 * 用在一个线程等待另一个线程的执行结果
 *
 * jion的实现应用此模式
 *
 * @Author: zhuzw
 * @Date: 2020-10-28 19:21
 * @Version: 1.0
 */
public class GuardedSuspensionDemo {
    public static void main(String[] args) {
        GuardedObj guardedObj = new GuardedObj();

        //获取结果
        new Thread(() -> {
            System.out.println("t1等待结果...");
            Object o = guardedObj.get(3000);
            System.out.println(o);
        },"t1").start();

        //产生结果
        new Thread(() -> {
            try {
                System.out.println("t2产生结果...");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            guardedObj.accept(new Object());
        },"t2").start();
    }
}

package orderliness;

/**
 * 情况1：actor1先判断，actor2未开始执行，结果为1
 * 情况2：actor1先判断，actor2执行至num = 2，未执行ready = true，结果为1
 * 情况3：actor2执行完，actor1在判断,此时ready = true，结果为4
 * -情况4：发生指令重排，先执行了actor2的ready=true,num还未赋值，结果为0 （特殊，且不易发生，需使用jcsstress工具演示）
 *
 * @Author: zhuzw
 * @Date: 2020-08-19 19:25
 * @Version: 1.0
 */
public class OrderlinessDemo {
    public int result = 0;

    public static void main(String[] args) {
        OrderlinessDemo orderlinessDemo = new OrderlinessDemo();
        for (int i = 0; i < 1000; i++) {
            Actor actor = new Actor();
            Thread thread1 = new Thread(() -> {
                actor.actor1(orderlinessDemo);
            });
            Thread thread2 = new Thread(() -> {
                actor.actor2();
            });
            thread1.start();
            thread2.start();
            System.out.println(orderlinessDemo.result);
        }
    }
}

class Actor {

    int num = 0;
    boolean ready = false;

    public void actor1(OrderlinessDemo orderlinessDemo) {
        if (ready) {
            orderlinessDemo.result = num + num;
        }else {
            orderlinessDemo.result = 1;
        }
    }

    public void actor2() {
        num = 2;
        ready = true;
    }
}

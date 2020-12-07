import java.util.concurrent.TimeUnit;

/**
 * 守护线程演示
 *
 * 所有非守护线程结束后，就算守护线程未结束依旧会结束
 *
 * 1、垃圾回收线程是一种守护线程
 * 2、Tomcat中的Acceptor和Poller线程都是守护线程，所以Tomcat接收到shutdown命令后，不会其等待处理完请求
 *
 * @Author: zhuzw
 * @Date: 2020-10-13 19:36
 * @Version: 1.0
 */
public class DaemonThreadDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //设置为守护线程
        thread.setDaemon(true);
        thread.start();;
        System.out.println("主线程结束...");
    }
}

package communication.wait_and_notify;

/**
 * 生产者：包子生产者类
 *
 * 注意：
 *      包子铺线程和吃包子者线程关系， -> 通信（互斥）
 *      必须使用同步技术保证两个线程只能有一个在执行（锁对象唯一）
 *
 *
 * @Author: zhuzw
 * @Date: 2020-03-05 20:10
 * @Version: 1.0
 */
public class BunProducer implements Runnable{
    private Bun bun;

    public BunProducer(Bun bun) {
        this.bun = bun;
    }


    @Override
    public void run() {
        //生产包子
        while (true) {
            synchronized (bun) {
                if (!bun.isHaveBun()) {
                    //没有包子，生产
                    System.out.println("没有包子，生产包子中(5s)...");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    bun.setHaveBun(true);
                    System.out.println("包子生产完成，通知消费包子。");
                    //通知消费
                    bun.notify();
                }
                //生产完或还有包子,等待消费掉
                try {
                    System.out.println("有包子，等待吃完包子...");
                    bun.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

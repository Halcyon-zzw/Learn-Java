package communication.wait_and_notify.consume_which_have;

/**
 * 消费者类：包子吃货类
 *
 * @Author: zhuzw
 * @Date: 2020-03-05 20:17
 * @Version: 1.0
 */
public class BunConsumer implements Runnable{
    private Bun bun;

    public BunConsumer(Bun bun) {
        this.bun = bun;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (bun) {
                if (bun.isHaveBun()) {
                    //有包子，吃包子
                    System.out.println("有包子，吃包子中(3s)...");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    bun.setHaveBun(false);
                    System.out.println("包子吃完，通知做包子。");
                    bun.notify();

                }
                try {
                    System.out.println("等待做包子...");
                    System.out.println("-------------华丽的分割线----------------");
                    bun.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}

package communication.wait_and_notify.consume_which_have;

/**
 * 有即消费的线程通信演示
 *
 * @Author: zhuzw
 * @Date: 2020-03-05 20:06
 * @Version: 1.0
 */
public class ConsumeWhichHaveDemo {
    public static void main(String[] args) {
        Bun bun = new Bun();

        Thread bunProducerThread = new Thread(new BunConsumer(bun));
        Thread bunConsumerThread = new Thread(new BunProducer(bun));

        System.out.println("生产一个，消费一个演示:");
        bunProducerThread.start();
        bunConsumerThread.start();
    }
}

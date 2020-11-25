package safe.transfer;

import java.util.Random;

/**
 * 线程安全问题之转账
 *
 * @Author: zhuzw
 * @Date: 2020-10-20 18:40
 * @Version: 1.0
 */
public class TransferDemo {
    static Random random = new Random();
    public static void main(String[] args) throws InterruptedException {
        problem();
        System.out.println("================");
        solve();

    }

    private static void solve() throws InterruptedException {
        Account a = new Account(1000);
        Account b = new Account(1000);
        System.out.println("before transfer:" + (a.getMoney() + b.getMoney()));

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                a.transferSolve(b, randomAmount());
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                a.transferSolve(b, randomAmount());
            }
        }, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("after transfer:" + (a.getMoney() + b.getMoney()));
    }

    private static void problem() throws InterruptedException {
        Account a = new Account(1000);
        Account b = new Account(1000);
        System.out.println("before transfer:" + (a.getMoney() + b.getMoney()));

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                a.transfer(b, randomAmount());
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                a.transfer(b, randomAmount());
            }
        }, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("after transfer:" + (a.getMoney() + b.getMoney()));
    }

    public static int randomAmount() {
        return random.nextInt(100) + 1;
    }
}

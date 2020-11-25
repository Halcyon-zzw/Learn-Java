package no_lock.atomic_class;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA问题解决，使用AtomicStampedReference保护共享变量，通过新引入版本号解决；相当于比较两个值
 *
 * @Author: zhuzw
 * @Date: 2020-11-24 20:13
 * @Version: 1.0
 */
public class ABA_ProblemSolve {
    static AtomicStampedReference<String> asref = new AtomicStampedReference<>("A", 0);
    public static void main(String[] args) {
        String prev = asref.getReference();
        int preStamp = asref.getStamp();
        other();
        sleep(2);
        System.out.println("历史版本：" + preStamp + ",当前版本：" + asref.getStamp());
        boolean change = asref.compareAndSet(prev, "C", preStamp, preStamp + 1);

        System.out.println("A -> C:" + change);
    }

    private static void sleep(int i) {
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void other() {

        new Thread(() -> {
            String prev = asref.getReference();
            int preStamp = asref.getStamp();
            System.out.println("历史版本：" + preStamp + ",当前版本：" + asref.getStamp());
            boolean changeB = asref.compareAndSet(prev, "B", preStamp, preStamp + 1);
            System.out.println("change A -> B:" + changeB);
        }).start();
        sleep(1);
        new Thread(() -> {
            String prev = asref.getReference();
            int preStamp = asref.getStamp();
            System.out.println("历史版本：" + preStamp + ",当前版本：" + asref.getStamp());
            boolean changeA = asref.compareAndSet(prev, "A", preStamp, preStamp + 1);
            System.out.println("change B -> A:" + changeA);
        }).start();

    }
}

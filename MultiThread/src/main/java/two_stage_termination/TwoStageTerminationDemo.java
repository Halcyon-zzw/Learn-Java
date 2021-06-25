package two_stage_termination;

import util.ThreadUtils;

/**
 * 两阶段终止模式
 *
 * @Author: zhuzw
 * @Date: 2021-05-07 14:33
 * @Version: 1.0
 */
public class TwoStageTerminationDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true) {
                Thread cur = Thread.currentThread();
                if (cur.isInterrupted()) {
                    System.out.println("料理后事...");
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("被打断...");
                    //会清空打断标记，需要重新打断
                    cur.interrupt();
                }
            }
        });

        thread.start();
        ThreadUtils.sleep(500);
        thread.interrupt();
    }
}

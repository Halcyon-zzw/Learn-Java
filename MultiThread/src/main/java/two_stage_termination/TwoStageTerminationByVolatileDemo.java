package two_stage_termination;

import util.ThreadUtils;

/**
 * 两阶段终止模式
 *
 * @Author: zhuzw
 * @Date: 2021-05-07 14:33
 * @Version: 1.0
 */
public class TwoStageTerminationByVolatileDemo {
    private static boolean stop;
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true) {
                Thread cur = Thread.currentThread();
                if (stop) {
                    System.out.println("料理后事...");
                    break;
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println("被打断...");

                }
            }
        });

        thread.start();
        ThreadUtils.sleep(500);
        stop = true;
        //打断sleep中的线程，立刻结束
        thread.interrupt();
    }

}

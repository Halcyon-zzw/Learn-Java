package memory_structure;

/**
 * 堆内存监测演示
 *
 * @Author: zhuzw
 * @Date: 2020-07-14 19:53
 * @Version: 1.0
 */
public class HeapMonitorDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("1....");
        Thread.sleep(20000);
        byte[] bytes = new byte[1024 * 1024 * 1024];
        System.out.println("2....");
        Thread.sleep(20000);
        bytes=null;
        System.gc();
        System.out.println("3....");
        Thread.sleep(20000);
    }


}

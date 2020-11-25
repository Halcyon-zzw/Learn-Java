package lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * cas compare and swap
 *
 * 如：多线程i++:
 * while(true) {
 *     int oldValue = 共享变量;
 *     int result = oldValue + 1;
 *     if (compareAndSwap(oldValue, result)) {
 *         修改值
 *         退出循环
 *     }
 * }
 * 注意：
 *    获取共享变量，保证可见性 -> 使用volatile
 *
 *    使用竞争不激烈（激烈导致重试频繁发生）、多核cpu(否则其中一线程重试，其他线程无法重试)。
 *
 * 底层实现：
 *      Unsafe调用底层CAS指令
 *
 *
 * @Author: zhuzw
 * @Date: 2020-09-04 19:34
 * @Version: 1.0
 */
public class CASDemo {
    static int data = 0;
    public static void main(String[] args) throws InterruptedException {
        testCAS();
        testNonCAS();
    }

    private static void testNonCAS() throws InterruptedException {
        int count = 5000;
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < count; i++) {
                data++;
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < count; i++) {
                data--;
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(data);
    }

    public static void testCAS() throws InterruptedException {
        DataContainer dc = new DataContainer(10);

        int count1 = 190;
        int count2 = 100;

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < count1; i++) {
                dc.increase();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < count2; i++) {
                dc.decrease();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(dc.getData());
    }
}

class DataContainer {
    private volatile int data;

    public DataContainer() {
    }

    public DataContainer(int data) {
        this.data = data;
    }

    static final Unsafe unsafe;

    /**
     * 地址偏移量
     */
    static final long DATA_OFFSET;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new Error(e);
        }
        try {
            DATA_OFFSET = unsafe.objectFieldOffset(DataContainer.class.getDeclaredField("data"));
        } catch (NoSuchFieldException e) {
            throw new Error(e);
        }

    }

    public void increase() {
        int oldValue;

        while (true) {
            oldValue = data;
            //this + DATA_OFFSET 能确定对哪个字段做compareAndSwap
            if (unsafe.compareAndSwapInt(this, DATA_OFFSET, oldValue, oldValue + 1)) {
                return;
            }
        }
    }

    public void decrease() {
        int oldValue;
        while (true) {
            oldValue = data;
            if (unsafe.compareAndSwapInt(this, DATA_OFFSET, oldValue, oldValue - 1)) {
                return;
            }
        }
    }

    public int getData() {
        return data;
    }
}


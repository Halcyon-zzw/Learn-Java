package memory_structure.constant_pool.direct_memory;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 直接内存演示：
 *      利用直接内存复制数据文件与传统io复制的区别
 *
 *
 * @Author: zhuzw
 * @Date: 2020-07-19 22:24
 * @Version: 1.0
 */
public class DirectMemeoryDemo {
    private static final String FROM = "E:\\文件\\学习视频\\test\\公司级框架原理解析(3)-公司级框架原理解析(3)[超清版].flv";
    private static final String TO = "E:\\公司级框架原理解析(3)-公司级框架原理解析(3)[超清版].flv";
    private static final int SIZE_1M = 1024 * 1024;

    public static void main(String[] args) throws IOException {
        long start = System.nanoTime();
        traditionalIO();
        long end = System.nanoTime();
        System.out.println("io耗时："  + (end - start) / 1000_000.00);
        directBuffer();
        long endDirectBuffer = System.nanoTime();
        System.out.println("directBuffer耗时："  + (endDirectBuffer - end) / 1000_000.00);
    }

    /**
     * 传统阻塞IO
     */
    private static void directBuffer() throws IOException {
        FileChannel from = new FileInputStream(FROM).getChannel();
        FileChannel to = new FileOutputStream(TO).getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(SIZE_1M);

        while (true) {
            int len = from.read(byteBuffer);
            if (-1 == len) {
                break;
            }
            byteBuffer.flip();
            to.write(byteBuffer);
            byteBuffer.clear();
        }
    }

    /**
     *
     */
    private static void traditionalIO() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(FROM);
        FileOutputStream fileOutputStream = new FileOutputStream(TO);

        byte[] buf = new byte[SIZE_1M];
        while (true) {
            int len = fileInputStream.read(buf);
            if (-1 == len) {
                break;
            }
            fileOutputStream.write(buf, 0, len);
        }
    }
}

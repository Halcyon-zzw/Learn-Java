package garbage_collection;

import config.ConfigLocal;

import java.util.ArrayList;

/**
 * 垃圾回收 - GC分析演示 - 对应P65、P66
 *
 * @Author: zhuzw
 * @Date: 2020-07-22 18:41
 * @Version: 1.0
 */
public class GCAnalysicDemo {
    //堆内存初始20M、最大20M    新生代：10M     使用SerialGC  打印GC信息
    //-Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:+PrintGCDetails -verbose:gc
    public static void main(String[] args) {
        ArrayList<byte[]> list = new ArrayList<>();
        list.add(new byte[ConfigLocal._1MB * 8]);
        list.add(new byte[ConfigLocal._1MB * 8]);
//        list.add(new byte[ConfigLocal._512KB]);
//        list.add(new byte[ConfigLocal._512KB]);
    }
}

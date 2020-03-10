import java.lang.reflect.Field;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-03-02 20:22
 * @Version: 1.0
 */
public class Demo {

    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        mainThread.setName("主线程");
        System.out.println(mainThread.getName());

    }


}

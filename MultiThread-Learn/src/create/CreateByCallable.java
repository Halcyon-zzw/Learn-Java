package create;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 使用Callable、Future创建线程
 * 步骤：
 *      - 创建Callable接口的实现类；
 *      - 
 *
 * @Author: zhuzw
 * @Date: 2020-07-10 18:30
 * @Version: 1.0
 */
public class CreateByCallable implements Callable {
    @Override
    public Object call() throws Exception {
        return null;
    }

    public static void main(String[] args) {

        //使用FutureTask包装Callable对象
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            int i = 0;
            for (; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "的循环变量i的值：" + i);
            }
            return i;
        });
        new Thread(futureTask, "有返回值的线程").start();

        try {
            System.out.println("子线程的返回值：" + futureTask.get());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

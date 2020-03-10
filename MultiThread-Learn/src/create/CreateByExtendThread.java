package create;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-03-03 19:21
 * @Version: 1.0
 */
public class CreateByExtendThread extends Thread {


    @Override
    public void run() {
        Thread thread = new Thread(() -> {
            System.out.println(1);
        });
    }
}

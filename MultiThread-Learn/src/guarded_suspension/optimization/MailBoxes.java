package guarded_suspension.optimization;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-10-29 19:21
 * @Version: 1.0
 */
public class MailBoxes {
    private static int id = 1;

    private static synchronized int genrateId() {
        return id++;
    }
}

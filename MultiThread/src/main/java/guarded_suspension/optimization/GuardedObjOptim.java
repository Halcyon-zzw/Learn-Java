package guarded_suspension.optimization;

import guarded_suspension.GuardedObj;

/**
 * 保护性暂停模式扩展：解耦等待和生产
 *
 *
 * @Author: zhuzw
 * @Date: 2020-10-29 19:18
 * @Version: 1.0
 */
public class GuardedObjOptim extends GuardedObj {
    private int id;

    public GuardedObjOptim(int id) {
        this.id = id;
    }
}

package byte_code;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-08-04 19:40
 * @Version: 1.0
 */
public class FinallyDemo {
    public static void main(String[] args) {
        System.out.println(finallyDemo1());
    }

    /**
     * 方法最后方法20，且不会抛出任何异常
     * 说明：
     *  1、（因为finally中的字节码被插入到了所有流程中（try、catch））
     *  2、finally中有return会吞掉所有的异常，所以建议finally中不要写return
     * @return
     */
    public static int finallyDemo1() {
        try {
            int i = 1 / 0;
            return 10;
        }finally {
            return 20;
        }
    }

//    public static int finallyDemo2() {
//
//    }
}

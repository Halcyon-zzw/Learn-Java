import java.lang.annotation.*;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-03-31 14:33
 * @Version: 1.0
 */
@MyAnno
public class MyAnnoDemo {
    private String name;
    @MyAnno
    private int age;

    @MyAnno
    public void demo1() {

    }

    public void demo2() {

    }
}

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface MyAnno {
}
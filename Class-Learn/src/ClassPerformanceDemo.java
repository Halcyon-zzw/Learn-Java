import model.Person;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 不同方式执行方法性能演示
 *
 * @Author: zhuzw
 * @Date: 2020-07-02 15:45
 * @Version: 1.0
 */
public class ClassPerformanceDemo {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        test01();
        test02();
        test03();
    }

    //测试普通对象执行性能
    public static void test01() {

        Person person = new Person();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            person.getAge();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("普通对象执行方法耗时：" + (endTime - startTime));
    }


    //测试反射执行方法性能
    public static void test02() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Person person = new Person();
        Class personClass = person.getClass();
        Method getAgeMethod = personClass.getMethod("getAge");

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            getAgeMethod.invoke(person);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("反射执行方法耗时：" + (endTime - startTime));
    }

    //测试关闭修饰符检查性能
    public static void test03() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Person person = new Person();
        Class personClass = person.getClass();
        Method getAgeMethod = personClass.getMethod("getAge");
        getAgeMethod.setAccessible(true);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            getAgeMethod.invoke(person);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("关闭校验反射执行方法耗时：" + (endTime - startTime));
    }
}

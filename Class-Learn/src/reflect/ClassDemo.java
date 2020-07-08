package reflect;

import model.Person;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-03-30 18:35
 * @Version: 1.0
 */
public class ClassDemo {
    static Class clazz = Person.class;
    static Person person = new Person();
    public static void main(String[] args) throws Exception {
        fieldDemo();
        System.out.println("============================");
        methodDemo();
    }

    private static void methodDemo() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method1 = clazz.getMethod("eat");
        method1.invoke(person);
        Method method2 = clazz.getMethod("eat", String.class);
        method2.invoke(person, "apple");
    }

    private static void fieldDemo() throws Exception {

        System.out.println("公共字段：");
        Field[] fields = clazz.getFields();
        Arrays.stream(fields).forEach(field -> System.out.println(field));

        System.out.println("所有字段：");
        Field[] fields1 = clazz.getDeclaredFields();
        Arrays.stream(fields1).forEach(field -> System.out.println(field));


        Field field = clazz.getDeclaredField("name");
        //忽略权限修饰符的安全检查
        field.setAccessible(true);
        Object value1 = field.get(person);
        System.out.println("name原始值：" + value1);
        field.set(person, "zhuzw");
        System.out.println("设置值后：" + field.get(person));

    }
}

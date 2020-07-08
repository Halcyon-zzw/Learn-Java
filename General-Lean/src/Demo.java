import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

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
        Person person = new Person();
        test(person);
        System.out.println(person);
        testStr();
    }

    public static void test(Person person) {
        HashSet<Person> personSet = new HashSet<>();

        personSet.add(person);
        for (Person person1 : personSet) {
            System.out.println(person1);
        }
        person.setAge(20);
        for (Person person1 : personSet) {
            System.out.println(person1);
        }

    }

    public static void testStr() {
        String text = "123\n123";
        System.out.println(text.length());
    }
}

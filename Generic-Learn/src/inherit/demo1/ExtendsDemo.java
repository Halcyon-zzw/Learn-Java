package inherit.demo1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-04-09 19:25
 * @Version: 1.0
 */
public class ExtendsDemo {
    public static void main(String[] args) {
        testExtends();
    }

    private static void testExtends() {
        PersonChild personChild = new PersonChild("zhuzw", 25, "男");
        PersonChild personChild2 = new PersonChild("zhuzwFalse", 25, "男");
        List<PersonChild> personChildList = new ArrayList<>();
        personChildList.add(personChild);
        personChildList.add(personChild2);
        List<PersonChild> personChildList1 = (List<PersonChild>) printPerson(personChildList);

        personChildList1.stream().forEach(personChild3 -> System.out.println(personChild3.getAge()));


    }

    private static List<? extends Person> printPerson(List<? extends Person> personList) {
        return personList.stream().filter(person -> !person.getName().contains("False")).collect(Collectors.toList());
    }
}

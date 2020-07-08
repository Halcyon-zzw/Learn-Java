import model.Person;

/**
 * 获取类信息演示类
 *
 * @Author: zhuzw
 * @Date: 2020-07-03 8:55
 * @Version: 1.0
 */
public class ClassInfoDemo {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<Person> personClass = Person.class;

        System.out.println(personClass.getName());

        System.out.println(personClass.getEnclosingMethod());



    }
}

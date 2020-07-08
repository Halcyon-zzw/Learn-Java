/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-03-20 13:15
 * @Version: 1.0
 */
public class Person {
    private String name = "祝志伟";
    private int age = 10;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

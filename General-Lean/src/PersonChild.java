/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-04-09 19:22
 * @Version: 1.0
 */
public class PersonChild extends Person {

    private String sex;

    public PersonChild(String name, int age, String sex) {
        super(name, age);
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "PersonChild{" +
                "sex='" + sex + '\'' +
                '}';
    }
}

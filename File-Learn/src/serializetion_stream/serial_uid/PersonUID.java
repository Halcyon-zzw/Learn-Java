package serializetion_stream.serial_uid;

import java.io.Serializable;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-03-19 20:07
 * @Version: 1.0
 */
public class PersonUID implements Serializable {
    /**
     * 解决序列号不同的问题 static final long
     */
    private static final long serialVersionUID = 1L;
    private String name;

    private int age;

    private transient String transientStr;

    public PersonUID(String name, int age) {
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

    public String getTransientStr() {
        return transientStr;
    }

    public void setTransientStr(String transientStr) {
        this.transientStr = transientStr;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", transientStr='" + transientStr + '\'' +
                '}';
    }
}

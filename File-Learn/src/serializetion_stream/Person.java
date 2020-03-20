package serializetion_stream;

import java.io.Serializable;

/**
 * Serializable为标识接口
 *
 * static：
 *      - 静态优先于非静态加载到内存（静态优先于对象进入内存中）
 *      - 被static修饰的成员变量不能被序列化
 * transient:瞬态关键字
 *      - 被transient修饰的成员变量，不能被序列化
 *
 *
 * @Author: zhuzw
 * @Date: 2020-03-19 17:16
 * @Version: 1.0
 */
public class Person implements Serializable {
    private String name;

    private int age;

    private transient String transientStr;

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

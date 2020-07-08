package model;

import com.sun.org.apache.bcel.internal.classfile.InnerClass;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-03-30 18:35
 * @Version: 1.0
 */
public class Person {
    private String name;

    private int age;

    public String sex;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public void eat() {
        System.out.println("eat...");
    }

    public void eat(String food) {
        System.out.println("eat " + food);
    }

    public Runnable test() {
        return new Runnable() {

            @Override
            public void run() {

            }
        };
    }
}

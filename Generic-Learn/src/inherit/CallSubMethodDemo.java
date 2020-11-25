package inherit;

/**
 * 父类调用子类方法演示
 *
 * @Author: zhuzw
 * @Date: 2020-08-05 20:04
 * @Version: 1.0
 */
public class CallSubMethodDemo {
    public static void main(String[] args) {
        //输出 name:null,weight:0.0
        //核心原因，父类中调用的子类方法，而父类构造时子类的变量还没赋值
        System.out.println(new Wolf("灰太狼", 33.3).toStirng());
    }
}

class Animal {
    private String desc;

    public Animal() {
        this.desc = getDesc();
    }

    public String getDesc() {
        return "Animal";
    }

    public String toStirng() {
        return desc;
    }
}

class Wolf extends Animal {
    private String name;

    private double weight;

    public Wolf(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public String getDesc() {
        return "name:" + name + ",weight:" + weight;
    }
}
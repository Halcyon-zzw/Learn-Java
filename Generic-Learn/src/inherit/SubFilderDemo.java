package inherit;

/**
 * 子类继承父类，其中成员变量值演示
 *
 * @Author: zhuzw
 * @Date: 2020-08-05 21:24
 * @Version: 1.0
 */
public class SubFilderDemo {
    public static void main(String[] args) {
        Sub sub = new Sub();
        Mid mid = sub;
        Base base = sub;
        System.out.println(sub.i);  //2
        System.out.println(mid.i);  //20
        System.out.println(base.i); //200
        //说明：上述引用变量指向同一个对象，但调用i时，输出不一样，
        //      说明在Sub对象中不仅保存了Sub类中定义的所有实例变量，还保存了它的所有父类定义的实例变量
    }
}

class Base {
    public int i = 200;
}

class Mid extends Base {
    public int i = 20;
}

class Sub extends Mid {
    public int i = 2;
}

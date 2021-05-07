package memory_structure.constant_pool.string_table;

/**
 * StringTable的测试题
 *
 * @Author: zhuzw
 * @Date: 2020-07-15 20:07
 * @Version: 1.0
 */
public class StringTableExam {

    private static void test() {
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";
        String s4 = s1 + s2;
        String s5 = "a" + "b";
        String s6 = s4. intern();

        System.out.println(s3 == s4);   //false
        System.out.println(s3 == s5);   //true
        System.out.println(s3 == s6);   //true



        //问：如果在x2.intern()之前判断
        //如果是jdk1.6呢
        String y2 = new String("x") + new String("y");  //new String("xy")
        String y3 = y2.intern();
        String y1 = "xy";
        System.out.println(y1 == y3);   //true
        //1.6返回false，因为y2.intern()会创建一个新的""xy对象并返回

        //=========jdk1.6==========
        //String y2 = new String("x") + new String("y");  //new String("xy")
        //y2.intern();    //串池不存在，放入复制的副本

        //String y1 = "xy";   //获取到的是y2复制的副本
        //System.out.println(y2 == y1);   //false  intern时不存在，成功放入
    }


    public static void main(String[] args) {

        test();

        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";
        String s4 = s1 + s2;    //new StringBuilder().append("a").append("b") => new String("ab") => new出来的对象，在堆内存中
        String s5 = "a" + "b";  //结果在编译期间已确定为ab
        String s6 = s4. intern();

        System.out.println(s3 == s4);   //false     s3在串池中，s4在堆内存中
        System.out.println(s3 == s5);   //true      都是串池中的"ab"
        System.out.println(s3 == s6);   //true      虽然串池中存在放不进去，但会将串池中的对象返会

        //========================
        String x2 = new String("c") + new String("d");  //new String("cd")
        String x1 = "cd";   // "cd"
        x2.intern();

        //问：如果在x2.intern()之前判断
        //如果是jdk1.6呢(交换后)
        System.out.println(x1 == x2);   //false intern时已存在，放不进

        //===========交换========
        String y2 = new String("x") + new String("y");  //new String("xy")
        y2.intern();

        String y1 = "xy";
        System.out.println(y2 == y1);

        //=========jdk1.6==========
        //String y2 = new String("x") + new String("y");  //new String("xy")
        //y2.intern();    //串池不存在，放入复制的副本

        //String y1 = "xy";   //获取到的是y2复制的副本
        //System.out.println(y2 == y1);   //false  intern时不存在，成功放入
    }

}

import java.lang.annotation.ElementType;

/**
 * 测试哪些类有class
 *
 * @Author: zhuzw
 * @Date: 2020-12-07 9:37
 * @Version: 1.0
 */
public class ReflectionDemo {
    public static void main(String[] args) {

        Class<Object> objectClass = Object.class;   //类
        Class<Comparable> comparableClass = Comparable.class;   //接口
        Class<Override> overrideClass = Override.class;     //注解
        Class<ElementType> elementTypeClass = ElementType.class;       //枚举
        Class<Integer> integerClass = int.class;       //基本类型
        Class<int[]> aClass = int[].class;      //数组类型
        Class<Void> voidClass = void.class;     //void
    }
}

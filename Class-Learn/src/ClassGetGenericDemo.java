import model.Person;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * class获取泛型信息 演示
 * 只能获取方法参数中的泛型信息以及方法返回值中的泛型信息
 *
 * @Author: zhuzw
 * @Date: 2020-07-03 8:55
 * @Version: 1.0
 */
public class ClassGetGenericDemo {
    public static void test01(Map<String, Integer> map, List<String> list, int a) {
        System.out.println();
    }

    public Map<String, Integer> test02() {
        return null;
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        Class clazz = ClassGetGenericDemo.class;
        System.out.println("获取方法参数泛型：");
        Method test01Method = clazz.getMethod("test01", Map.class, List.class, int.class);
        Type[] genericParameterTypes = test01Method.getGenericParameterTypes();
        for (Type genericParameterType : genericParameterTypes) {
            System.out.println("参数：" + genericParameterType);
            if (genericParameterType instanceof ParameterizedType) {
                Type[] actualTypeArguments = ((ParameterizedType) genericParameterType).getActualTypeArguments();
                Arrays.stream(actualTypeArguments).forEach(System.out::println);
            }
            System.out.println("-----------------");
        }
        System.out.println("获取返回值泛型：");
        Method test02Method = clazz.getMethod("test02");
        Type genericReturnType = test02Method.getGenericReturnType();
        if (genericReturnType instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
            Arrays.stream(actualTypeArguments).forEach(System.out::println);

        }
    }
}

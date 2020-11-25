import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 *
 *
 * @Author: zhuzw
 * @Date: 2020-06-16 18:54
 * @Version: 1.0
 */
public class TypeDemo {
    public static void main(String[] args) {
        //TODO 为什么加{}与不加的结果不一样？
        List<String> list = new ArrayList<String>(){};
        Type superClass = list.getClass().getGenericSuperclass();

//        Map<String, Integer> map = new HashMap<String, Integer>(){};
//        Type superClass = map.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = ((ParameterizedType) superClass).getActualTypeArguments();
        Arrays.stream(actualTypeArguments).forEach(System.out::println);
    }
}

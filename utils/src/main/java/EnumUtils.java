import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 枚举工具类
 *
 * @Author: zhuzw
 * @Date: 2020-07-23 9:04
 * @Version: 1.0
 */
public class EnumUtils {


    /**
     * 获取枚举类中变量的列表
     *
     * @param enumClass  枚举类的class
     * @param paramClass 参数类型的class
     * @param <E,        T>
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static <E extends Enum<E>, T> List<T> getValues(Class<E> enumClass, Class<T> paramClass) {
        try {
            E[] values = values(enumClass);

            Field field = getField(enumClass, paramClass);
            List<T> resultList = new ArrayList<>();
            field.setAccessible(true);
            for (E e : values) {
                T t = (T) field.get(e);
                resultList.add(t);
            }
//    org.apache.commons.lang3.EnumUtils
            return resultList;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 通过field的类型获取field
     *
     * @param enumClass
     * @param paramClass
     * @param <E>
     * @param <T>
     * @return
     */
    private static <E, T> Field getField(Class<E> enumClass, Class<T> paramClass) throws IllegalAccessException {
        //如果是包装类获取包装类的基本类型
        Class basicClass = getBasicClass(paramClass);
        List<Field> fieldList = Arrays.stream(enumClass.getDeclaredFields())
                .filter(f -> f.getType() == paramClass || f.getType() == basicClass).collect(Collectors.toList());
        if (fieldList.size() != 1) {
            //抛出异常，只支持一个属性
            throw new IllegalArgumentException(paramClass + "类型属性数量异常。");
        }
        return fieldList.get(0);
    }

    private static Class getBasicClass(Class paramClass) throws IllegalAccessException {
        Field typeField = null;
        try {
            //尝试获取包装类的TYPE
            typeField = paramClass.getField("TYPE");
        } catch (NoSuchFieldException e) {
            return null;
        }
        //获取包装类TYPE成功，获取TYPE属性值（因为类型为static，所以传入null）
        return (Class) typeField.get(null);
    }

    private static <E> E[] values(Class<E> enumClass) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method valuesMethod = enumClass.getMethod("values");
        Object valuesObj = valuesMethod.invoke(enumClass);
        E[] values = (E[]) valuesObj;
        return values;
    }

    /**
     * 通过指定参数值获取枚举类型
     *
     * @param enumClass
     * @param value
     * @return
     */
    public static <E extends Enum<E>, T> E valueOf(Class<E> enumClass, T value) {
        E[] values = null;
        Field field = null;
        try {
            field = getField(enumClass, value.getClass());
            field.setAccessible(true);
            values = values(enumClass);
            for (E e : values) {
                T t = (T) field.get(e);
                if (Objects.equals(t, value)) {
                    return e;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * TODO 通过指定参数值获取枚举类型
     *
     * @param enumClass
     * @param value
     * @return
     */
    public static <E, T> E valueOf(Class<E> enumClass, T... value) {
        E[] values = null;
        Field field = null;
        try {
            field = getField(enumClass, value.getClass());
            field.setAccessible(true);
            values = values(enumClass);
            for (E e : values) {
                T t = (T) field.get(e);
                if (Objects.equals(t, value)) {
                    return e;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

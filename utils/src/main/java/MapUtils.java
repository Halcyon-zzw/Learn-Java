import java.util.*;
import java.util.function.Supplier;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-11-12 17:25
 * @Version: 1.0
 */
public class MapUtils {
    /**
     * value为集合对象时,新增新数据直接在list后添加数据
     * @param map
     * @param key
     * @param value
     * @param <T>
     * @param <V>
     */
    public static <T, V> void putList(Map<T, List<V>> map, T key, V... values) {
        putList(map, key, Arrays.asList(values));
    }

    public static <T, V> void putList(Map<T, List<V>> map, T key, List<V> values) {
        if (! map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }
        map.get(key).addAll(values);
    }



    public static <T, V> void putSet(Map<T, Set<V>> map, T key, V... values) {
        putSet(map, key, new HashSet<>(Arrays.asList(values)));
    }

    public static <T, V> void putSet(Map<T, Set<V>> map, T key, Set<V> values) {
        if (! map.containsKey(key)) {
            map.put(key, new HashSet<>());
        }
        map.get(key).addAll(values);
    }

    /**
     *
     * 参数暂时无法匹配：TODO
     * @param map
     * @param key
     * @param values
     * @param supplier 初始化容器
     * @param <T>
     * @param <V>
     */
    public static <T, V> void put(Map<T, Collection<V>> map, Supplier<Collection<V>> supplier, T key, V... values) {
        Collection<V> vs = supplier.get();
        for (V value : values) {
            vs.add(value);
        }
        put(map, key, vs, supplier);
    }

    public static <T, V> void put(Map<T, Collection<V>> map, T key, Collection<V> values, Supplier<Collection<V>> supplier) {
        if (! map.containsKey(key)) {
            map.put(key, supplier.get());
        }
        map.get(key).addAll(values);
    }
}

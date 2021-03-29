import org.springframework.lang.Nullable;

import java.util.*;

/**
 * 集合工具类
 *
 * @Author: zhuzw
 * @Date: 2020-09-03 13:58
 * @Version: 1.0
 */
public class CollectionUtils extends org.springframework.util.CollectionUtils {

    public static boolean isNotEmpty(@Nullable Collection<?> collection) {
        return collection != null && !collection.isEmpty();
    }

    public static boolean isNotEmpty(@Nullable Map<?, ?> map) {
        return map != null && !map.isEmpty();
    }

    public static <T> Set<T> list2Set(List<T> list) {
        return new HashSet<>(list);
    }

    public static <T> Collection<T> union(Collection<T> c1, Collection<T> c2) {
        if (org.springframework.util.CollectionUtils.isEmpty(c1)) {
            return c2;
        }
        if (org.springframework.util.CollectionUtils.isEmpty(c2)) {
            return c1;
        }
        c1.addAll(c2);
        return c1;
    }
}
